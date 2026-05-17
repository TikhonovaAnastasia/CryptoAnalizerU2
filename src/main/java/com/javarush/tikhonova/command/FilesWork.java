package com.javarush.tikhonova.command;

import com.javarush.khmelov.util.PathBuilder;
import com.javarush.tikhonova.constant.Alphabet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class FilesWork {

    public FilesWork(String input, String output, int key) {
        writeInFile(input, output, key);
    }

    void writeInFile(String input, String output, int key) {
        Path source = PathBuilder.get(input);
        Path target = PathBuilder.get(output);
        try (BufferedReader bufferedReader = Files.newBufferedReader(source);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(target)) {
            while (bufferedReader.ready()) {
                char letter = (char) bufferedReader.read();
                letter = Character.toLowerCase(letter);
                if (Alphabet.alphabetMap.containsKey(letter)) {
                    int index = Alphabet.alphabetMap.get(letter);
                    if (index + key >= Alphabet.alphabet.length) {
                        int gap = Alphabet.alphabet.length - (index + 1);
                        index = key - gap;
                        char newLetter = Alphabet.alphabet[index - 1];
                        bufferedWriter.write(newLetter);
                    } else if (key < 0 && (index + 1) + key <= 0) {
                        char newLetter = Alphabet.alphabet[Alphabet.alphabet.length + ((index + 1) + key) - 1];
                        bufferedWriter.write(newLetter);
                    } else {
                        char newLetter = Alphabet.alphabet[index + key];
                        bufferedWriter.write(newLetter);
                    }
                } else {
                    bufferedWriter.write("");
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка в чтении или записи файла, класс FilesWork");
        }
    }

    // читать символы и передавать их
    //проверять, не дошли ли до конца, вернет true/false

}
