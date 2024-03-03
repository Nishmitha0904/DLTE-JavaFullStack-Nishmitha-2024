package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

public class IllustrationOfLists {
    public static void main(String[] args) {

        //ArrayList
        //Non-generics
        ArrayList arrayList = new ArrayList();
        arrayList.add(12);
        arrayList.add("Nishmitha");
        System.out.println(arrayList);
        //generics
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(9); integerArrayList.add(4);
        integerArrayList.add(3); integerArrayList.add(1);
        System.out.println(integerArrayList);
        Collections.sort(integerArrayList);
        integerArrayList.forEach(data-> System.out.print(data+" "));
        System.out.println();
        integerArrayList.set(1, 5);
        System.out.println(integerArrayList);

        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("Hello");
        stringArrayList.add("World");
        System.out.println(stringArrayList);

        //Vector
        Vector<Integer> integerVector = new Vector<>(integerArrayList);
        for (Integer each:integerVector) {
            System.out.print(each+" ");
        }
        System.out.println();
//        Iterator<Integer> integerIterator = integerVector.iterator();
//        while (integerIterator.hasNext()) {
//            System.out.println(integerIterator.next());
//        }
        System.out.println(integerVector.contains(9));

        System.out.println(Collections.max(integerArrayList));
        System.out.println(Collections.min(integerVector));


    }
}
