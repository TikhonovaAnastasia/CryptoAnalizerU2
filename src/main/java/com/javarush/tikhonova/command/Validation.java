package com.javarush.tikhonova.command;

import com.javarush.tikhonova.constant.Alphabet;

import java.io.File;
import java.nio.file.Files;

public class Validation {
    public boolean testKey(int key) {
        boolean test = true;
        if (key < 0 || key > Alphabet.alphabet.length) {
            test = false;
        }
        return test;
    }

    public boolean testFile(String fileName) {
        boolean test = true;
        File file = new File(fileName);
        if (file.exists()) {
            test = true;
        } else {
            test = false;
        }
        return test;
    }
}
