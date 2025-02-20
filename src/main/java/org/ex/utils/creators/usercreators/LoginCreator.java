package org.ex.utils.creators.usercreators;
import static org.ex.utils.base.CyrillicStringGenerator.generateRandomCyrillicString;
import static org.ex.utils.base.StaticBaseUtils.FAKER;
import static org.ex.utils.base.StaticBaseUtils.RANDOM;

public class LoginCreator {

    public static String createLogin(String type) {
        String trimmedType = (type != null) ? type.trim() : null;

        if (trimmedType == null || trimmedType.isEmpty()) {
            return null;
        }

        return switch (trimmedType) {
            case "только спецсимволы" -> generateSpecialCharacters();
            case "кириллица" -> {
                String[] usernames = {"Иван", "Петр", "Мария", "Елена", "Алексей", "Ольга", "Дмитрий", "Татьяна"};
                int index = RANDOM.nextInt(usernames.length);
                yield usernames[index]+generateRandomCyrillicString(10);
            }
            case "латиница" -> FAKER.name().username();
            default -> null;
        };
    }

    private static String generateSpecialCharacters() {
        String specialChars = "!@#$%^&*()_+=-`~[]{}|;':\",./<>?";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) { // Генерируем строку из 20 спецсимволов
            int index = RANDOM.nextInt(specialChars.length());
            sb.append(specialChars.charAt(index));
        }
        return sb.toString();
    }
}

