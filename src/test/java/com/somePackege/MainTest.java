package com.somePackege;

import com.sun.istack.internal.Nullable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainTest {

    public static final DateTimeFormatter DATE_DD_MM_YYYY_FORMATTER = DateTimeFormatter.ofPattern("d-MMM-yyyy");

    public static void main(String[] args) {

        String dateInString = "2019-05-01";

        LocalDate date = java.sql.Date.valueOf(dateInString).toLocalDate();

        System.out.println();
        IntStream.of(1, 2, 3, 1, 9, 2, 5, 3, 4, 8, 2).sum();

        Stream.of(1, 2, 3, 1, 9, 2, 5, 3, 4, 8, 2).max(
                Comparator.comparing(integer -> integer.hashCode()
                )).get();
        List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");
        System.out.println(items.stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList()));
        System.out.println(items);
        Map<String, Long> map = items.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);
    }

    public static String formatLocalDateDdMmYyyy(@Nullable final LocalDate localDate) {
        return localDate != null ? localDate.format(DATE_DD_MM_YYYY_FORMATTER) : "";
    }
}
