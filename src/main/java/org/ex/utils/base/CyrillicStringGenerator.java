package org.ex.utils.base;

import static org.ex.utils.base.StaticBaseUtils.RANDOM;

public class CyrillicStringGenerator {
    private static final String CYRILLIC_ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    private CyrillicStringGenerator() {
    }

    public static String generateRandomCyrillicString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Длина строки должна быть положительной.");
        }

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CYRILLIC_ALPHABET.length());
            sb.append(CYRILLIC_ALPHABET.charAt(randomIndex));
        }
        return sb.toString();
    }
}
