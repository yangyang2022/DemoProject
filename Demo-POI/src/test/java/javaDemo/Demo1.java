package javaDemo;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Demo1 {
    private static String filePath = "C:\\code\\java\\users.txt";
    private static String baseDir = "C:\\code\\java\\";

    private static<T> void printList(Collection<T> coll){
        coll.forEach(System.out::println);
    }
    private static<K,V> void printMap(Map<K,V> maps){
        maps.forEach((k,v)-> System.out.println(k + " : " + v));
    }
    @Test
    public void testDemo1() {
        Path path = FileSystems.getDefault().getPath(filePath);

        System.out.println(path.toString());
        System.out.printf("getFileName: %s\n", path.getFileName());
        System.out.printf("getRoot: %s\n", path.getRoot());
        System.out.printf("getNameCount: %d\n", path.getNameCount());
        for(int index=0; index<path.getNameCount(); index++) {
            System.out.printf("getName(%d): %s\n", index, path.
                    getName(index));
        }
        System.out.printf("subpath(0,2): %s\n", path.subpath(0, 2));
        System.out.printf("getParent: %s\n", path.getParent());
        System.out.println(path.isAbsolute());
    }

    @Test
    public void testDemo2() throws IOException {

        Path path = Paths.get(filePath);
        System.out.println(path.toAbsolutePath());
        path = Paths.get(filePath);
        File file = path.toFile();
        System.out.println(file.exists());

        List<String> files = Files.readAllLines(path);
        printList(files);

    }

    private static void displayContentType(String filePath){
        try {
            System.out.println(Files.probeContentType(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDemo3() {
        String path1 = "D:\\Program Files (x86)\\JetBrains\\CLion 2016.1.2\\bin\\clion64.exe";
        String path2 = "C:\\Users\\syy\\Desktop\\开学\\选课\\课表9-10.xls";
        String path3 = "D:\\workLib\\book\\temp\\read-will\\Java 7 New Features Cookbook.pdf";

        displayContentType(filePath);
        displayContentType(path1);
        displayContentType(path2);
        displayContentType(path3);

    }

    @Test
    public void testDemo4() throws IOException {
        Path path = Paths.get(filePath);
        System.out.println(Files.getAttribute(path, "size"));

        FileTime time = Files.getLastModifiedTime(path);
        Map<String,Object> maps = Files.readAttributes(path,"*");
        printMap(maps);

    }

    @Test
    public void testDemo5() throws IOException {
        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);
        for(byte b : bytes){
            System.out.print((char) b);
        }
        Path newPath = Paths.get(baseDir + "newUser.txt");
        byte[] newContent = "hello world".getBytes();

        Files.write(newPath,bytes, StandardOpenOption.CREATE);
        Files.write(newPath,newContent, StandardOpenOption.APPEND);
    }

    @Test
    public void testDemo6() {
        String newName = "xuming";
        Path path = Paths.get(filePath);
        try(BufferedWriter writer = Files.newBufferedWriter(path, Charset.defaultCharset(),StandardOpenOption.APPEND)){

            writer.newLine();
            writer.write(newName,0,newName.length());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testDemo7() {
        int byteSize = 8;
        Path path = Paths.get(filePath);
        try (SeekableByteChannel sbc = Files.newByteChannel(path)){
            ByteBuffer buffer = ByteBuffer.allocate(byteSize);

            sbc.position(4);
            sbc.read(buffer);
            for (int i = 0; i < 5; ++i) {
                System.out.print((char) buffer.get(i));
            }
            System.out.println();
            buffer.clear();
            sbc.position(0);
            sbc.read(buffer);
            for (int i = 0; i < 5; ++i) {
                System.out.print((char) buffer.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDemo8() {
        LocalDate date = LocalDate.of(2015,9,3);
        System.out.println(date.isLeapYear());
    }
}
