package org.example;

public interface Activity<T> {



    public String insertNewRecord(T objects);
    public void viewAll();
    public T read(int index);
    public abstract String delete(int index);
    public abstract void update(int index, T object);
}
