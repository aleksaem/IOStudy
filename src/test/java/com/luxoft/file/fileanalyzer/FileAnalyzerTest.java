package com.luxoft.file.fileanalyzer;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileAnalyzerTest {

    @Test
    public void testReadFromFile() throws IOException {
        String contentFromFile = FileAnalyzer.readFromFile("src/main/resources/test.txt");

        String text = "Timmie Willie is a country mouse who is accidentally taken to a city in a vegetable basket. When he wakes up, he finds himself at a party and makes a friend. When he is unable to bear (tolerate or experience) the city life, he returns to his home but invites his friend to the village. When his friend visits him, something similar happens.";
        assertEquals(text, contentFromFile);
    }

    @Test
    public void testReadFromEmptyFile() throws IOException {
        String contentFromFile = FileAnalyzer.readFromFile("src/main/resources/test1.txt");

        String text = "";
        assertEquals(text, contentFromFile);
    }

    @Test
    public void testReadFromNonExistentFile() throws IOException {
        assertThrows(FileNotFoundException.class, () -> {
            String contentFromFile = FileAnalyzer.readFromFile("src/main/resources/test2.txt");
        });
    }

    @Test
    public void testCountWords() throws IOException {
        int numberOfWords = FileAnalyzer.countWords(FileAnalyzer.readFromFile("src/main/resources/test.txt"), "city");

        assertEquals(2, numberOfWords);
    }

    @Test
    public void testCountWordsIfWordIsNotPresentInText() throws IOException {
        int numberOfWords = FileAnalyzer.countWords(FileAnalyzer.readFromFile("src/main/resources/test.txt"), "bird");

        assertEquals(0, numberOfWords);
    }

    @Test
    public void testCountSentencesWithGivenWord() throws IOException {
        List sentences = FileAnalyzer.contentWord(FileAnalyzer.getContentList("src/main/resources/test.txt"), "city");
        int numberOfSentences = sentences.size();

        assertEquals(2, numberOfSentences);
    }

    @Test
    public void testCountSentencesWithGivenWordIfWordIsNotPresentInText() throws IOException {
        List sentences = FileAnalyzer.contentWord(FileAnalyzer.getContentList("src/main/resources/test.txt"), "sea");
        int numberOfSentences = sentences.size();

        assertEquals(0, numberOfSentences);
    }
}
