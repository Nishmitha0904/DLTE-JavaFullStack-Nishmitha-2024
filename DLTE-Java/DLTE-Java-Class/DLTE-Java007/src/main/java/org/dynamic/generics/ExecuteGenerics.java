package org.dynamic.generics;

public class ExecuteGenerics {
    public static void main(String[] args) {
        GenericAPI<Profile> profileGenericAPI = new GenericAPI<>();
        GenericAPI<DebitCard> debitCardGenericAPI = new GenericAPI<>();
        GenericAPI<Integer> integerGenericAPI = new GenericAPI<>();

        profileGenericAPI.myObjects = new Profile[3];

        Profile profile1 = new Profile();

    }
}
