package com.javarush.tikhonova.command;


public class Decode extends FilesWork {

    public Decode(String input, String output, int key) {
        super(input, output, key);
        writeInFile(input, output, key);
    }

    @Override
    void writeInFile(String input, String output, int key) {
        super.writeInFile(input, output, key * (-1));
    }
}
