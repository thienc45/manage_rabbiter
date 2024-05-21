package com.example.send.sendmail.process;

import java.text.Normalizer;
import java.util.List;
import java.util.stream.Collectors;

//public class EmailProcessor {
//    public static List<String> processEmails(List<String> emails) {
//        return emails.stream()
//                .map(String::trim) // Loại bỏ khoảng trắng thừa
//                .map(EmailProcessor::removeAccents) // Loại bỏ dấu
//                .collect(Collectors.toList());
//    }
//
//    private static String removeAccents(String str) {
//        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
//        return normalized.replaceAll("\\p{M}", "");
//    }
//}
