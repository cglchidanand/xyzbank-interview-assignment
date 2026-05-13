package xyzbank.interview.assignment.util;

import java.util.Random;

public class AccountNumberGenerator {

    private static final Random RANDOM =
            new Random();

    public static String generateAccountNumber() {

        StringBuilder builder =
                new StringBuilder("ABNA");

        for (int i = 0; i < 12; i++) {

            builder.append(
                    RANDOM.nextInt(10)
            );
        }

        return builder.toString();
    }
}