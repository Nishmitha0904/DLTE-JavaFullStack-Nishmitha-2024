package org.features.storage.fileprocessing;

import org.features.storage.DebitCard;

import java.io.*;
import java.util.Date;

public class WritingObjectsIntoFile  {
    public static void main(String[] args) throws IOException {
        File file = new File("debits.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        DebitCard debitCard = new DebitCard(397583759375L,111,3434,new Date("2/11/2030"));
        objectOutputStream.writeObject(debitCard);
        objectOutputStream.close();
        fileOutputStream.close();
    }
}
