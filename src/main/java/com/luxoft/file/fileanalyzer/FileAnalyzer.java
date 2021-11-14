package com.luxoft.file.fileanalyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAnalyzer {

    public static void search(String path, String word) throws IOException {
        System.out.println("Number of word '" + word + "' in the text: ");
        System.out.println(countWords(readFromFile(path), word));

        System.out.println("Sentences that contain this word: ");
        List sentences = contentWord(getContentList(path), word);
        for (Object sentence : sentences) {
            System.out.println(sentence);
        }


    }

    public static String readFromFile(String path) throws IOException {
        File file = new File(path);
        if(!file.exists()){
            throw new FileNotFoundException("File doesn`t exist!");
        }
        InputStream inputStream = new FileInputStream(file);

        int fileLength = (int) file.length();
        byte[] contentArray = new byte[fileLength];

        inputStream.read(contentArray);

        return new String(contentArray);
    }

    public static List getContentList(String path) throws IOException {
        String content = readFromFile(path);
        ArrayList lines = new ArrayList();
        String[] sentences = content.split("[.!?]\\s*");
        for (String sentence : sentences) {
            lines.add(sentence);
        }

        return lines;
    }

    public static int countWords(String text, String givenWord) {
        Pattern pattern = Pattern.compile(givenWord);
        int counter = 0;

        for (String word : text.split("[ ,()]+")) {
            Matcher matcher = pattern.matcher(word);

            if (matcher.matches()) {
                counter++;
            }
        }
        return counter;
    }

    public static List contentWord(List<String> list, String givenWord) {
        List result = new ArrayList();

        for (String sentence : list) {
            String[] words = sentence.split("[ ,()]+");

            for (String word : words) {
                if (givenWord.equals(word)) {
                    result.add(sentence);
                }
            }
        }
        return result;
    }
}
