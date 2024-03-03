package org.example;

import java.util.*;

public class IllustrationOfMaps {
    public static void main(String[] args) {
        Hashtable<String, Double> myAsset = new Hashtable<>();
        myAsset.put("Wipro",2500.5); myAsset.put("Gold",223.5);
        myAsset.put("Wipro",670.4); myAsset.put("JSW",893.4);
        System.out.println(myAsset);
        TreeMap<String, Double> treeMap = new TreeMap<>();
        treeMap.putAll(myAsset);
        System.out.println(treeMap);

        Set<String> myOrgs = myAsset.keySet();
        System.out.println(myOrgs);
        Collection<Double> prices = treeMap.values();
        System.out.println(prices);

        myAsset.remove("Wipro");
        System.out.println("myAsset after removing Wipro: "+myAsset);

        treeMap.replace("JSW", 1900.4);
        System.out.println("TreeMap: "+treeMap);

        System.out.println(treeMap.containsKey("Wipro"));
        System.out.println(myAsset.containsValue(1900.4));
    }
}
