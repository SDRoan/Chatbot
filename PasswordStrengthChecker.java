public class PasswordStrengthChecker {

    public static boolean isStrongPassword(String password) {
        if (password.length() < 8) {
            return false; 
        }

        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else hasSpecial = true;

            if (hasUpper && hasLower && hasDigit && hasSpecial) {
                return true; 
            }
        }

        return false; 
    }
}
