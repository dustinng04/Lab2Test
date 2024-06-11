import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Validator {

    public boolean isValidEmailAddress(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        if (!email.contains("@")) {
            return false;
        }

        String[] parts = email.split("@");
        if (parts.length != 2) {
            return false;
        }

        String localPart = parts[0];
        String domain = parts[1];

        if (localPart.isEmpty() || domain.isEmpty()) {
            return false;
        }

        if (localPart.length() > 64 || domain.length() > 255) {
            return false;
        }
        // Check local part test@example.com => local part: Test
        String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!#$%&'*+-/=?^_`{|}~.";

        for (char c : localPart.toCharArray()) {
            if (validChars.indexOf(c) == -1) {
                return false;
            }
        }

        // Check domain part
        if (domain.length() < 3 || domain.split("\\.").length < 2) {
            return false;
        }

        return true;
    }

    public boolean validatePassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password must not be null");
        }
        if (password.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        if (!Character.isLetter(password.charAt(0))) {
            return false;
        }

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        return hasUppercase && hasLowercase && hasDigit && hasSpecial;
    }

    public boolean isValidPhone(String phone) {

        if (phone== null || phone.length() != 10 || phone.charAt(0) != '0') {
            return false;
        }

        for (char c : phone.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public boolean validateCreditCard(String creditCard) {
        if (creditCard == null) {
            throw new IllegalArgumentException("Credit card number must not be null");
        }
        // Simplified validation: check if credit card is exactly 16 digits
        if (creditCard.length() != 16) {
            return false;
        }
        for (char ch : creditCard.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }
    public boolean validatePostalCode(String postalCode) {
        if (postalCode == null) {
            throw new IllegalArgumentException("Postal code must not be null");
        }
        // Simplified validation: check if postal code is 5 digits or 9 digits with a hyphen
        if (postalCode.length() == 5 || postalCode.length() == 10) {
            if (postalCode.length() == 10 && postalCode.charAt(5) != '-') {
                return false;
            }
            for (int i = 0; i < postalCode.length(); i++) {
                if (i != 5 && !Character.isDigit(postalCode.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isValidDateOfBirth(int day, int month, int year) {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        // Check if the date is after the current date
        if (year > currentYear) return false;

        // Check if the day is valid for the given month and year
        YearMonth yearMonth = YearMonth.of(year, month);
        int lastDayOfMonth = yearMonth.lengthOfMonth();
        if (day < 1 || day > lastDayOfMonth) {
            return false;
        }

        // Check if the year is a leap year
        boolean isLeapYear = yearMonth.isLeapYear();
        if (isLeapYear && month == 2 && day == 29) {
            return true;
        } else if (!isLeapYear && month == 2 && day > 28) {
            return false;
        }

        // Check months with 30 days
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
            return false;
        }

        return true;
    }

    public boolean validateTime(String time) {
        if (time == null) {
            throw new IllegalArgumentException("Time must not be null");
        }

        String[] parts = time.split(":");
        if (parts.length != 2) {
            return false;
        }

        int hours;
        int minutes;

        try {
            hours = Integer.parseInt(parts[0]);
            minutes = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return false;
        }

        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            return false;
        }

        return true;
    }

    public boolean validateIPAddress(String ipAddress) {
        if (ipAddress == null) {
            throw new IllegalArgumentException("IP address must not be null");
        }

        String[] parts = ipAddress.split("\\.");
        if (parts.length != 4) {
            return false;
        }

        // Check the chars from each part
        for (String part : parts) {
            if (part.isEmpty() || part.length() > 3) {
                return false;
            }

            for (int i = 0; i < part.length(); i++) {
                char c = part.charAt(i);
                if (!Character.isDigit(c) && c != 'a' && c != 'A' && c != 'b' && c != 'B' && c != 'c'
                        && c != 'C' && c != 'd' && c != 'D' && c != 'e' && c != 'E'
                        && c != 'f' && c != 'F') {
                    return false;
                }
            }

            if (Integer.parseInt(part) > 255) {
                return false;
            }
        }

        return true;
    }

    public static int fibonacci(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Invalid input. n must be a positive integer.");
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        int prev1 = 1;
        int prev2 = 1;
        int fib = 0;

        // Calculate fibonacci
        for (int i = 3; i <= n; i++) {
            fib = prev1 + prev2;
            prev1 = prev2;
            prev2 = fib;
        }

        return fib;
    }
    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Invalid input. n must be a non-negative integer.");
        }

        if (n == 0 || n == 1) {
            return 1;
        }

        int factorial = 1;

        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }

        return factorial;
    }

    public static boolean isPalindrome(String input) {
        if (input == null) {
            return false;
        }

        int left = 0;
        int right = input.length() - 1;
        // Ktra char doi xung
        while (left < right) {
            if (input.charAt(left) != input.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean areAnagrams(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }

        if (str1.length() != str2.length()) {
            return false;
        }

        char[] sortedStr1 = str1.toLowerCase().toCharArray();
        char[] sortedStr2 = str2.toLowerCase().toCharArray();

        Arrays.sort(sortedStr1);
        Arrays.sort(sortedStr2);

        return Arrays.equals(sortedStr1, sortedStr2);
    }
}
