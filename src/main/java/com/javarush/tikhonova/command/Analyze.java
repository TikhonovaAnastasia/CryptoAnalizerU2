package com.javarush.tikhonova.command;

import com.javarush.khmelov.util.PathBuilder;
import com.javarush.tikhonova.constant.Alphabet;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Analyze {
    public static final int[][] inputAnalyze = new int[Alphabet.alphabet.length][Alphabet.alphabet.length];

    public void readForAnalyze(String input){
        int count = 0;
        int indexY = 0;
        Path source = PathBuilder.get(input);
        try (BufferedReader bufferedReader = Files.newBufferedReader(source)){
            while (bufferedReader.ready()){
                char letter = (char) bufferedReader.read();
                if(Alphabet.alphabetMap.containsKey(letter)) {
                    count++;
                    if(count == 1){
                        indexY = Alphabet.alphabetMap.get(letter);
                    }else{
                        int indexX = Alphabet.alphabetMap.get(letter);
                        for (int i = 0; i < inputAnalyze.length; i++) {
                            if(i == indexY){
                                for (int j = 0; j < inputAnalyze[i].length; j++) {
                                    if(j == indexX){
                                        inputAnalyze[i][j]++;
                                        indexY = indexX;
                                    }
                                }
                            }
                        }
                    }


                }
            }
        }catch (IOException e) {
            System.out.println("Ошибка в чтении или записи файла, класс FilesWork");
        }
    }



}
