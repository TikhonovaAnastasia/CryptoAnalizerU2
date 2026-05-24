package com.javarush.tikhonova.controller;


import com.javarush.tikhonova.command.BruteForce;
import com.javarush.tikhonova.command.Decode;
import com.javarush.tikhonova.command.Encode;
import com.javarush.tikhonova.command.Validation;

public class MainController {
    Decode decode;
    Encode encode;
    BruteForce bruteForce;
    Validation validation = new Validation();
    public void decodeFile(String input, String output, int key){
        boolean boolKey = validation.testKey(key);
        boolean boolFile = validation.testFile(input);
       if(!boolKey){
           System.out.println("Ключ не подходит!");
       }
       else if(!boolFile){
           System.out.println("Такого файла нет!");
       }
       else {
           decode = new Decode(input, output, key);
       }
    }
    public void encodeFile(String input, String output, int key){
        boolean boolKey = validation.testKey(key);
        boolean boolFile = validation.testFile(input);
        if(!boolKey){
            System.out.println("Ключ не подходит!");
        }
        else if(!boolFile){
            System.out.println("Такого файла нет!");
        }
        else {
            encode = new Encode(input, output, key);
        }
    }
    public void bruteForceFile(String input, String output){
        boolean boolFile = validation.testFile(input);

        if(!boolFile){
            System.out.println("Такого файла нет!");
        }
        else {
            bruteForce = new BruteForce(input, output);
        }
    }
}
