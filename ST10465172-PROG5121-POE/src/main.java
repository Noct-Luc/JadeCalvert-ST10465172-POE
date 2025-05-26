
import java.util.Scanner; // For user input
import java.util.regex.Matcher; // For matching regular expressions
import java.util.regex.Pattern; // For defining regular expression patterns
import java.util.HashMap; // For storing usernames and passwords
public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> users = new HashMap<>();

        String username = "";
        boolean usernameValid = false;
        String cellphoneNumber;
        boolean cellphoneValid = false;
        String password = "";
        boolean passwordValid = false;

        // Loops until user puts in the correct username
        while (!usernameValid) {
            System.out.println("Enter your username (max 5 chars, one '_'):");
            username = scanner.nextLine();

            // Validate the username
            if (checkUsername(username)) {
                System.out.println("Username successfully captured!");
                usernameValid = true;
            } else {
                System.out.println("Username is not correctly formated, please ensure it contains an underscore and is no than 5 characters in length.");
            }
        }
        // Storing username and password
        users.put(username, password);

        // Loops until user uses a valid cellphone number
        while (!cellphoneValid) {
            System.out.println("Enter your cellphone number (starting with international country code):");
            cellphoneNumber = scanner.nextLine();

            // Validate cellphone number
            if (checkCellphoneNumber(cellphoneNumber)) {
                System.out.println("Cellphone number successfully added!");
                cellphoneValid = true;
            } else {
                System.out.println("Cellphone number incorrectly formatted or does not contain an international code");
            }
        }
        // Loop until a valid password is entered
        while (!passwordValid) {
            password = scanner.nextLine();
            System.out.println("Enter your password (>= 8 chars, upper, lower, digit, special):");
            // Validate the entered password
            if (checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured!");
                System.out.println("All data captured successfully");
                passwordValid = true;
            } else {
                System.out.println("Password is not formatted correctly, please ensure that it is least 8 characters, contains an upper and lowercase letter and a special character.");
            }
        }

    }
    //username validation
    public static boolean checkUsername (String username) {
        // Count the number of underscore characters in the username
        long underscoreCount = username.chars().filter(ch -> ch == '_').count();
        // Return true if conditions are met
        return underscoreCount == 1 && username.length() <= 5;
    }

    //Regex pattern was inspired by ChatGPT.2025. loginFeature.(Version 1.0) [Source code] available at: <https://chatgpt.com> accessed on 2 April 2025
    //Cellphone validation
    public static boolean checkCellphoneNumber(String cellphoneNumber) {

        return cellphoneNumber != null && cellphoneNumber.matches("^[0-9+]+$") && cellphoneNumber.length() < 12;
    }

    //password validation
    public static boolean checkPasswordComplexity(String password) {

        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else {
                Pattern specialPattern = Pattern.compile("[^a-zA-Z0-9]");
                Matcher specialMatcher = specialPattern.matcher(String.valueOf(c));
                if (specialMatcher.find()) {
                    hasSpecial = true;
                }
            }
        }
        // Return true if all the conditions are met
        return hasUpper && hasLower && hasDigit && hasSpecial;

    }
}
