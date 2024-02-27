package org.dynamic.generics;

import java.util.Arrays;

//Generic abstract class
public abstract class MyBankCrudActivity<T> {

    //generic array of objects
    protected T[] myObjects;

    //generic method with argument
    public abstract String insertNewRecord(T objects);
    public void viewAll() {
        System.out.println(Arrays.toString(myObjects));
    }
    //generic method with return type
    public abstract T read(int index);
    public abstract String delete(int index);
    public abstract void update(int index, T object);
}
