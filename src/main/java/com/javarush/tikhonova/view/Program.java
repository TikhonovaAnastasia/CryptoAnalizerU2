package com.javarush.tikhonova.view;

import com.javarush.tikhonova.command.Encode;
import com.javarush.tikhonova.command.analyze.Analyze;
import com.javarush.tikhonova.command.analyze.Analyze2;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.Objects;

public class Program {
    public static void main(String[] args) {
        System.out.println("Encode: Name file 1 = original fail; Name file 2 = the encoded file; key = shift");
        System.out.println("Decode: Name file 1 = the encoded file; Name file 2 = the file to decode; key = shift");
        System.out.println("Brute force: Name file 1 = the encoded file; Name file 2 = the file to decode");
        System.out.println("Brute force: Name file 1 = original fail; Name file 2 = the encoded file; Analyze file = the file to decode");
        new WindowProgram();

    }

}
