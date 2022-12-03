package com.github.oogasawa.utility.types;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Optional;

import com.github.oogasawa.utility.types.string.DoubleToString;

/** A type conversion utility class, which comforms to {@code Optional} class style of error handling.
 *
 * @author oogasawa
 *
 */
public class Type {

    public static final String NA = "\\N";

    public static void main(String[] args) {
        System.out.println("Byte.MAX_VALUE    : " + Byte.MAX_VALUE);   //(1)
        System.out.println("Short.MAX_VALUE   : " + Short.MAX_VALUE);   //(2)
        System.out.println("Integer.MAX_VALUE : " + Integer.MAX_VALUE);   //(3)
        System.out.println("Long.MAX_VALUE    : " + Long.MAX_VALUE);    //(4)
        System.out.println("Float.MAX_VALUE   : " + Float.MAX_VALUE);   //(5)
        System.out.println("Double.MAX_VALUE  : " + Double.MAX_VALUE);   //(6)

        // Byte.MAX_VALUE    : 127
        // Short.MAX_VALUE   : 32767
        // Integer.MAX_VALUE : 2147483647
        // Long.MAX_VALUE    : 9223372036854775807
        // Float.MAX_VALUE   : 3.4028235E38
        // Double.MAX_VALUE  : 1.7976931348623157E308


        System.out.println(Type.toInteger(3147483647L));
        System.out.println(Type.toInteger(-3.14));


    }

    ///////////////////////////////////////////////////////////////////////

    /**  Returns an {@code Optional<Integer>} instance representing the specified boolean value.
     *
     * @param b  an {@code boolean} value.
     * @returns an {@code Optional<Integer>} instance representing b (true is 1, false is 0).
     */
    public static Optional<Integer> toInteger(boolean b) {
        if (b) {
            return Optional.ofNullable(Integer.valueOf(1));
        } else {
            return Optional.of(Integer.valueOf(0));
        }
    }


    public static Optional<Integer> toInteger(int n) {
        return Optional.of(Integer.valueOf(n));
    }

    /**  Returns an {@code Optional<Integer>} instance representing the specified {@code long} value.
     * If the value of the argument exceeds the upper limit of int, it overflows.
     *
     * @param n  a {@code long} value.
     * @returns an {@code Integer} instance of the value {@code n}.
     */
    public static Optional<Integer> toInteger(long n) {
        return Optional.of(Integer.valueOf((int)n));
    }


    public static Optional<Integer> toInteger(double r) {
        Double d = Double.valueOf(r);
        return Optional.of(Integer.valueOf(d.intValue()));
    }


    public static Optional<Integer> toInteger(Integer n) {
        return Optional.ofNullable(n);
    }

    public static Optional<Integer> toInteger(Long n) {
        return Optional.ofNullable(Integer.valueOf(n.intValue()));
    }

    public static Optional<Integer> toInteger(Double rD) {
        return Optional.ofNullable(rD.intValue());
    }

    public static Optional<Integer> toInteger(String s) {
        
        try {
            return toInteger(Double.valueOf(s));
        }
        catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    
    public static Optional<Integer> toInteger(char ch) {

        int n = Character.getNumericValue(ch);
        if (n >= 0) {
            return toInteger(n);
        }
        else {
            return Optional.empty();
        }
    }

    
    public static Optional<Integer> toInteger(Object o) {

        try {
            if (o == null) {
                return Optional.empty();
            }
            else if (isInteger(o)) {
                return toInteger((Integer) o);
            } else if (isLong(o)) {
                return toInteger((Long) o);
            } else if (isDouble(o)) {
                return toInteger((Double) o);
            } else if (isString(o)) {
                return toInteger((String) o);
            }
            else {
                return Optional.empty();
            }
        }
        catch (Exception e) {
            return Optional.empty();
        }

    }


    public static <T> Optional<Integer> toInteger(Optional<T> o) {
        try {
            if (o.isEmpty()) {
                return Optional.empty();
            }
            else {
                T val = o.get();
                return toInteger(val);
            }
        }
        catch (Exception e) {
            return Optional.empty();
        }
    }

    //////////////////////////////////////////////////////////////////////
    public static Optional<Long> toLong(boolean b) {
        if (b) {
            return Optional.of(Long.valueOf(1));
        } else {
            return Optional.of(Long.valueOf(0));
        }
    }

    public static Optional<Long> toLong(int n) {
        return Optional.of(Long.valueOf(n));
    }

    public static Optional<Long> toLong(long n) {
        return Optional.of(Long.valueOf(n));
    }

    public static Optional<Long> toLong(double r) {
        Double rD = Double.valueOf(r);
        return Optional.of(rD.longValue());
    }

    public static Optional<Long> toLong(Double rD) {
        return Optional.ofNullable(rD.longValue());
    }


    public static Optional<Long> toLong(Object o) {

        try {
            if (o == null) {
                return Optional.empty();
            } else if (isInteger(o)) {
                return toLong((Integer) o);
            } else if (isLong(o)) {
                return Optional.of((Long) o);
            } else if (isDouble(o)) {
                return toLong((Double) o);
            } else if (isString(o)) {
                return toLong((String) o);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    
    public static Optional<Long> toLong(String s) {

        try {
            return Optional.ofNullable(Long.valueOf(s));
        }
        catch (NumberFormatException e) {
            return Optional.empty();
        }
    }


    public static Optional<Long> toLong(StringBuffer s) {

        try {
            return Optional.ofNullable(Long.valueOf(s.toString()));
        }
        catch (NumberFormatException e) {
            return Optional.empty();
        }
    }



    public static <T> Optional<Long> toLong(Optional<T> o) {
        try {
            if (o.isEmpty()) {
                return Optional.empty();
            }
            else {
                T val = o.get();
                return toLong(val);
            }
        }
        catch (Exception e) {
            return Optional.empty();
        }
    }


    
    //////////////////////////////////////////////////////////////////////////

    public static Optional<Double> toDouble(boolean b) {
        if (b) {
            return Optional.of(Double.valueOf(1.0));
        } else {
            return Optional.of(Double.valueOf(0.0));
        }
    }

    public static Optional<Double> toDouble(char ch) {
        return toDouble(String.valueOf(ch));
    }

    public static Optional<Double> toDouble(String s) {
        try {
            if (s == null) {
                return Optional.empty();
            }
            else {
                return Optional.ofNullable(Double.valueOf(s));
            }
        }
        catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<Double> toDouble(StringBuffer s) {
        return Optional.ofNullable(Double.valueOf(s.toString()));
    }

    public static Optional<Double> toDouble(int n) {
        return Optional.of(Double.valueOf(n));
    }

    public static Optional<Double> toDouble(Integer n) {
        return Optional.ofNullable(Double.valueOf(n));
    }

    public static Optional<Double> toDouble(long n) {
        return Optional.of(Double.valueOf(n));
    }

    public static Optional<Double> toDouble(Long n) {
        return Optional.ofNullable(Double.valueOf(n));
    }

    public static Optional<Double> toDouble(double r) {
        return Optional.of(Double.valueOf(r));
    }

    public static Optional<Double> toDouble(Double r) {
        return Optional.ofNullable(r);
    }

    public static Optional<Double> toDouble(Object o) {

        try {
            if (isInteger(o)) {
                return toDouble((Integer) o);
            } else if (isLong(o)) {
                return toDouble((Long) o);
            } else if (isDouble(o)) {
                return toDouble((Double) o);
            } else if (isString(o)) {
                return toDouble((String) o);
            }
            else {
                return Optional.empty();
            }
        } catch (Exception e) {
            return Optional.empty();
        }

    }



    public static <T> Optional<Double> toDouble(Optional<T> o) {
        try {
            if (o.isEmpty()) {
                return Optional.empty();
            }
            else {
                T val = o.get();
                return toDouble(val);
            }
        }
        catch (Exception e) {
            return Optional.empty();
        }
    }


    
    
    ////////////////////////////////////////////////////////////////////////

    public static Optional<String> toString(boolean b) {
        if (b) {
            return Optional.of(new String("true"));
        } else {
            return Optional.of(new String("false"));
        }
    }

    public static Optional<String> toString(char ch) {
        return Optional.of(String.valueOf(ch));
    }

    public static Optional<String> toString(String s) {
        return Optional.ofNullable(s);
    }

    public static Optional<String> toString(int n) {
        return Optional.of(String.valueOf(n));
    }

    public static Optional<String> toString(long n) {
        return Optional.of(String.valueOf(n));
    }

    public static Optional<String> toString(Integer n) {
        return Optional.ofNullable(String.valueOf(n));
    }

    public static Optional<String> toString(int n, int nDigits) {
        StringBuffer format = new StringBuffer("%0");
        format.append(Type.toString(nDigits));
        format.append("d");
        return Optional.ofNullable(String.format(format.toString(), n));
    }

    
    public static Optional<String> toString(int n, String format) {
        return Optional.ofNullable(String.format(format, n));
    }

    public static Optional<String> toString(double r) {
        DoubleToString a = new DoubleToString();
        StringBuffer s = new StringBuffer();
        a.append(s, r);
        return Optional.ofNullable(s.toString());
    }

    public static Optional<String> toString(double r, String formatStr) {
        NumberFormat form = new DecimalFormat(formatStr);
        return Optional.ofNullable(form.format(r));
    }

    public static Optional<String> toString(Double rD) {
        return toString(rD.doubleValue());
    }

    public static Optional<String> toString(Object o) {

        try {
            if (o == null) {
                return Optional.empty();
            }
            else if (isInteger(o)) {
                return toString((Integer) o);
            }
            else if (isLong(o)) {
                return toString((Long) o);
            }
            else if (isDouble(o)) {
                return toString((Double) o);
            }
            else if (isString(o)) {
                return Optional.of((String)o);
            }
            else {
                return Optional.of(o.toString());
            }

        } catch (Exception e) {
            return Optional.empty();
        }

    }



    public static <T> Optional<String> toString(Optional<T> o) {
        try {
            if (o.isEmpty()) {
                return Optional.empty();
            }
            else {
                T val = o.get();
                return toString(val);
            }
        }
        catch (Exception e) {
            return Optional.empty();
        }
    }


    ////////////////////////

    
    
    public static Optional<StringBuffer> toStringBuffer(char ch) {
        return Optional.of(new StringBuffer(Type.toString(ch).get()));
    }

    public static Optional<StringBuffer> toStringBuffer(String s) {
        if (s == null) {
            return Optional.empty();
        }
        return Optional.of(new StringBuffer(s));
    }

    
    public static Optional<StringBuffer> toStringBuffer(int n) {
        return Optional.of(new StringBuffer(Type.toString(n).get()));
    }

    
    public static Optional<StringBuffer> toStringBuffer(Integer n) {
        if (n == null) {
            return Optional.empty();
        }

        return Optional.of(new StringBuffer(Type.toString(n).get()));
    }

    
    public static Optional<StringBuffer> toStringBuffer(double r) {
        return Optional.of(new StringBuffer(Type.toString(r).get()));
    }
            

    public static Optional<StringBuffer> toStringBuffer(Double rD) {
        if (rD == null) {
            return Optional.empty();
        }

        return Optional.of(new StringBuffer(Type.toString(rD).get()));
    }

    public static Optional<StringBuffer> toStringBuffer(Object o) {
        if (o == null) {
            return Optional.empty();
        }

        return Optional.of(new StringBuffer(Type.toString(o).get()));
    }


    

    ////////////////////////////////////////////////////////////////////
    // Comparing functions
    ///////////////////////////////////////////////////////////////////
    public static boolean eq(Object val1, Object val2) {
        if (Type.isInteger(val1) || Type.isDouble(val1)) {
            double v1 = Type.to_double(val1);
            double v2 = Type.to_double(val2);
            return v1 == v2;
        } else if (Type.isString(val1)) {
            return val1.equals(val2);
        }
        return false;
    }

    public static boolean gt(Object val1, Object val2) {
        if (Type.isInteger(val1) || Type.isDouble(val1)) {
            double v1 = Type.to_double(val1);
            double v2 = Type.to_double(val2);
            return v1 > v2;
        } else if (Type.isString(val1)) {
            String v1 = Type.toString(val1).orElse("");
            String v2 = Type.toString(val2).orElse("");
            return v1.compareTo(v2) > 0;
        }
        return false;
    }

    public static boolean lt(Object val1, Object val2) {
        if (Type.isInteger(val1) || Type.isDouble(val1)) {
            double v1 = Type.to_double(val1);
            double v2 = Type.to_double(val2);
            return v1 < v2;
        } else if (Type.isString(val1)) {
            String v1 = Type.toString(val1).orElse("");
            String v2 = Type.toString(val2).orElse("");
            return v1.compareTo(v2) < 0;
        }
        return false;
    }

    ////////////////////////////////////////////////////////////////////
    // Type checking functions
    ///////////////////////////////////////////////////////////////////
    public static boolean isInteger(Object o) {
        if (o == null) {
            return false;
        }

        String cl = o.getClass().getName();
        boolean ret = false;
        if (cl.compareTo("java.lang.Integer") == 0) {
            ret = true;
        }
        return ret;
    }

    public static boolean isLong(Object o) {
        if (o == null) {
            return false;
        }

        String cl = o.getClass().getName();
        boolean ret = false;
        if (cl.compareTo("java.lang.Long") == 0) {
            ret = true;
        }
        return ret;
    }

    public static boolean isDouble(Object o) {
        if (o == null) {
            return false;
        }

        String cl = o.getClass().getName();
        boolean ret = false;
        if (cl.equals("java.lang.Double")) {
            ret = true;
        }
        return ret;

    }

    public static boolean isString(Object o) {
        if (o == null) {
            return false;
        }

        String cl = o.getClass().getName();
        boolean ret = false;
        if (cl.equals("java.lang.String")) {
            ret = true;
        }
        return ret;

    }

    public static boolean isStringBuffer(Object o) {
        if (o == null) {
            return false;
        }

        String cl = o.getClass().getName();
        boolean ret = false;
        if (cl.equals("java.lang.StringBuffer")) {
            ret = true;
        }
        return ret;

    }

    public static boolean isBigDecimal(Object o) {
        if (o == null) {
            return false;
        }

        String cl = o.getClass().getName();
        boolean ret = false;
        if (cl.equals("java.math.BigDecimal")) {
            ret = true;
        }
        return ret;

    }

    ////////////////////////////////////////////////////////////////////
    // String type checking functions
    ///////////////////////////////////////////////////////////////////
    public static boolean isStringOfDouble(String s) {

        boolean ret = false;
        try {
            Double.parseDouble(s);
            ret = true;
        } catch (NumberFormatException ex) {
            ret = false;
        }
        return ret;
    }

    public static boolean isStringOfInteger(String s) {

        boolean ret = false;
        try {
            Integer.parseInt(s);
            ret = true;
        } catch (NumberFormatException ex) {
            ret = false;
        }
        return ret;
    }

    public static boolean isStringBufferOfDouble(StringBuffer s) {
        boolean ret = false;
        try {
            Double.parseDouble(s.toString());
            ret = true;
        } catch (NumberFormatException ex) {
            ret = false;
        }
        return ret;
    }

    public static boolean isStringOfInteger(StringBuffer s) {
        boolean ret = false;
        try {
            Integer.parseInt(s.toString());
            ret = true;
        } catch (NumberFormatException ex) {
            ret = false;
        }
        return ret;
    }

    public static boolean isEmptyString(String s) {
        if (s == null || s.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmptyString(Object obj) {

        String str = toString(obj).orElse("");
        return isEmptyString(str);

    }

    ///////////////////////////////////////////////////////////////
    public static char to_char(boolean b) {
        if (b) {
            return 1;
        } else {
            return 0;
        }
    }

    public static char to_char(int n) {
        return (char) n;
    }

    public static char to_char(Integer nI) {
        return (char) nI.intValue();
    }

    public static char to_char(String s) {
        return (char) to_int(s);
    }

    public static char to_char(StringBuffer s) {
        return (char) to_int(s);
    }

    public static char to_char(Object o) {
        return (char) to_int(o);
    }

    ///////////////////////////////////////////////////////////////
    public static int to_int(boolean b) {
        if (b) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int to_int(int n) {
        return n;
    }

    public static int to_int(Integer nI) {
        return nI.intValue();
    }

    public static int to_int(double r) {
        Double rD = Double.valueOf(r);
        return rD.intValue();
    }

    public static int to_int(Double rD) {
        if (rD == null) {
            return 0;
        }

        return rD.intValue();
    }

    public static int to_int(String s) {
        if (s == null) {
            return 0;
        }
        return Integer.valueOf(s).intValue();
    }

    public static int to_int(StringBuffer s) {
        if (s == null) {
            return 0;
        }

        return to_int(s.toString());
    }

    public static int to_int(char ch) {
        return to_int(String.valueOf(ch));
    }

    public static int to_int(Object o) {
        if (o == null) {
            return 0;
        }

        return to_int(toInteger(o));
    }

    /////////////////////////////////////////////////////////////////////
    public static long to_long(boolean b) {
        if (b) {
            return 1;
        } else {
            return 0;
        }
    }

    public static long to_long(char ch) {
        return to_long(String.valueOf(ch));
    }

    public static long to_long(int n) {
        return n;
    }

    public static long to_long(Integer nI) {
        return nI.longValue();
    }

    public static long to_long(double r) {
        return toDouble(r).orElse(0.0).longValue();
    }

    public static long to_long(Double rD) {
        if (rD == null) {
            return 0;
        }

        return rD.longValue();
    }

    public static long to_long(String s) {
        if (s == null) {
            return 0;
        }

        return to_long(toInteger(s));
    }

    public static long to_long(StringBuffer s) {
        if (s == null) {
            return 0;
        }

        return to_long(toInteger(s.toString()));
    }

    public static long to_long(Object o) {
        if (o == null) {
            return 0;
        }

        return to_long(toInteger(o));
    }

    // -----------------------
    public static float to_float(boolean b) {
        if (b) {
            return 1.0f;
        } else {
            return 0.0f;
        }
    }

    public static float to_float(char ch) {
        return to_float(String.valueOf(ch));
    }

    public static float to_float(String ds) {
        if (ds == null || ds.length() == 0) {
            return 0.0f;
        } else {
            return Float.parseFloat(ds);
        }
    }

    public static float to_float(StringBuffer ds) {
        if (ds == null) {
            return 0.0f;
        }
        return Float.parseFloat(ds.toString());
    }

    public static float to_float(int n) {
        return toInteger(n).orElse(0).floatValue();
    }

    public static float to_float(Integer n) {
        if (n == null) {
            return 0.0f;
        }

        return n.floatValue();
    }

    public static float to_float(double r) {
        return (float) r;
    }

    public static float to_float(Double rD) {
        if (rD == null) {
            return 0.0f;
        }

        return rD.floatValue();
    }

    public static float to_float(Object o) {
        if (o == null) {
            return 0.0f;
        }

        return to_float(toDouble(o));
    }

    // -----------------------
    public static double to_double(boolean b) {
        if (b) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

    public static double to_double(char ch) {
        return to_double(String.valueOf(ch));
    }

    public static double to_double(String ds) {
        if (ds == null || ds.length() == 0) {
            return 0.0;
        } else {
            return Double.parseDouble(ds);
        }
    }

    public static double to_double(StringBuffer ds) {
        if (ds == null) {
            return 0.0;
        }
        return Double.parseDouble(ds.toString());
    }

    public static double to_double(int n) {
        return toInteger(n).orElse(0).doubleValue();
    }

    public static double to_double(Integer n) {
        if (n == null) {
            return 0.0;
        }

        return n.doubleValue();
    }

    public static double to_double(double r) {
        return r;
    }

    public static double to_double(Double rD) {
        if (rD == null) {
            return 0.0;
        }

        return rD.doubleValue();
    }

    public static double to_double(Object o) {
        if (o == null) {
            return 0.0;
        }

        return to_double(toDouble(o));
    }



    // -----------------------    
    /*   
    public static String toString(int n, int digit) {
        String ret = new String();
        if (n>=0) {
            String s = String.valueOf(n);
            int    d = digit - s.length();
            if (d > 0) {
                for (int i=0; i<d; i++) {
                    ret += "0";
                }
                ret += s;
            }
            else {
                ret = s;
            }
        }
        else {
            int    a = Math.abs(n);
            String s = String.valueOf(a);
            int    d = digit - s.length() - 1;
            if (d > 0) {
                ret += "-";
                for (int i=0; i<d; i++) {
                    ret += "0";
                }
                ret += s;
            }
            else {
                ret = String.valueOf(n);
            }
        }
        return ret;    
    }
     */
    // -----------------------

    /*
    public static ArrayList<Integer> toIntegerList(int[] array) {
        ArrayList<Integer> list = new ArrayList<Integer>(array.length);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    public static <T> int[] to_int_array(ArrayList<T> lst) {

        int[] ret = new int[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            ret[i] = to_int(toInteger(lst.get(i)));
        }
        return ret;
    }

    public static <T> double[] to_double_array(ArrayList<T> lst) {

        double[] ret = new double[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            ret[i] = to_double(toDouble(lst.get(i)));
        }
        return ret;
    }

    public static <T> String[] toStringArray(ArrayList<T> lst) {
        String[] ret = new String[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            ret[i] = toString(lst.get(i));
        }
        return ret;
    }

    // -----------------------
    public static ArrayList<String> toStringList(File[] f) {
        ArrayList<String> ret = new ArrayList<String>(f.length);
        for (int i = 0; i < f.length; i++) {
            ret.set(i, f[i].getPath());
        }
        return ret;
    }

    public static ArrayList<String> toStringList(String[] a) {
        ArrayList<String> ret = new ArrayList<String>();
        for (int i = 0; i < a.length; i++) {
            ret.add(a[i]);
        }
        return ret;
    }

    public static ArrayList<String> toStringList(int[] a) {
        ArrayList<String> ret = new ArrayList<String>();
        for (int i = 0; i < a.length; i++) {
            ret.add(toString(a[i]));
        }
        return ret;
    }

    public static ArrayList<String> toStringList(double[] a) {
        ArrayList<String> ret = new ArrayList<String>();
        for (int i = 0; i < a.length; i++) {
            ret.add(toString(a[i]));
        }
        return ret;
    }

    public static String[] toArray(ArrayList<String> list) {
        String[] ret = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
*/
}
