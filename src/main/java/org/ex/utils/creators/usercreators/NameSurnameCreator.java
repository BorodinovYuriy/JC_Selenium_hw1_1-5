package org.ex.utils.creators.usercreators;

import static org.ex.utils.base.StaticBaseUtils.FAKER;
import static org.ex.utils.base.StaticBaseUtils.RANDOM;

public class NameSurnameCreator {
    private NameSurnameCreator() {
    }


    public static String createNameSurname(String type) {

        return switch (type.trim()) {
            case "латиница спец символы" ->
                    FAKER.name().firstName()+"!@#$%^&*()_+=-`~[]{}|;':\",./<>?";
            case "кириллица" -> {
                String[] firstNames = {"Иван", "Петр", "Мария", "Елена", "Алексей", "Ольга", "Дмитрий", "Татьяна"};
                int index = RANDOM.nextInt(firstNames.length);
                yield firstNames[index];
            }
            case "латиница" -> FAKER.name().firstName();
            default -> null;
        };
    }
}