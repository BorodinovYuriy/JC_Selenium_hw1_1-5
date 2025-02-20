package org.ex.utils.base;

import com.github.javafaker.Faker;

import java.time.format.DateTimeFormatter;
import java.util.Random;

public class StaticBaseUtils {
    public static final DateTimeFormatter DATE_FORMATTER;
    public static final Random RANDOM;
    public static final Faker FAKER;

    static {
        FAKER = new Faker();
        RANDOM = new Random();
        DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    private StaticBaseUtils() {
    }
}
