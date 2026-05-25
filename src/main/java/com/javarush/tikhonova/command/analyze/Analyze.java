package com.javarush.tikhonova.command.analyze;

import com.javarush.khmelov.util.PathBuilder;
import com.javarush.tikhonova.constant.Alphabet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Analyze implements Statistic{
/*
NOT USED
 */
    public  final int COUNT_TRY_FIND = 10;

    public double[][] getBiGramStat(Path path) {
        int length = Alphabet.alphabet.length;
        double[][] biGramStat = new double[length][length];
        char prefix = '\u0000';
        int value;
        long pairCount = 0;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (-1 != (value = reader.read())) {
                char current = (char) value;
                Integer indexPrefix = Alphabet.alphabetMap.get(prefix);
                Integer indexCurrent = Alphabet.alphabetMap.get(current);
                if (indexPrefix != null && indexCurrent != null) {
                    biGramStat[indexPrefix][indexCurrent]++;
                    pairCount++;
                }
                prefix = current;
            }
        } catch (IOException e) {
            System.out.println("Error in method getBiGramStat");
        }
        for (int i = 0; i < biGramStat.length; i++) {
            for (int j = 0; j < biGramStat[i].length; j++) {
                biGramStat[i][j] /= pairCount;
            }
        }
        return biGramStat;
    }


    public double calcDistance(double[][] firstMatrix, double[][] secondMatrix) {
        double destination = 0;
        if (firstMatrix.length == secondMatrix.length
                && firstMatrix.length != 0
                && firstMatrix[0].length == secondMatrix[0].length
                && firstMatrix[0].length != 0
        ) {
            for (int i = 0; i < firstMatrix.length; i++) {
                for (int j = 0; j < firstMatrix[i].length; j++) {
                    double delta = firstMatrix[i][j] - secondMatrix[i][j];
                    destination += delta * delta;
                }
            }
        } else {
            System.out.println("Error in method calcDistance");
        }
        return Math.sqrt(destination);
    }

    public void swap(double[][] matrix, int i, int j) {
        //swap rows
        double[] row = matrix[i];
        matrix[i] = matrix[j];
        matrix[j] = row;
        //swap cols
        for (int k = 0; k < matrix[i].length; k++) {
            double value = matrix[k][i];
            matrix[k][i] = matrix[k][j];
            matrix[k][j] = value;
        }
    }

    public double getCharsByRandomSwapper(char[] chars, double[][] genom, double[][] original) {
        int skipSwapCounter = 0;
        double bestProbeDistance = Double.MAX_VALUE;
        genom = genom.clone();
        for (int i = 0; i < genom.length; i++) {
            genom[i] = genom[i].clone();
        }
        while (skipSwapCounter < genom.length * genom.length) {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int i = random.nextInt(genom.length);
            int j = random.nextInt(genom.length);
            if (i != j) {
                swap(genom, i, j);
                double distance = calcDistance(genom, original);
                if (distance < bestProbeDistance) {
                    bestProbeDistance = distance;
                    skipSwapCounter = 0;
                    char ch = chars[j];
                    chars[j] = chars[i];
                    chars[i] = ch;
                } else {
                    swap(genom, j, i); //revert
                    skipSwapCounter++;
                }
            }
        }
        return bestProbeDistance;
    }

    private List<Character> findBestVersionAlphabet(String encryptedFilename, String dictionaryFilename) {
        double[][] matrix = getBiGramStat(PathBuilder.get(encryptedFilename));
        double[][] original = getBiGramStat(PathBuilder.get(dictionaryFilename));
        double bestDistance = Double.MAX_VALUE;
        char[] bestChars = null;
        System.out.println("\nAnalyze");
        for (int i = COUNT_TRY_FIND; i > 0; i--) {
            char[] chars = Alphabet.alphabet.clone();
            double probeDistance = getCharsByRandomSwapper(chars, matrix, original);
            if (probeDistance < bestDistance) {
                i += COUNT_TRY_FIND;
                bestDistance = probeDistance;
                bestChars = chars.clone();
                //For debug only, here System.out.println - not the best solution. Here need the logger
                System.out.println("Best distance = " + bestDistance);
            }
        }
        return getCharacterList(bestChars);
    }

public void writeRezultAnalyze(String encryptedFilename, String dictionaryFilename, String analyzedFilename) {
    List<Character> dictChar = getCharacterList(Alphabet.alphabet);
    List<Character> sourceChar = findBestVersionAlphabet(encryptedFilename, dictionaryFilename);

    Path source = PathBuilder.get(encryptedFilename);
    Path target = PathBuilder.get(analyzedFilename);
    try (
            BufferedReader reader = Files.newBufferedReader(source);
            BufferedWriter writer = Files.newBufferedWriter(target)
    ) {
        int value;
        while ((value = reader.read()) > -1) {
            char character = (char) value;
            int index = sourceChar.indexOf(character);
            Character characterDecrypted = dictChar.get(index);
            writer.write(
                    characterDecrypted != null
                            ? characterDecrypted
                            : character);
        }
    } catch (IOException e) {
        System.out.println("Error in method writeRezultAnalyze");
    }
}

    private List<Character> getCharacterList(char[] chars) {
        return String.valueOf(chars)
                .chars()
                .mapToObj(c -> (char) c)
                .toList();
    }

}
