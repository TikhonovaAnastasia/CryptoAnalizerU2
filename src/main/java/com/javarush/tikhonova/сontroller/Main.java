package com.javarush.tikhonova.сontroller;

import com.javarush.tikhonova.сommand.Decode;
import com.javarush.tikhonova.сommand.Encode;


public class Main {
    public static void main(String[] args) {

        Encode encode = new Encode("text.txt", "rex.txt", 3);

        Decode decode = new Decode("rex.txt", "rez.txt", 3);


    }
}
