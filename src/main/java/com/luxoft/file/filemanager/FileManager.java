package com.luxoft.file.filemanager;

import java.io.*;

public class FileManager {

    private static void checkFileExistent(File file) throws FileNotFoundException {
        if(!file.exists()){
            throw new FileNotFoundException("File doesn`t exist!");
        }
    }

    public static int countFiles(String path) throws FileNotFoundException {
        File file = new File(path);
        checkFileExistent(file);
        File[] filesInDir = file.listFiles();
        int numberOfFiles = 0;

        if (filesInDir != null) {
            for (File innerFile : filesInDir) {
                if (innerFile.isFile()) {
                    numberOfFiles++;
                } else {
                    numberOfFiles += countFiles(innerFile.getPath());
                }
            }
        }

        return numberOfFiles;
    }

    public static int countDirs(String path) throws FileNotFoundException {
        File file = new File(path);
        checkFileExistent(file);
        File[] filesInDir = file.listFiles();
        int numberOfDirs = 0;

        if (filesInDir != null) {
            for (File innerFile : filesInDir) {
                if (innerFile.isDirectory()) {
                    numberOfDirs++;
                    numberOfDirs += countDirs(innerFile.getPath());
                }
            }
        }
        return numberOfDirs;
    }

    public static void copy(String from, String to) throws IOException {
        File sources = new File(from);
        checkFileExistent(sources);
        File destination = new File(to);

        if (sources.isFile()) {
            copyFile(sources, destination);
        } else if (sources.isDirectory()) {
            copyDir(sources, destination);
        }

    }

    public static void copyFile(File sources, File destination) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(sources);
        FileOutputStream fileOutputStream = new FileOutputStream(destination);
        fileInputStream.transferTo(fileOutputStream);

        fileInputStream.close();
        fileOutputStream.close();
    }

    public static void copyDir(File sources, File destination) throws IOException {
        if (!destination.exists()) {
            destination.mkdir();
        }

        String[] files = sources.list();

        for (String file : files) {
            File srcFile = new File(sources, file);
            File destFile = new File(destination, file);

            copy(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
        }

    }

    public static void move(String from, String to) throws IOException {
        copy(from, to);
        delete(from);
    }

    public static void delete(String path) throws FileNotFoundException {
        File dir = new File(path);
        checkFileExistent(dir);
        File[] filesInDir = dir.listFiles();

        for (File file : filesInDir) {
            if (file.isFile()) {
                file.delete();
            } else {
                file.deleteOnExit();
                delete(file.getAbsolutePath());
            }
        }

        dir.delete();
    }

}
