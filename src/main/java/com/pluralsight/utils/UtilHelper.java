package com.pluralsight.utils;

import com.pluralsight.model.Transaction;

public class UtilHeper {


    public static void format(Transaction transaction) {

        String content =
                """
                \s\s\s%s\s\s\s|\s\s\s%s\s\s\s|\s\s\s%s\s\s\s|\s\s\s%s\s\s\s|\s\s\s%.02f\s\s\s|
                """.formatted(
                        transaction.getCreateDate(),
                        transaction.getCreateTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount());
        System.out.println(content);
    }
}
