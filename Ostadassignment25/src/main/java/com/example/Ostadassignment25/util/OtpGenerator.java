package com.example.Ostadassignment25.util;

import java.util.Random;

public class OtpGenerator {

    private static final Random RANDOM = new Random();

    private OtpGenerator() {
    }

    public static String generateOtp() {

        int otp = 100000 + RANDOM.nextInt(900000);

        return String.valueOf(otp);
    }
}