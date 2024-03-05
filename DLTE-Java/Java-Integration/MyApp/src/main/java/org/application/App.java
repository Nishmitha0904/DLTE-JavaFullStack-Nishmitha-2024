package org.application;

import org.layer.Basket;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
        Basket basket = new Basket();
        String[] items = basket.display();
        System.out.println(Arrays.toString(items));
    }
}
