package com.javarush.tikhonova.controller;

import com.javarush.tikhonova.command.BruteForce;
import com.javarush.tikhonova.command.Decode;
import com.javarush.tikhonova.command.Encode;
import com.javarush.tikhonova.constant.Alphabet;


public class Main {
    public static void main(String[] args) {

        Encode encode = new Encode("text.txt", "rex.txt", 10);


        //Decode decode = new Decode("rex.txt", "rez.txt", 2);
        BruteForce bruteForce = new BruteForce("rex.txt", "rez.txt");

    }
}
