package image.filenamestrategy;

import image.ImageFile;
import org.junit.jupiter.api.Test;
import utils.FileManager;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileNameStrategyTest {

    @Test
    void testFileLocation1() {
        String fileName = "mock1/test.jpg";
        ImageFile testFile = new ImageFile(new File(fileName));
        String location = new FileLocation().getName(testFile);
        assertTrue(location.equals("mock1"));
    }

    @Test
    void testFileLocation2() {
        String fileName = "mock1/mock2/test.jpg";
        ImageFile testFile = new ImageFile(new File(fileName));
        String location = new FileLocation().getName(testFile);
        assertTrue(location.equals("mock1/mock2"));
    }

    @Test
    void testFileLocation3() {
        String fileName = "mock1/mock2/mock3/test.jpg";
        ImageFile testFile = new ImageFile(new File(fileName));
        String location = new FileLocation().getName(testFile);
        assertTrue(location.equals("mock1/mock2/mock3"));
    }

    @Test
    void testOneFileName() {
        String fileName = "mock1/mock2/mock3/test.jpg";
        ImageFile testFile = new ImageFile(new File(fileName));
        String name = new FileName(true).getName(testFile);
        assertTrue(name.equals("test.jpg"));
    }

    @Test
    void testTwoFileName() {
        String fileName = "mock1/mock2/mock3/test @group_0577.jpg";
        ImageFile testFile = new ImageFile(new File(fileName));
        String name = new FileName(false).getName(testFile);
        System.out.println(name);
        assertTrue(name.equals("test @group_0577"));
    }

    @Test
    void testThreeFileNameWithoutExtension() {
        String fileName = "mock1/mock2/mock3/test @group_0577 @CSC207.jpg";
        ImageFile testFile = new ImageFile(new File(fileName));
        String name = new FileName(true).getName(testFile);
        assertTrue(name.equals("test @group_0577 @CSC207.jpg"));
    }

    @Test
    void testUntaggedNameWithExtension() {
        String fileName = "mock1/mock2/mock3/test @group_0577 @CSC207.jpg";
        ImageFile testFile = new ImageFile(new File(fileName));
        String name = new FileUntaggedName(true).getName(testFile);
        assertTrue(name.equals("test.jpg"));
    }

    @Test
    void testUntaggedNameWithoutExtension() {
        String fileName = "mock1/mock2/mock3/test @group_0577 @CSC207 @Aleks @Jonathan.jpg";
        ImageFile testFile = new ImageFile(new File(fileName));
        String name = new FileUntaggedName(false).getName(testFile);
        assertTrue(name.equals("test"));

    }

    @Test
    void testFileExtensionJPG() {
        String fileName = "/mock1/mock2/test.jpg";
        String fileName2 = "/mock1/mock2/test.JPG";
        ImageFile testFile = new ImageFile(new File(fileName));
        String extension = new FileExtension().getName(testFile);
        ImageFile testFile2 = new ImageFile(new File(fileName2));
        String extension2 = new FileExtension().getName(testFile2);
        assertTrue(extension.equals(".jpg"));
        assertTrue(extension2.equals(".JPG"));
    }

    @Test
    void testFileExtensionGIF() {
        String fileName = "/mock1/test.gif";
        ImageFile testFile = new ImageFile(new File(fileName));
        String extension = new FileExtension().getName(testFile);
        assertTrue(extension.equals(".gif"));
    }

    @Test
    void testFileExtensionPNG() {
        String fileName = "mock2/test a.png";
        ImageFile testFile = new ImageFile(new File(fileName));
        String extension = new FileExtension().getName(testFile);
        assertTrue(extension.equals(".png"));
    }

    @Test
    void testFileExtensionJPEG() {
        String fileName = "/mock1/mock2/test.jpeg";
        ImageFile testFile = new ImageFile(new File(fileName));
        String extension = new FileExtension().getName(testFile);
        assertTrue(extension.equals(".jpeg"));
    }

    @Test
    void testFileExtensionBMP() {
        String fileName = "/mock1/mock2/test__.bmp";
        ImageFile testFile = new ImageFile(new File(fileName));
        String extension = new FileExtension().getName(testFile);
        assertTrue(extension.equals(".bmp"));
    }

    @Test
    void testFileTagsNoTags() {
        String fileName = "/mock1/mock2/test.jpg";
        ImageFile testFile = new ImageFile(new File(fileName));
        String tags = new FileTags(true).getName(testFile);
        assertTrue(tags.equals(""));
    }

    @Test
    void testFileTagsOneTagWithoutLabel() {
        String fileName = "/mock1/mock2/test @tag1.jpg";
        ImageFile testFile = new ImageFile(new File(fileName));
        String tags = new FileTags(false).getName(testFile);
        assertTrue(tags.equals("tag1 "));
    }

    @Test
    void testFileTagsMultipleTagsWithoutLabel() {
        String fileName = "/mock1/mock2/test @tag1 @tag2 @tag3 @tag4.jpg";
        ImageFile testFile = new ImageFile(new File(fileName));
        String tags = new FileTags(false).getName(testFile);
        System.out.println(tags);
        assertTrue(tags.equals("tag1 tag2 tag3 tag4 "));
    }

    @Test
    void testFileTagsOneTagWithLabel() {
        String fileName = "/mock1/mock2/test @tag1.jpg";
        ImageFile testFile = new ImageFile(new File(fileName));
        String tags = new FileTags(true).getName(testFile);
        assertTrue(tags.equals("@tag1 "));
    }

    @Test
    void testFileTagsMultipleTagsWithLabel() {
        String fileName = "/mock1/mock2/test @tag1 @tag2 @tag3 @tag4.jpg";
        ImageFile testFile = new ImageFile(new File(fileName));
        String tags = new FileTags(true).getName(testFile);
        System.out.println(tags);
        assertTrue(tags.equals("@tag1 @tag2 @tag3 @tag4 "));
    }

}