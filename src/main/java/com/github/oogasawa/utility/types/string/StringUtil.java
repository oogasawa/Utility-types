package com.github.oogasawa.utility.types.string;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.oogasawa.utility.types.Type;

public class StringUtil {

    public static void main(String[] args) {
        String testData = "abc\\n\ndef\tgh\\あ";

        System.out.println(testData);
        System.out.println("");

        String one = StringUtil.asOneLine(testData);
        System.out.println(one);
       System.out.println("");

        System.out.println(StringUtil.asMultiLines(one));

    }


    public static String reverse(String str) {
        return new StringBuffer(str).reverse().toString();
    }


    public static String getMD5(String str) {
        MessageDigestAdapter md5 = null;
        try {
            md5 = new MessageDigestAdapter("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5.digest(str);
    }


    public static String asMultiLines(String str) {
        boolean flg = false;
        StringBuilder sb = new StringBuilder();

        for (int index = 0; index < str.length(); index++) {
            char c = str.charAt(index);

            switch (c) {

                case '\\':
                    if (flg == true) {
                        sb.append("\\");
                        flg = false;
                    } else {
                        flg = true;
                    }
                    break;
                case 'n':
                    if (flg == true) {
                        sb.append("\n");
                        flg = false;
                    } else {
                        sb.append("n");
                    }
                    break;
                case 't':
                    if (flg == true) {
                        sb.append("\t");
                        flg = false;
                    } else {
                        sb.append("t");
                    }
                    break;
                default:
                    if (flg == true) {
                        sb.append("\\" + c);
                        flg = false;
                    } else {
                        sb.append(c);
                    }
                    break;
            }
        }

        return sb.toString();

    }

    public static String asOneLine(String str) {

        StringBuilder sb = new StringBuilder();
        try {

            if (str == null) {
                return "";
            }

            //logger.debug("asOneLine : " + str);
            boolean escaped = false;

            for (int index = 0; index < str.length(); index++) {
                char c = str.charAt(index);

                switch (c) {

                    case '\t':
                        sb.append("\\" + "t");
                        break;
                    case '\r':
                        sb.append(" ");
                        break;
                    case '\n':
                        sb.append("\\" + "n");
                        break;
                    case 'n':
                        if (escaped) {
                            sb.append("\\\\" + "n");
                        } else {
                            sb.append("n");
                        }
                        escaped = false;
                        break;
                    case 't':
                        if (escaped) {
                            sb.append("\\\\" + "t");
                        } else {
                            sb.append("t");
                        }
                        escaped = false;
                        break;
                    case '\\':
                        if (escaped) {
                            sb.append("\\\\");
                            escaped = false;
                        } else {
                            escaped = true;
                        }
                        break;
                    default:
                        if (escaped) {
                            escaped = false;
                            sb.append("\\" + c);
                        } else {
                            sb.append(c);
                        }
                        break;
                }
            }
        } catch (Exception e) {
            //logger.debug(str);
            e.printStackTrace();

        }

        return sb.toString();
    }


    /*

    public static String escapeUtfSuppl(String s) {
        CharsetDecoder utf8Decoder = Charset.forName("UTF-8").newDecoder();
        utf8Decoder.onMalformedInput(CodingErrorAction.REPLACE);
        utf8Decoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
        ByteBuffer bytes = ByteBuffer.wrap(s.getBytes());

        CharBuffer parsed = null;
        try {
            parsed = utf8Decoder.decode(bytes);
        } catch (CharacterCodingException e) {
            // TODO Auto-generated catch block
            System.err.println("error");
            e.printStackTrace();
        }

        return parsed.toString();
    }
*/


    public static String removeChar(String str, char ch) {
        StringBuffer buf = new StringBuffer();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) != ch) {
                buf.append(str.charAt(i));
            }
        }
        return buf.toString();
    }


    /** Replaces all newline characters in the given string with a space character.
     *
     * @param origStr A String
     * @return the replaced string.
     */
   public static String removeNewLines(String origStr) {
        ArrayList<String> lines = StringUtil.splitByNewLine(origStr);
        String ret = join(" ", lines);
        return ret;
    }



    private static <T> String join(String delim, ArrayList<T> list) {
        if (list == null || list.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0));

        for (int i = 1; i < list.size(); i++) {
            sb.append(delim);
            sb.append(Type.toString(list.get(i)));
        }

        return sb.toString();
    }

    
    /** Replaces all tab characters in the given string with a string consisting of 8 space characters.
     *
     * @param origStr A String
     * @return the replaced string.
     */
    public static String removeTabs(String str) {
        StringBuffer buf = new StringBuffer();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == '\t') {
                buf.append("        ");
            } else {
                buf.append(str.charAt(i));
            }
        }
        return buf.toString();
    }


    
    public static ArrayList<String> splitByChar(String str, char ch) {

        ArrayList<String> ret = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                ret.add(sb.toString());
                sb.delete(0, sb.length());

                // When a delimiting character is at the last of a string,
                // the last element of the returned list should be an empty string.
                if (i == str.length() - 1) {
                    sb.append("");
                }
            } else {
                sb.append(str.charAt(i));
            }

        }

        ret.add(sb.toString());

        return ret;
    }

    public static ArrayList<String> splitBySpace(String str) {
        return splitByChar(str, ' ');
    }

    public static ArrayList<String> splitByTab(String str) {
        return splitByChar(str, '\t');
    }

    public static ArrayList<String> splitByComma(String str) {
        return splitByChar(str, ',');
    }

    public static ArrayList<String> splitByNewLine(String str) {
        return splitByChar(str, '\n');
    }

    public static ArrayList<String> splitByRegex(String str, Pattern p) {

        Matcher m = p.matcher(str);

        int start = 0;
        ArrayList<String> ret = new ArrayList<String>();

        while (m.find()) {
            ret.add(str.substring(start, m.start()));
            start = m.end();
        }
        ret.add(str.substring(start, str.length()));

        return ret;
    }



}
