package org.dynamic.framework;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class IllustrationSets {
    public static void main(String[] args) {
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>(1);
        linkedHashSet.add(98);linkedHashSet.add(232);linkedHashSet.add(112);
        linkedHashSet.add(78);linkedHashSet.add(98);linkedHashSet.add(34);

        HashSet<Integer> hashSet = new HashSet<>(linkedHashSet);

        linkedHashSet.forEach(System.out::println);

    }
}
