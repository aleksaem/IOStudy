package com.luxoft.file.fileanalyzer;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileAnalyzerTest {
    private static String text = "Timmie Willie is a country mouse who is accidentally taken to a city in a vegetable basket. When he wakes up, he finds himself at a party and makes a friend. When he is unable to bear (tolerate or experience) the city life, he returns to his home but invites his friend to the village. When his friend visits him, something similar happens.";

    @Test
    public void testReadFromFile() throws IOException {
        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        String contentFromFile = fileAnalyzer.readFromFile("src/main/resources/test.txt");

        assertEquals(text, contentFromFile);
    }

    @Test
    public void testCountWords() throws IOException {
        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        int numberOfWords = fileAnalyzer.countWords(fileAnalyzer.readFromFile("src/main/resources/test.txt"), "city");

        assertEquals(2, numberOfWords);
    }
}
