package br.harlan.sbi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encoder(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
