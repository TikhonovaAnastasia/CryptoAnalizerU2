package com.javarush.tikhonova.command;

import com.javarush.khmelov.util.PathBuilder;
import com.javarush.tikhonova.constant.Alphabet;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BruteForce extends FilesWork {

    public BruteForce(String input, String output) {
        super(input, output, 0);
        int bestKey = selectionKey(input);
        System.out.println("best key: " + bestKey);
        System.out.println("Сompleted!");
        writeInFile(input, output, bestKey);
    }

    public int selectionKey(String input) {
        char frequentlyUsedSymbol = ' ';
        int countFrequentlyUsedSymbol = 0;
        int key = 0;
        for (int i = 0; i < Alphabet.alphabet.length; i++) {
            int receivedCountSymbol = countingSpaces(input, i, frequentlyUsedSymbol);
            if (receivedCountSymbol > countFrequentlyUsedSymbol) {
                countFrequentlyUsedSymbol = receivedCountSymbol;
                key = i;
            }
        }
        return key;
    }

    public int countingSpaces(String input, int key, char frequentlyUsed) {
        Path source = PathBuilder.get(input);
        int count = 0;
        try (BufferedReader bufferedReader = Files.newBufferedReader(source)) {
            while (bufferedReader.ready()) {
                char letter = (char) bufferedReader.read();
                int indexInAlphabet = (Alphabet.alphabetMap.get(letter) + key + Alphabet.alphabet.length) % Alphabet.alphabet.length;
                if (Alphabet.alphabet[indexInAlphabet] == frequentlyUsed) {
                    count++;
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка в чтении файла, класс BruteForce");
        }
        return count;
    }

    @Override
    void writeInFile(String input, String output, int key) {
        super.writeInFile(input, output, key);
    }
}
