package com.javarush.tikhonova.сommand;


public class Encode extends FilesWork {
    public Encode(String input, String output, int key) {
        super(input, output, key);
        writeInFile(input, output, key);
    }

    @Override
    void writeInFile(String input, String output, int key) {
        super.writeInFile(input, output, key);
    }
}
