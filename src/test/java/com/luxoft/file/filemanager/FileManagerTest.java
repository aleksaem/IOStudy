package com.luxoft.file.filemanager;

import com.luxoft.file.fileanalyzer.FileAnalyzer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {
    final static String RESOURCES = "src/main/resources/FileManagerTest";

    @Test
    public void testCountFilesInExistentDir() throws FileNotFoundException {
        String path = RESOURCES;
        int numberOfFiles = FileManager.countFiles(path);
        assertEquals(8, numberOfFiles);
    }

    @Test
    public void testCountFilesInEmptyDir() throws FileNotFoundException {
        String path = RESOURCES + "/CopyTo";
        int numberOfFiles = FileManager.countFiles(path);
        assertEquals(0, numberOfFiles);
    }

    @Test
    public void testCountFilesInNonExistentDir() throws FileNotFoundException {
        String path = RESOURCES + "/FileManagerTest2";
        assertThrows(FileNotFoundException.class, () -> {
            FileManager.countDirs(path);
        });
    }

    @Test
    public void testCountDirsInExistentDir() throws FileNotFoundException {
        String path = RESOURCES;
        int numberOfDirs = FileManager.countDirs(path);
        assertEquals(7, numberOfDirs);

    }

    @Test
    public void testCountDirsInEmptyDir() throws FileNotFoundException {
        String path = RESOURCES + "/CopyTo";
        int numberOfDirs = FileManager.countDirs(path);
        assertEquals(0, numberOfDirs);

    }

    @Test
    public void testCountDirsInNonExistentDir()  {
        String path = RESOURCES + "/FileManagerTest2";
        assertThrows(FileNotFoundException.class, () -> {
            FileManager.countDirs(path);
        });
    }

    @Test
    public void testCopyToMethod() throws IOException {
        assertEquals(0, FileManager.countFiles(RESOURCES + "/CopyTo"));

        FileManager.copy(RESOURCES + "/CopyFrom", RESOURCES + "/CopyTo");

        assertEquals(4, FileManager.countFiles(RESOURCES + "/CopyTo"));
    }

    @Test
    public void testMoveMethod() throws IOException {
        assertTrue(new File(RESOURCES + "/MoveFrom").exists());
        assertTrue(new File(RESOURCES + "/MoveTo").exists());

        FileManager.move(RESOURCES + "/MoveFrom", RESOURCES + "/MoveTo");

        assertFalse(new File(RESOURCES + "/MoveFrom").exists());
        assertTrue(new File(RESOURCES + "/MoveTo").exists());
        assertEquals(1, FileManager.countFiles(RESOURCES + "/MoveTo"));
    }

    @AfterAll
    static void moveFilesBack() throws IOException {
        FileManager.move(RESOURCES + "/MoveTo", RESOURCES + "/MoveFrom");
        new File(RESOURCES + "/MoveTo").mkdir();
        FileManager.delete(RESOURCES + "/CopyTo");
        new File(RESOURCES + "/CopyTo").mkdir();
    }
}
