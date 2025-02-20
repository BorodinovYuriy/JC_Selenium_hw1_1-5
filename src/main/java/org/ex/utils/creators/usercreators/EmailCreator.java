package org.ex.utils.creators.usercreators;

import static org.ex.utils.base.StaticBaseUtils.FAKER;
import static org.ex.utils.base.StaticBaseUtils.RANDOM;

public class EmailCreator {

    public static String createEmail(String type) {
        String trimmedType = type.trim();

        return switch (trimmedType) {
            case "латиница не в формате email" ->
                    FAKER.lorem().word() + FAKER.number().digits(3) + "@@example.com";
            case "латиница в в формате email" -> FAKER.internet().emailAddress();
            case "пустое поле" -> null;
            case "кириллица" -> {
                String[] domains = {"@пример.рф", "@почта.рус", "@тест.ком"};
                String username = FAKER.name().firstName().toLowerCase() + FAKER.number().digits(2);
                int index = RANDOM.nextInt(domains.length);
                yield username + domains[index];
            }
            default -> null;
        };
    }
}