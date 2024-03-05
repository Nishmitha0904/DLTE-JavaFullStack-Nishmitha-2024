package org.features.storage.fileprocessing;

import java.io.File;
import java.io.IOException;

public class Creation {
    public static void main(String[] args) {
        File file = new File("samplefile.txt");
        //File not yet created... only object created
        System.out.println(file.isFile());
        System.out.println(file.exists());
        //Creating file
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println();
            e.printStackTrace();
        }
    }
}
