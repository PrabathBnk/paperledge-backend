package edu.icet.util;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class FileSaveUtil {
    private FileSaveUtil() {}

    public static void saveFile(String saveDir, String fileName, MultipartFile file) throws IOException{
        InputStream fileInputStream = file.getInputStream();
        Files.copy(fileInputStream, Paths.get(saveDir).resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
    }

    public static String getFileExtension(String originalName){
        String[] splitStrings = StringUtils.cleanPath(Objects.requireNonNull(originalName)).split("\\.");
        return ("." + splitStrings[splitStrings.length - 1]);
    }
}
