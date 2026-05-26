package com.javarush.tikhonova.command.analyze;

import java.nio.file.Path;
import java.util.Map;

public interface Statistic2 {
    int COUNT_TRY_FIND = 10;
    Map<Character, Double> getBiGramStat(Path path);
    double calcDistance(Map<Character, Double> original, Map<Character, Double> dem);
    void swap(Map<Character, Double> letters, int i, int j);
    double getCharsByRandomSwapper(char[] chars, Map<Character, Double> genom, Map<Character, Double> original);
}
