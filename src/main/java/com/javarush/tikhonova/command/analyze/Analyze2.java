package com.javarush.tikhonova.command.analyze;

import com.javarush.khmelov.util.PathBuilder;
import com.javarush.tikhonova.constant.Alphabet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Analyze2 implements Statistic2 {
    public  final int COUNT_TRY_FIND = 10;
    public Map<Character, Double> getBiGramStat(Path path) {
        Map<Character, Double> frequency = new HashMap<>();
        double[] lettersFrequency = new double[Alphabet.alphabet.length];
        int count = 0;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                char current = (char) reader.read();
                current = Character.toLowerCase(current);
                if (Alphabet.alphabetMap.containsKey(current)) {
                    int index = Alphabet.alphabetMap.get(current);
                    for (int i = 0; i < lettersFrequency.length; i++) {
                        if(i==index){
                            lettersFrequency[i]++;
                            count++;
                        }

                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error in method getBiGramStat");
        }
        for (int i = 0; i < lettersFrequency.length; i++) {
            frequency.put(Alphabet.alphabet[i], lettersFrequency[i]/count);
        }
        return frequency;
    }

    public double calcDistance(Map<Character, Double> original, Map<Character, Double> demo) {
        double destination = 0;
        if (original.size() == demo.size()
                && original.size() != 0
        ) {
            for (int i = 0; i < original.size(); i++) {

                    double delta = original.get(Alphabet.alphabet[i]) - demo.get(Alphabet.alphabet[i]);
                    destination += delta * delta;

            }
        } else {
            System.out.println("Error in method calcDistance");
        }
        return Math.sqrt(destination);
    }
    public void swap(Map<Character, Double> letters, int i, int j) {
        double row = letters.get(Alphabet.alphabet[i]);
        double row2 = letters.get(Alphabet.alphabet[j]);
        letters.put(Alphabet.alphabet[i], row2);
        letters.put(Alphabet.alphabet[j], row);
    }

    public double getCharsByRandomSwapper(char[] chars, Map<Character, Double> genom, Map<Character, Double> original) {
        int skipSwapCounter = 0;
        double bestProbeDistance = Double.MAX_VALUE;
        Map<Character, Double> genom2 = genom;

        while (skipSwapCounter < genom.size() * genom.size()) {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int i = random.nextInt(genom2.size());
            int j = random.nextInt(genom2.size());
            if (i != j) {
                swap(genom2, i, j);
                double distance = calcDistance(genom2, original);
                if (distance < bestProbeDistance) {
                    bestProbeDistance = distance;
                    skipSwapCounter = 0;
                    char ch = chars[j];
                    chars[j] = chars[i];
                    chars[i] = ch;
                } else {
                    swap(genom2, j, i); //revert
                    skipSwapCounter++;
                }
            }
        }
        return bestProbeDistance;
    }

    private List<Character> findBestVersionAlphabet(String encryptedFilename, String dictionaryFilename) {
        Map<Character, Double> matrix = getBiGramStat(PathBuilder.get(encryptedFilename));
        Map<Character, Double> original = getBiGramStat(PathBuilder.get(dictionaryFilename));
        double bestDistance = Double.MAX_VALUE;
        char[] bestChars = null;
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
    private List<Character> getCharacterList(char[] chars) {
        return String.valueOf(chars)
                .chars()
                .mapToObj(c -> (char) c)
                .toList();
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

}
