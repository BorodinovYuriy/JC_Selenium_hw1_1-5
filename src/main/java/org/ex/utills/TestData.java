package org.ex.utills;


import com.github.javafaker.Faker;

public class TestData {
    private TestData() {
    }

    static Faker faker = new Faker();

    public static String makeName() {
        return faker.name().name();
    }
    public static String makeSurname() {
        return faker.name().lastName();
    }
    public static String makeUsername() {
        return faker.name().username();
    }
    public static String makeEmail() {
        return faker.internet().emailAddress();
    }
    public static String makePassword() {
        return faker.internet().password();
    }
    public static String makeWords() {
        return faker.lorem().sentence(5);
    }



}