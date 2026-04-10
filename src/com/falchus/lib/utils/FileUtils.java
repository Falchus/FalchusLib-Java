package com.falchus.lib.utils;

import lombok.experimental.UtilityClass;

import java.io.File;

@UtilityClass
public class FileUtils {

    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }
        folder.delete();
    }
}
