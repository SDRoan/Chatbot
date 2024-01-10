import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTimeResponder {

    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }


    public static void main(String[] args) {
        String currentDateTime = getCurrentDateTime();
        System.out.println("Current Date and Time: " + currentDateTime);
    }
}
