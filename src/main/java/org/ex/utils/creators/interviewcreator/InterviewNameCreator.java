package org.ex.utils.creators.interviewcreator;

import org.ex.utils.base.CyrillicStringGenerator;

import static org.ex.utils.base.StaticBaseUtils.FAKER;

public class InterviewNameCreator {

    private InterviewNameCreator() {}

    public static String createInterviewName(String type) {
        String trimmedType = type.trim();

        return switch (trimmedType) {
            case "латиница спец символы" ->
                    FAKER.lorem().sentence(5)+"  !@#$%^&*()_+=-`~[]{}|;':\",./<>?";
            case "кириллица" ->
                CyrillicStringGenerator.generateRandomCyrillicString(20);
            case "латиница" -> FAKER.lorem().sentence(5);
            default -> "default!!!!";
        };
    }
}
