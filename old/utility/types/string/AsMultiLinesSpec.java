package com.github.oogasawa.utility.types.string;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.github.oogasawa.pojobdd.BddUtil;
//import com.github.oogasawa.utility.files.FileIO;

public class AsMultiLinesSpec {


    public static boolean exec() {
        
        String docId = BddUtil.documentId("AsMultiLinesSpec");
        Path mdPath = Path.of(docId, docId + ".md");
        
        try (PrintStream out = BddUtil.newPrintStream(mdPath)) {

            List<Boolean> results = new ArrayList<Boolean>();

            out.println(BddUtil.yamlHeader(docId, "asMultiLines"));
            results.add(asMultiLinesDesc(out));
            results.add(asOneLineExample01(out));
            results.add(asMultiLinesExample01(out));
            //results.add(conversionOnFileExample(out));
            results.add(idempotenceExample(out));

            out.flush();
            return BddUtil.allTrue(results);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;
    }



    public static boolean asMultiLinesDesc(PrintStream out) {

        String description = """

            ## asMultiLines, asOneLine

            When data is rewritten in tab-delimited format, the representation of data containing newlines is problematic.
            These methods escape newlines to a single line and restore them.
            
            """;

        out.println(description);
        
        return true;
    }
    

    public static boolean asOneLineExample01(PrintStream out) {

        // Description
        String description = """
            
            ## `asOneLine`

            ### Example: Conversion of a multi-line string to a single line string.
            
            - Given a string
            - When the give string contains some newline characters
            - Then returns a string in which all the newline characters are escaped as '\\n'

            Code:

            ```
            {{snippet}}
            ```

            Result:
                                           
            """;



        // Reality
        // %begin snippet : asOneLineExample01

        String data = """
            Today is November 26th.
            It snowed all day today.
            The snow is beautiful.
            The snow finally stopped.
            """;
            
        String result = StringUtil.asOneLine(data);
        // %end snippet : asOneLineExample01

            
        String snippet = BddUtil.readSnippet(
                            Path.of("src/test/java/com/github/oogasawa/utility/types/string/AsMultiLinesSpec.java"),
                            "asOneLineExample01");
        description = description.replace("{{snippet}}", snippet);
        out.println(description);

        
        // Expectations
        String expectation = "Today is November 26th.\\nIt snowed all day today.\\nThe snow is beautiful.\\nThe snow finally stopped.\\n";


        // Check the answer.
        boolean passed = BddUtil.assertTrue(out, expectation, result);
        assert passed;
        return passed;


        //return true;
    }



    public static boolean asMultiLinesExample01(PrintStream out) {

        // Description
        String description = """
            
            ## `asMultiLines`

            ### Example: Conversion of a single line string to a multi-line string.
            
            - Given a string
            - When the given string contains some escaped new line characters '\\n'
            - Then returns a multi-line string with escaped newlines rewritten as normal newline characters.

            Code:

            ```
            {{snippet}}
            ```

            Result:
                                           
            """;



        // Reality
        // %begin snippet : asMultiLinesExample01

        String data = "Today is November 26th.\\nIt snowed all day today.\\nThe snow is beautiful.\\nThe snow finally stopped.\\n";
            
        String result = StringUtil.asMultiLines(data);
        // %end snippet : asMultiLinesExample01

            
        String snippet = BddUtil.readSnippet(
                            Path.of("src/test/java/com/github/oogasawa/utility/types/string/AsMultiLinesSpec.java"),
                            "asMultiLinesExample01");
        description = description.replace("{{snippet}}", snippet);
        out.println(description);

        
        // Expectations
        String expectation = """
            Today is November 26th.
            It snowed all day today.
            The snow is beautiful.
            The snow finally stopped.
            """;


        // Check the answer.
        boolean passed = BddUtil.assertTrue(out, expectation, result);
        assert passed;
        return passed;


        //return true;
    }



    // public static boolean conversionOnFileExample(PrintStream out) {

    //     // Description
    //     String description = """


    //             ### Example: Conversion on a file

    //             - Given a multi-line string
    //             - When `asOneLine` method is applyed to the string and save to a file
    //             - Then The same results are obtained as when processed in memory.

    //             Code:

    //             ```
    //             {{snippet}}
    //             ```

    //             Result:

    //             """;

    //     // Reality
    //     // %begin snippet : conversionOnFileExample

    //     String result = null;
    //     try {

    //         String data = """
    //                 Today is November 26th.
    //                 It snowed all day today.
    //                 The snow is beautiful.
    //                 The snow finally stopped.
    //                 """;

    //         String testfile = "/tmp/as_multilines_test.txt";
    //         FileIO.writeFile(new File(testfile), StringUtil.asOneLine(data));
    //         result = FileIO.readFile(testfile);

    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     // %end snippet : conversionOnFileExample

    //     String snippet = BddUtil.readSnippet(
    //                         Path.of("src/test/java/com/github/oogasawa/utility/types/string/AsMultiLinesSpec.java"),
    //                         "conversionOnFileExample");
    //     description = description.replace("{{snippet}}", snippet);
    //     out.println(description);


    //     // Expectations
    //     String expectation = "Today is November 26th.\\nIt snowed all day today.\\nThe snow is beautiful.\\nThe snow finally stopped.\\n\n";

    //     // Check the answer.
    //     boolean passed = BddUtil.assertTrue(out, expectation, result);

    //     assert passed;
    //     return passed;

    //     //return true;
    // }


    
    public static boolean idempotenceExample(PrintStream out) {

        // Description
        String description = """


                ### Example: Idempotence of asOneLine method

                - Given a multi-line string
                - When `asOneLine` method is applyed to the string multiple times
                - Then The result is the same as when applied only once.

                Code:

                ```
                {{snippet}}
                ```

                Result:

                """;



        // Reality
        // %begin snippet : idempotenceExample

        String data = """
            Today is November 26th.
            It snowed all day today.
            The snow is beautiful.
            The snow finally stopped.
            """;

        String result1 = StringUtil.asOneLine(data);
        String result2 = StringUtil.asOneLine(result1);
        String result3 = StringUtil.asOneLine(result2);
        // %end snippet : idempotenceExample

            
        String snippet = BddUtil.readSnippet(
                            Path.of("src/test/java/com/github/oogasawa/utility/types/string/AsMultiLinesSpec.java"),
                            "idempotenceExample");
        description = description.replace("{{snippet}}", snippet);
        out.println(description);

        
        // Expectations
        String expectation = "Today is November 26th.\\nIt snowed all day today.\\nThe snow is beautiful.\\nThe snow finally stopped.\\n";


        // Check the answer.
        boolean r1 = BddUtil.assertTrue(out, expectation, result1);
        boolean r2 = BddUtil.assertTrue(out, expectation, result2);
        boolean r3 = BddUtil.assertTrue(out, expectation, result3);
        boolean passed = BddUtil.allTrue(r1, r2, r3);

        assert passed;
        return passed;

        //return true;
    }


    

}
