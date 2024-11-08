package edu.icet.util;

public class GenerateIdUtil {
    private GenerateIdUtil() {}

    public static String generateId(String identifier, int size, String currentMaxId){
        return identifier + String.format(("%0"+ size +"d"), (Integer.parseInt(currentMaxId.replaceFirst(identifier, "")) + 1));
    }
}
