package com.pluralsight;

public class UtilHelper {


    public static void format(Transaction transaction) {

        String content =
                """
                \s\s\s%s\s\s\s|\s\s\s%s\s\s\s|\s\s\s%s\s\s\s|\s\s\s%s\s\s\s|\s\s\s%.02f\s\s\s|
                """.formatted(
                        transaction.getCreatedDatetime().toLocalDate(),
                        transaction.getCreatedDatetime().toLocalTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount());
        System.out.println(content);
    }
}
