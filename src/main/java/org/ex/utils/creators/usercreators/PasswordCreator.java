package org.ex.utils.creators.usercreators;

import static org.ex.utils.base.StaticBaseUtils.FAKER;
import static org.ex.utils.base.StaticBaseUtils.RANDOM;

public class PasswordCreator {

    public static String createPassword(String type) {
        String trimmedType = (type != null) ? type.trim() : null;

        if (trimmedType == null || trimmedType.isEmpty()) {
            return null;
        }

        return switch (trimmedType) {
            case "только спецсимволы" -> generateSpecialCharacters();
            case "пустое поле" -> null;
            case "кириллица" -> {
                String[] passwords = {"пароль", "ключ", "код", "секрет007"};
                int index = RANDOM.nextInt(passwords.length);
                yield passwords[index];
            }
            case "латиница с цифрами" -> FAKER
                    .lorem().characters(10, true, true)
                    + FAKER.number().digits(3);
            default -> null;
        };
    }

    private static String generateSpecialCharacters() {
        String specialChars = "!@#$%^&*()_+=-`~[]{}|;':\",./<>?";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) { // Длина пароля увеличена
            int index = RANDOM.nextInt(specialChars.length());
            sb.append(specialChars.charAt(index));
        }
        return sb.toString();
    }
}