package utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    private FilenameFilter filter = new FilenameFilter() {
        private String[] imageExtensions = new String[]{"gif", "png", "bmp", "JPG", "jpeg", "jpg"};

        public boolean accept(File f, String name) {
            for (String extension : imageExtensions) {
                if (name.endsWith("." + extension)) {
                    return true;
                }
            }

            return false;
        }
    };

    @Test
    void testInNoSubDirectories() {
        FileManager fileManager = new FileManager();
        ArrayList<File> files = fileManager.listFilesUnderDirectory(new File("DirectoryTests/Case1"), filter);
        File f1 = new File("DirectoryTests/Case1/cath1.jpg");
        File f2 = new File("DirectoryTests/Case1/cath2.jpg");
        assertTrue(files.contains(f1));
        assertTrue(files.contains(f2));
    }

    @Test
    void testThreeEmptySubDirectoriesAndTwoFilledDirectories() {
        FileManager fileManager = new FileManager();
        ArrayList<File> files = fileManager.listFilesUnderDirectory(new File("DirectoryTests/Case5"), filter);
        File f1 = new File("DirectoryTests/Case5/Sub2/Meme1.jpg");
        File f2 = new File("DirectoryTests/Case5/Sub1/Sub1:Sub2/Meme2.jpg");
        assertTrue(files.contains(f1));
        assertTrue(files.contains(f2));
    }

    @Test
    void testEmptyDirectoryAndThreeFilledSubDirectories() {
        FileManager fileManager = new FileManager();
        ArrayList<File> files = fileManager.listFilesUnderDirectory(new File("DirectoryTests/Case6"), filter);
        File f1 = new File("DirectoryTests/Case6/Sub1/Meme1.jpg");
        File f2 = new File("DirectoryTests/Case6/Sub2/Meme2.jpg");
        File f3 = new File("DirectoryTests/Case6/Sub3/Meme3.png");
        assertTrue(files.contains(f1));
        assertTrue(files.contains(f2));
        assertTrue(files.contains(f3));
    }

    @Test
    void testFilledDirectoryEmptySubDirectoryAndTwoFilledSubDirectories() {
        FileManager fileManager = new FileManager();
        ArrayList<File> files = fileManager.listFilesUnderDirectory(new File("DirectoryTests/Case7"), filter);
        File f1 = new File("DirectoryTests/Case7/Meme1.jpg");
        File f2 = new File("DirectoryTests/Case7/Sub1/Sub2/Meme2.jpg");
        File f3 = new File("DirectoryTests/Case7/Sub1/Sub2/Sub3/Meme3.png");
        assertTrue(files.contains(f1));
        assertTrue(files.contains(f2));
        assertTrue(files.contains(f3));
    }

    @Test
    void mixedDirectoryCase() {
        FileManager fileManager = new FileManager();
        ArrayList<File> files = fileManager.listFilesUnderDirectory(new File("DirectoryTests/Case8"), filter);
        File f1 = new File("DirectoryTests/Case8/Meme1.jpg");
        File f2 = new File("DirectoryTests/Case8/Sub1/Meme2.jpg");
        File f3 = new File("DirectoryTests/Case8/Sub2/Sub2:Sub2/Meme3.png");
        assertTrue(files.contains(f1));
        assertTrue(files.contains(f2));
        assertTrue(files.contains(f3));
    }


    @Test
    void testUnderOneSubDirectory() {
        FileManager fileManager = new FileManager();
        ArrayList<File> files = fileManager.listFilesUnderDirectory(new File("DirectoryTests/Case2"), filter);
        File f1 = new File("DirectoryTests/Case2/cath1.jpg");
        File f2 = new File("DirectoryTests/Case2/cath2.jpg");
        File f3 = new File("DirectoryTests/Case2/Case2Sub1/cath3.jpg");
        File f4 = new File("DirectoryTests/Case2/Case2Sub1/cath4.jpg");
        System.out.println();
        assertTrue(files.contains(f1));
        assertTrue(files.contains(f2));
        assertTrue(files.contains(f3));
        assertTrue(files.contains(f4));
    }

    @Test
    void testUnderOneSubDirectoryEmptyParent() {
        FileManager fileManager = new FileManager();
        ArrayList<File> files = fileManager.listFilesUnderDirectory(new File("DirectoryTests/Case3"), filter);
        File f1 = new File("DirectoryTests/Case3/Case3Sub1/cath3.jpg");
        File f2 = new File("DirectoryTests/Case3/Case3Sub1/cath4.jpg");
        assertTrue(files.contains(f1));
        assertTrue(files.contains(f2));
    }

    @Test
    void testUnderOneSubFullOneSubEmptyFullParent() {
        FileManager fileManager = new FileManager();
        ArrayList<File> files = fileManager.listFilesUnderDirectory(new File("DirectoryTests/Case4"), filter);
        File f1 = new File("DirectoryTests/Case4/cath1.jpg");
        File f2 = new File("DirectoryTests/Case4/cath2.jpg");
        File f3 = new File("DirectoryTests/Case4/Case4Sub2/cath4.jpg");
        File f4 = new File("DirectoryTests/Case4/Case4Sub2/cath5.jpg");
        assertTrue(files.contains(f1));
        assertTrue(files.contains(f2));
        assertTrue(files.contains(f3));
        assertTrue(files.contains(f4));
    }

}