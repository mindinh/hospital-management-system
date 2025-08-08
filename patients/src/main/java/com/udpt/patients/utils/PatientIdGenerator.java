package com.udpt.patients.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PatientIdGenerator {
    private static final String PREFIX = "PA";

    public static String generatePatientCode() {
        String datePart = new SimpleDateFormat("yyMMdd").format(new Date());
        String randomPart = generateRandomCode(6);

        return String.format("%s%s%s", PREFIX, datePart, randomPart);
    }

    private static String generateRandomCode(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }

}
