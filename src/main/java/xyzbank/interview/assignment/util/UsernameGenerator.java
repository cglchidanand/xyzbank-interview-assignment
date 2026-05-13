package xyzbank.interview.assignment.util;

import java.util.Random;

public class UsernameGenerator {

    private static final Random RANDOM =
            new Random();

    public static String generateUsername(
            String fullName
    ) {

        String cleaned =
                fullName
                        .replaceAll("\\s+", "")
                        .toLowerCase();

        int random =
                100 + RANDOM.nextInt(900);

        return cleaned + random;
    }
}