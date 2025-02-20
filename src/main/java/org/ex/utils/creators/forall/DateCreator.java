package org.ex.utils.creators.forall;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.ex.utils.base.StaticBaseUtils.DATE_FORMATTER;
import static org.ex.utils.base.StaticBaseUtils.FAKER;
@Slf4j
public class DateCreator {
    private DateCreator() {
    }

    public static String createDate(String type) {
        String trimmedType = type.trim();

        return switch (trimmedType) {
            case "сегодняшняя дата" -> LocalDate.now().format(DATE_FORMATTER);
            case "будущая дата" -> {
                LocalDate futureDate = LocalDate.now().plusDays(FAKER.number().numberBetween(1, 365));
                yield futureDate.format(DATE_FORMATTER);
            }
            case "прошлая дата" -> {
                LocalDate pastDate = LocalDate.now().minusDays(FAKER.number().numberBetween(1, 365));
                yield pastDate.format(DATE_FORMATTER);
            }
            case "пустое поле" -> null;
            default -> {
                try {
                    LocalDate parsedDate = LocalDate.parse(trimmedType, DATE_FORMATTER);
                    yield parsedDate.format(DATE_FORMATTER);
                } catch (DateTimeParseException e) {
                    log.error("Некорректный формат даты: {}", trimmedType);
                    yield null;
                }
            }
        };
    }
}
