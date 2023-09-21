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

        System.out.println("\nQuantifier:");
        // ------------------------------ //

        System.out.println("123-456-7890"
                .matches("\\d{3}-\\d{3}-\\d{4}"));  // true

        System.out.println("123.456.7890"
                .matches("\\d{3}[-.]\\d{3}[-.]\\d{4}"));  // true

    }
}
