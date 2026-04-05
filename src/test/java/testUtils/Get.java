package testUtils;

import java.util.concurrent.ThreadLocalRandom;

public class Get {

    public static String randomLetterString(int length) throws Exception {
        try {
            StringBuilder stringBuilder = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                stringBuilder.append((char) ('A' + ThreadLocalRandom.current().nextInt(26)));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static String randomNumericString(int length) throws Exception {
        try {
            StringBuilder stringBuilder = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                stringBuilder.append((char) ('0' + ThreadLocalRandom.current().nextInt(10)));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
