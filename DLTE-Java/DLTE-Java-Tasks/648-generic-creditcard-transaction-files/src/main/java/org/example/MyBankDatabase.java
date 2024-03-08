package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MyBankDatabase<T> implements Activity<T> {

    ArrayList<T> dataList;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyBankDatabase<CreditCard> creditCardMyBankDatabase = new MyBankDatabase<>();
        creditCardMyBankDatabase.dataList = new ArrayList<>(5);
        CreditCard creditCard1 = new CreditCard(6785649876564L,"Nishmitha Shetty",new Date(2036,9,23),787,50000,new Date(2024,4,15),new Date(2024,5,28),3223);
        CreditCard creditCard2 = new CreditCard(8765678765678L,"Sinchana",new Date(2034,12,30),565,100000,new Date(2024,3,11),new Date(2024,03,30),2316);
        CreditCard creditCard3 = new CreditCard(7654556987656L,"Shreya Poojary",new Date(2029,5,5),993,80000,new Date(2024,9,9),new Date(2024,10,28),2234);
        System.out.println(creditCardMyBankDatabase.create(creditCard1));
        System.out.println(creditCardMyBankDatabase.create(creditCard2));
        System.out.println(creditCardMyBankDatabase.create(creditCard3));
        creditCardMyBankDatabase.writeIntoFile();
        creditCardMyBankDatabase.readFromFile();

    }

    @Override
    public String create(T object) {
        int size = dataList.size();
        dataList.add(object);
        return dataList+" created.";

    }

    public void writeIntoFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("mybank-database.txt", true);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(dataList);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public void readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("mybank-database.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        dataList = (ArrayList<T>) objectInputStream.readObject();
        int size = dataList.size();
        for (int index=0;index<size;index++) {
            if (dataList.get(index)!=null) {
                System.out.println(dataList.get(index));
            }
        }
    }


}
