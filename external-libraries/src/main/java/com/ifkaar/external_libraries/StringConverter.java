package com.ifkaar.external_libraries;

/**
 * This class handles conversions regarding strings.
 *
 * @author Sajid Zeb
 * @version 1.0.0
 */
public class StringConverter {

    /**
     * This method converts word into uppercase.
     *
     * @param inputString is the word to get into uppercase.
     * @return uppercase string.
     */
    public static String toUpperCase(String inputString) {
        String result = "";
        for (int i = 0; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            char currentCharToUpperCase = Character.toUpperCase(currentChar);
            result = new StringBuilder().append(result).append(currentCharToUpperCase).toString();
        }
        return result;
    }

    /**
     * This method convert word into lowercase.
     *
     * @param inputString is the word to get into lowercase.
     * @return lowercase string.
     */
    public static String toLowerCase(String inputString) {
        String result = "";
        for (int i = 0; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            char currentCharToLowerCase = Character.toLowerCase(currentChar);
            result = result + currentCharToLowerCase;
        }
        return result;
    }

    /**
     * This method changes case of each word.
     *
     * @param inputString is the string to change case of each word.
     * @return toggle case string.
     */
    public static String toToggleCase(String inputString) {
        String result = "";
        for (int i = 0; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                char currentCharToLowerCase = Character.toLowerCase(currentChar);
                result = new StringBuilder().append(result).append(currentCharToLowerCase).toString();
            } else {
                char currentCharToUpperCase = Character.toUpperCase(currentChar);
                result = new StringBuilder().append(result).append(currentCharToUpperCase).toString();
            }
        }
        return result;
    }

    /**
     * This method change the string into camel case.
     *
     * @param inputString the string to get converted.
     * @return coverted string.
     */
    public static String toCamelCase(String inputString) {
        char[] stringArray = inputString.trim().toCharArray();
        boolean wordStarted = false;
        for (int i = 0; i < stringArray.length; i++) {
            char ch = stringArray[i];
            if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == '\'') {
                if (!wordStarted) {
                    stringArray[i] = Character.toUpperCase(stringArray[i]);
                    wordStarted = true;
                } else {
                    stringArray[i] = Character.toLowerCase(stringArray[i]);
                }
            } else {
                wordStarted = false;
            }
        }
        return new String(stringArray);
    }

    /**
     * This method change sentence to camel case.
     *
     * @param inputString is the sentence to get changed.
     * @return converted sentence.
     */
    public static String toSentenceCase(String inputString) {
        String result = "";
        if (inputString.length() == 0) {
            return result;
        }
        char firstChar = inputString.charAt(0);
        char firstCharToUpperCase = Character.toUpperCase(firstChar);
        result = result + firstCharToUpperCase;
        boolean terminalCharacterEncountered = false;
        char[] terminalCharacters = {'.', '?', '!'};
        for (int i = 1; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            if (terminalCharacterEncountered) {
                if (currentChar == ' ') {
                    result = new StringBuilder().append(result).append(currentChar).toString();
                } else {
                    char currentCharToUpperCase = Character.toUpperCase(currentChar);
                    result = new StringBuilder().append(result).append(currentCharToUpperCase).toString();
                    terminalCharacterEncountered = false;
                }
            } else {
                char currentCharToLowerCase = Character.toLowerCase(currentChar);
                result = new StringBuilder().append(result).append(currentCharToLowerCase).toString();
            }
            for (int j = 0; j < terminalCharacters.length; j++) {
                if (currentChar == terminalCharacters[j]) {
                    terminalCharacterEncountered = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * This method changes only first letter to uppercase.
     *
     * @param inputString is the string to get converted.
     * @return is the converted string.
     */
    public static String toUpperCaseOnlyFirstLetter(String inputString) {
        // Convert String to char array.
        char[] array = inputString.toCharArray();
        // Modify first element in array.
        array[0] = Character.toUpperCase(array[0]);
        // Return string.
        return new String(array);
    }

    /**
     * This method convert the long word into limited string adding 3 dots at the end.
     *
     * @param line  is the string.
     * @param limit is the number of words to which word has to limit.
     * @return converted word.
     */
    public static String conciseWordLength(String line, int limit) {
        String toReturn;
        if (line.length() >= limit) {
            toReturn = line.substring(0, limit - 3) + "...";

        } else {
            toReturn = line;
        }
        return toReturn;
    }

    /**
     * This method check if string contains both Alphabets and numbers.
     *
     * @param str is the string
     * @return true or false if string is AlphaNumeric or not.
     */
    public static boolean isAlphanumeric(String str) {
        final String numeric = ".*[0-9].*";
        final String alphabets = ".*[a-zA-Z].*";
        return str.matches(numeric) && str.matches(alphabets);
    }

    /**
     * This method gets the initials of name.
     *
     * @param name is the string of user name
     * @return initials
     */
    public static String getInitials(String name) {
        String initials = "";
        initials = name.replaceAll("([^\\s])[^\\s]+", "$1").replaceAll("\\s", "");
        if (initials.length() == 1) {
//            initials = initials.concat(String.valueOf(name.charAt(1)));
        } else {
            initials = initials.substring(0, 2);
        }
        return initials.toUpperCase();
    }
}
