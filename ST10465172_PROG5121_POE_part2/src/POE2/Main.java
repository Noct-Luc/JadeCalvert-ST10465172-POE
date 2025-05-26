package POE2;
import java.util.Scanner; // For user input
import java.util.regex.Matcher; // For matching regular expressions
import java.util.regex.Pattern; // For defining regular expression patterns

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String username;
        String password;
        String cellPhoneNumber;
        String userLogin;
        String passwordLogin;

        // users enter their usernames
        while (true) {
            System.out.print("Create a username (must contain '_' and be max 5 characters): ");
            username = scanner.next();
            if (username.length() <= 5 && username.contains("_")) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted. It must contain an underscore (_) and be no more than five characters.");
            }
        }

        // users input their password
        while (true) {
            System.out.print("Create a password: ");
            password = scanner.next();
            if (checkPasswordComplexity(password)) {
                System.out.println("Password has been successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted. It must contain at least 8 characters, an uppercase letter, a lowercase letter, a number, and a special character.");
            }
        }

        // users input their cellphone numbers
        while (true) {
            System.out.print("Enter your cellphone number (must start with +27 and be 12 characters): ");
            cellPhoneNumber = scanner.next();
            if (cellPhoneNumber.startsWith("+27") && cellPhoneNumber.length() == 12) {
                System.out.println("Cellphone number has been successfully added.");
                break;
            } else {
                System.out.println("Incorrect phone number format. Must include international code (+27) and be exactly 12 characters long.");
            }
        }

        // Login loops
        boolean loginSuccess = false;
        while (!loginSuccess) {
            System.out.print("Please re-enter your username for testlogin: ");
            userLogin = scanner.next();
            System.out.print("Please re-enter your password for testlogin: ");
            passwordLogin = scanner.next();

            boolean validUserName = LoginSystem.isValidUserName(userLogin);
            boolean validPassword = LoginSystem.isValidPassword(passwordLogin);

            if (!validUserName || !validPassword) {
                System.out.println("Login failed due to incorrect username or password format.");
                continue;
            }

            if (userLogin.equals(username) && passwordLogin.equals(password)) {
                System.out.println("Login successful!");
                System.out.println("Welcome back, " + username + "! It's good to have you back.");
                loginSuccess = true;
                Quickchat.startChat();
            } else {
                System.out.println("Login failed! Incorrect username or password.");
            }
        }

        scanner.close();
    }

    // Password complexity checker
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

    // Login validation system
    public static class LoginSystem {
        public static boolean isValidUserName(String username) {
            return username.length() <= 5 && username.contains("_");
        } //works together with the loops to ensure that the user can log in as many times as they want
        public static boolean isValidPassword(String password) {
            return checkPasswordComplexity(password);
        }

    }

}