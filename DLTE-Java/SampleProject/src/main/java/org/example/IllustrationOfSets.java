package org.example;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class IllustrationOfSets {
    public static void main(String[] args) {
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(71); linkedHashSet.add(109); linkedHashSet.add(94);
        linkedHashSet.add(89); linkedHashSet.add(112); linkedHashSet.add(113);


        HashSet<Integer> hashSet = new HashSet<>(linkedHashSet);
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.addAll(linkedHashSet);

        System.out.println("Linked Hash Set "+linkedHashSet);
        System.out.println("Hash Set "+hashSet);
        System.out.println("Tree Set "+treeSet);
    }
}
