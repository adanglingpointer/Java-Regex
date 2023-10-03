import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static void main(String[] args) {

        System.out.println("literal matches");
        // -------------------------------- //

        System.out.println("cat".matches("cat"));  // true
        System.out.println("cat".matches("Cat"));  // false
        System.out.println("cat".matches("ca"));  // false

        System.out.println("\nCase or character insensitive");
        // ------------------------------------------------ //

        System.out.println("Cat".toLowerCase().matches("cat"));  // true
        System.out.println("Cat".matches("[cC]at"));  // true
        System.out.println("bat".matches("[cCbB]at"));  // true

        System.out.println("\nRange of characters:");
        // --------------------------------------- //

        System.out.println("cat".matches("[a-f]at"));  // true
        System.out.println("Cat".matches("[a-fA-F]at"));  // true

        System.out.println("\nAllow every character except ... :");
        // ----------------------------------------------------- //

        System.out.println("cat".matches("[^c]at"));  // false
        System.out.println("bat".matches("[^c]at"));  // true
        System.out.println("cat".matches("[^a-z]at"));  // false

        System.out.println("\nWord characters:");
        // ----------------------------------- //

        //  \w is the regex for a Latin character,
        //  we escape it with an extra slash
        System.out.println("cat".matches("\\wat"));  // true
        System.out.println("&at".matches("\\wat"));  // false
        System.out.println("_at".matches("\\wat"));  // true
        //  _ is a special case

        System.out.println("1at".matches("\\wat"));  // true
        System.out.println("cat".matches("\\w\\w\\w"));  // true

        System.out.println("\nDigits:");
        // ------------------------ //

        System.out.println("c".matches("\\d"));  // false
        System.out.println("4".matches("\\d"));  // true

        System.out.println("\nQuantifiers:");
        // ------------------------------ //

        System.out.println("123-456-7890"
                .matches("\\d{3}-\\d{3}-\\d{4}"));  // true

        System.out.println("123.456.7890"
                .matches("\\d{3}[-.]\\d{3}[-.]\\d{4}"));  // true

        System.out.println("1 123.456.7890"
                .matches("\\d[.\\s]\\d{3}[.\\s]\\d{3}[.\\s]\\d{4}"));  // true
                // \\s for spaces

        System.out.println("a  -   b".matches("\\w[-\\s]+\\w"));  // true
        // + for one or more of the character(s) specified prior

        System.out.println("ab".matches("\\w[-\\s]*\\w"));  // true
        // * for zero or more of the character(s) specified prior

        System.out.println("azb".matches("\\w[-\\s]*\\w"));  // false

        System.out.println("a b".matches("\\w\\s?\\w"));  // true
        // ? for zero or one of the character(s) specified prior

        System.out.println("a  b".matches("\\w\\s?\\w"));  // false

        System.out.println("123 45678"
                .matches("\\d{3}[\\s-]\\d{2,5}"));  // true
        // , for a range of numbers

        System.out.println("123-456"
                .matches("\\d{3}[\\s-]\\d{2,5}"));  // true

        System.out.println("454546"
                .matches("\\d{4,}"));  // true
        // only a lower limit, no upper limit

        System.out.println("123 456-7890"
                .matches("(\\d{3}[-\\s]){2}\\d{4}"));  // true
        // grouping together regex inside parens

        System.out.println("\nPatterns");
        // --------------------------- //

        String regex = "(1[-.,\\s]?)?(\\d{3}[-.,\\s]?){1,2}\\d{4}";
        // each set of parens are a 'capture group'.
        // Two capture groups here

        String phoneNumber = "232.333.2365";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(phoneNumber);

        if (mat.matches()) {
            System.out.println(mat.group(1));  // null
            // no country code

            System.out.println(mat.group(2));  // 333.
            // when using quantifiers with capture groups
            // only comes back with the last match
            // 232 is skipped in this case
        }

        String phoneNumber2 = "1.232.333.2365";
        String regex2 = "(1[-.,\\s]?)?(\\d{3}[-.,\\s]?)(\\d{3}[-.,\\s]?)\\d{4}";

        Pattern pat2 = Pattern.compile(regex2);
        Matcher mat2 = pat2.matcher(phoneNumber2);

        if (mat2.matches()) {
            System.out.println(mat2.group(1));  // 1.
            System.out.println(mat2.group(2));  // 232.
            System.out.println(mat2.group(3));  // 333.
            System.out.println(mat2.group(0));  // 1.232.333.2365
        }

        // Note: outer captures groups are numbered prior to inner
        String regex3 = "((\\d{1,2})[-.,\\s]?)?((\\d{3})[-.,\\s]?)((\\d{3})[-.,\\s]?)(\\d{4})";
        String phoneNumber3 = "12.232.333.2365";

        Pattern pat3 = Pattern.compile(regex3);
        Matcher mat3 = pat3.matcher(phoneNumber3);

        if (mat3.matches()) {
            System.out.format("Country code: %s\n", mat3.group(2));
            System.out.format("Area code: %s\n", mat3.group(4));
            System.out.format("Exchange: %s\n", mat3.group(6));
            System.out.format("Line number: %s\n", mat3.group(7));
        }

        // To tell regex not to capture a group inside parens, use ?:
        String regex4 = "(?:(\\d{1,2})[-.,\\s]?)?(?:(\\d{3})[-.,\\s]?)(?:(\\d{3})[-.,\\s]?)(\\d{4})";
        String phoneNumber4 = "12.232.333.2365";

        Pattern pat4 = Pattern.compile(regex4);
        Matcher mat4 = pat4.matcher(phoneNumber4);

        if (mat4.matches()) {
            System.out.format("Country code: %s\n", mat4.group(1));
            System.out.format("Area code: %s\n", mat4.group(2));
            System.out.format("Exchange: %s\n", mat4.group(3));
            System.out.format("Line number: %s\n", mat4.group(4));
        }

        System.out.println("\nNamed capture groups:");
        // --------------------------- //

        // Named capture groups:
        // ?<name>
        String regex5 = "(?:(?<countryCode>\\d{1,2})[-.,\\s]?)?(?:(\\d{3})[-.,\\s]?)(?:(\\d{3})[-.,\\s]?)(\\d{4})";
        String phoneNumber5 = "12.232.333.2365";

        Pattern pat5 = Pattern.compile(regex5);
        Matcher mat5 = pat5.matcher(phoneNumber5);

        if (mat5.matches()) {
            System.out.format("Country code: %s\n", mat5.group("countryCode"));
            System.out.format("Area code: %s\n", mat5.group(2));
            System.out.format("Exchange: %s\n", mat5.group(3));
            System.out.format("Line number: %s\n", mat5.group(4));
        }

        System.out.println("\nRegex Comments");
        // --------------------------- //

        //  Regex comments disallows spaces

        String regex6 = """
                (?:(?<countryCode>\\d{1,2})[-.,\\s]?)? # Gets country code
                (?:(?<areaCode>\\d{3})[-.,\\s]?) # Gets area code
                (?:(?<exchange>\\d{3})[-.,\\s]?) # Gets exchange
                (?<lineNumber>\\d{4}) # Gets line number""";
        String phoneNumber6 = "12.232.333.2365";

        Pattern pat6 = Pattern.compile(regex6, Pattern.COMMENTS);
        Matcher mat6 = pat6.matcher(phoneNumber6);

        if (mat6.matches()) {
            System.out.format("Country code: %s\n", mat6.group("countryCode"));
            System.out.format("Area code: %s\n", mat6.group("areaCode"));
            System.out.format("Exchange: %s\n", mat6.group("exchange"));
            System.out.format("Line number: %s\n", mat6.group("lineNumber"));
        }

    }
}
