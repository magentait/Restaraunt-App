package business.util;

import business.mode.InfoMode;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


public class UserUtil {
    public static String handleInfoInput(String message, String errorMessage, InfoMode mode) {
        Scanner scanner = new Scanner(System.in);
        String data;

        while (true) {
            System.out.print(message);
            data = scanner.nextLine().trim().toLowerCase();

            if (data.equals("00")) {
                return "";
            }

            if ((mode == InfoMode.USERNAME && !isValidUsername(data)) ||
                    (mode == InfoMode.PASSWORD && !isValidPassword(data))) {
                System.out.println(errorMessage + " (00 - выход в главное меню)");
            } else {
                break;
            }
        }

        return data;
    }

    private static boolean isValidUsername(String username) {
        return username.matches("[a-zA-Z0-9]{4,}");
    }

    private static boolean isValidPassword(String password) {
        return password.matches("[a-zA-Z0-9]{8,}");
    }

    public static String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

