package lk.ijse.possringmaster.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppUtil {
        public static String getCurrentDateTime() {
            return LocalDateTime.now().toString();
        }

        public static String getCurrentDate() {
            return LocalDate.now().toString();
        }
}

