package com.javarush.tikhonova.view;

public class Program {
    public static void main(String[] args) {
        System.out.println("Encode: Name file 1 = original fail; Name file 2 = the encoded file; key = shift");
        System.out.println("Decode: Name file 1 = the encoded file; Name file 2 = the file to decode; key = shift");
        System.out.println("Brute force: Name file 1 = the encoded file; Name file 2 = the file to decode");
        System.out.println("Brute force: Name file 1 = original fail; Name file 2 = the encoded file; Analyze file = the file to decode");
        new WindowProgram();

    }

}
