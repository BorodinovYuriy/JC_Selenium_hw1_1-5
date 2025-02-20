package org.ex.utils.creators.interviewcreator;

import org.ex.utils.base.CyrillicStringGenerator;

import static org.ex.utils.base.StaticBaseUtils.FAKER;

public class InterviewGradeLinkCreator {
    private InterviewGradeLinkCreator() {
    }

    public static String createGrade(String input) {

        return switch (input.trim()) {
            case "стринга длинной 255" ->
                    FAKER.lorem().characters(255);
            case "стринга длинной 256" ->
                    FAKER.lorem().characters(256);
            case "стринга спецсимволы" ->
                    FAKER.lorem().sentence(5)+"  !@#$%^&*()_+=-`~[]{}|;':\",./<>?";
            case "кириллица" ->
                    CyrillicStringGenerator.generateRandomCyrillicString(20);
            case "латиница" -> FAKER.lorem().sentence(5);

            default -> null;
        };
    }

}
