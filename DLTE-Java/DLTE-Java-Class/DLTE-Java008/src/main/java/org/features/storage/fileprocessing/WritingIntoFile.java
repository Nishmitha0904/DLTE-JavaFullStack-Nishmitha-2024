package org.features.storage.fileprocessing;

import org.features.storage.DebitCard;

import java.io.*;
import java.util.Date;

public class WritingIntoFile {
    public static void main(String[] args) throws IOException {
        File file = new File("Debit.doc");
        FileOutputStream fileOutputStream = new FileOutputStream(file,true); //FileNotFoundException
//        DataInputStream dataInputStream = new DataInputStream(System.in);
//        String myContent = dataInputStream.readLine(); //IOException
        DebitCard debitCard = new DebitCard(397583759375L,111,3434,new Date("2/11/2030"));
        fileOutputStream.write(debitCard.toString().getBytes());
        fileOutputStream.close();
    }
}
