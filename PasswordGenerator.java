import java.util.Random;

public class PasswordGenerator {
    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()-_=+[]{};:,.<>?";
    private static final String ALL_CHARS = LOWERCASE_CHARS + UPPERCASE_CHARS + NUMBERS + SPECIAL_CHARS;
    private static final Random random = new Random();

    public static String generatePassword(int length) {
        if (length < 4) {
            throw new IllegalArgumentException("Password length should be at least 4 characters.");
        }

        StringBuilder password = new StringBuilder(length);


        password.append(LOWERCASE_CHARS.charAt(random.nextInt(LOWERCASE_CHARS.length())));
        password.append(UPPERCASE_CHARS.charAt(random.nextInt(UPPERCASE_CHARS.length())));
        password.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));


        for (int i = 4; i < length; i++) {
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }


        char[] passwordArray = password.toString().toCharArray();
        for (int i = passwordArray.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = passwordArray[index];
            passwordArray[index] = passwordArray[i];
            passwordArray[i] = temp;
        }

        return new String(passwordArray);
    }


    public static void main(String[] args) {
        String password = generatePassword(10); // Example: Generate a 10-character password
        System.out.println("Generated Password: " + password);
    }
}
