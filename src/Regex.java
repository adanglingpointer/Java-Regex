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

        

    }
}
