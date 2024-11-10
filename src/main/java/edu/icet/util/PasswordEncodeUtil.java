package edu.icet.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncodeUtil {
    private PasswordEncodeUtil() {}

    public static String encode(String plaintPassword){
        return BCrypt.hashpw(plaintPassword, BCrypt.gensalt());
    }

    public static boolean check(String plainPassword, String hashPassword){
        return BCrypt.checkpw(plainPassword, hashPassword);
    }
}
