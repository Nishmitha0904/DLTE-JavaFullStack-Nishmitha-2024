package org.features.storage.fileprocessing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadingFromFile {
    public static void main(String[] args) throws IOException {
        File file = new File("Debit.doc");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] current = new byte[fileInputStream.available()];
        fileInputStream.read(current);
        String collectedData = new String(current);
        System.out.println(collectedData);
        fileInputStream.close();
    }
}
