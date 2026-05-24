package com.javarush.tikhonova.command;


public class Encode extends FilesWork {
    public Encode(String input, String output, int key) {
        super(input, output, key);
        writeInFile(input, output, key);
        System.out.println("Сompleted!");
    }

    @Override
    void writeInFile(String input, String output, int key) {
        super.writeInFile(input, output, key);
    }
}
