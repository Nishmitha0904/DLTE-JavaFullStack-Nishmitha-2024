package org.dynamic.framework;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IllustrationOfList {
    public static void main(String[] args) {
//        ArrayList arrayList = new ArrayList();  //generics
//        //ArrayList arrayList = new ArrayList(1);  //generics with initial capacity -> size can be increased regardless
//        arrayList.add(10); arrayList.add("Nishmitha Shetty");
//        System.out.println(arrayList);
//
//        ArrayList<String> stringArrayList = new ArrayList<>();
//        //stringArrayList.add(10);
//
//        String deposits1="Fixed"; String deposits2="RD";
//        //stringArrayList = (ArrayList<String>)Stream.of(deposits1, deposits2).collect(Collectors.toCollection());
//
//        Vector<String> stringVector = new Vector<>(stringArrayList);
//        for (String each:stringVector) {
//            System.out.println(each);
//        }
//
//        Iterator<String> stringIterator = stringVector.iterator();
//        while (stringIterator.hasNext()) {
//            System.out.println(stringIterator.next());
//        }
//
//        stringArrayList.forEach(System.out::println);
//        stringVector.forEach(item-> System.out.println(item));
//
//        for (int index=0;index<stringArrayList.size();index++) {
//            System.out.println(stringArrayList.get(index));
//        }
//
//        List<Integer> integerList = new ArrayList<>();
//        integerList.add(10);integerList.add(99);
//        System.out.println(integerList.get(1));

        ArrayList<Double> myPortfolio = new ArrayList<>();
        Vector<Double> myStocks = new Vector<>();

        myPortfolio.add(78.5); myPortfolio.add(746.22); myPortfolio.add(856.98);
        myPortfolio.forEach(System.out::println);
        Collections.sort(myPortfolio);
        myPortfolio.forEach(data-> System.out.println(data));

        myStocks.addAll(myPortfolio);
        myStocks.add(2, 555.3);
        myStocks.forEach(System.out::println);

        myPortfolio.set(1,12900.4);
        myPortfolio.add(78.5); myPortfolio.add(879.1);
        System.out.println("My Portfolio: "+myPortfolio);



        }
    }

