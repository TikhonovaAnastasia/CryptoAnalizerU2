package com.javarush.tikhonova.command.analyze;

import java.nio.file.Path;
import java.util.Map;

public interface Statistic {
    /*
        NOT USED
 */
    double[][] getBiGramStat(Path path);
    double calcDistance(double[][] firstMatrix, double[][] secondMatrix);
    void swap(double[][] matrix, int i, int j);
    double getCharsByRandomSwapper(char[] chars, double[][] genom, double[][] original);
}
