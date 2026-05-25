package com.javarush.tikhonova.command.analyze;

public class Main {
    public static void main(String[] args) {
        //Analyze2 analyze2 = new Analyze2();
        //analyze2.writeRezultAnalyze("rez.txt","text.txt",  "rez.txt");

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Analyze analyze = new Analyze();
        analyze.writeRezultAnalyze("dict.txt", "rez.txt", "analyze3.txt");
    }
}
