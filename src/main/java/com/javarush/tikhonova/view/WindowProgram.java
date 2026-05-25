package com.javarush.tikhonova.view;

import com.javarush.tikhonova.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowProgram extends JFrame implements ActionListener {
    MainController mainController = new MainController();
    private Container c;
    private JLabel title;
    private JLabel nameFile1;
    private JTextField tnameFile1;
    private JLabel nameFile2;
    private JTextField tnameFile2;
    private JLabel nameFile3;
    private JTextField tnameFile3;
    private JRadioButton male;
    private JRadioButton female;
    private ButtonGroup gengp;
    private JLabel dob;
    private JComboBox key;
    private JLabel add;
    private JTextArea tadd;
    private JButton encode;
    private JButton decode;
    private JButton brute;
    private JButton analize;

    private String keys[]
            = {"1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31", "32", "33", "34", "35",
            "36", "37", "38", "39", "40",
            "41", "42", "43", "44"};

    WindowProgram() {
        setTitle("Code");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Code");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        c.add(title);

        nameFile1 = new JLabel("Name file 1");
        nameFile1.setFont(new Font("Arial", Font.PLAIN, 20));
        nameFile1.setSize(100, 20);
        nameFile1.setLocation(100, 100);
        c.add(nameFile1);

        tnameFile1 = new JTextField("C:\\Users\\Anastasia\\Desktop\\Java\\Experiment2GavaFX\\CryptoAnalizerU2\\text\\text.txt");
        tnameFile1.setFont(new Font("Arial", Font.PLAIN, 15));
        tnameFile1.setSize(560, 20);
        tnameFile1.setLocation(210, 100);
        c.add(tnameFile1);

        nameFile2 = new JLabel("Name file 2");
        nameFile2.setFont(new Font("Arial", Font.PLAIN, 20));
        nameFile2.setSize(100, 20);
        nameFile2.setLocation(100, 150);
        c.add(nameFile2);

        tnameFile2 = new JTextField("C:\\Users\\Anastasia\\Desktop\\Java\\Experiment2GavaFX\\CryptoAnalizerU2\\text\\rez.txt");
        tnameFile2.setFont(new Font("Arial", Font.PLAIN, 15));
        tnameFile2.setSize(560, 20);
        tnameFile2.setLocation(210, 150);
        c.add(tnameFile2);

        nameFile3 = new JLabel("Analyze file");
        nameFile3.setFont(new Font("Arial", Font.PLAIN, 20));
        nameFile3.setSize(100, 20);
        nameFile3.setLocation(100, 200);
        c.add(nameFile3);

        tnameFile3 = new JTextField("C:\\Users\\Anastasia\\Desktop\\Java\\Experiment2GavaFX\\CryptoAnalizerU2\\text\\analyze3.txt");
        tnameFile3.setFont(new Font("Arial", Font.PLAIN, 15));
        tnameFile3.setSize(560, 20);
        tnameFile3.setLocation(210, 200);
        c.add(tnameFile3);


        gengp = new ButtonGroup();
        gengp.add(male);
        gengp.add(female);

        dob = new JLabel("Keys");
        dob.setFont(new Font("Arial", Font.PLAIN, 20));
        dob.setSize(100, 20);
        dob.setLocation(100, 250);
        c.add(dob);

        key = new JComboBox(keys);
        key.setFont(new Font("Arial", Font.PLAIN, 15));
        key.setSize(50, 20);
        key.setLocation(210, 250);
        c.add(key);


        encode = new JButton("Encode");
        encode.setFont(new Font("Arial", Font.PLAIN, 15));
        encode.setSize(100, 20);
        encode.setLocation(100, 350);
        encode.addActionListener(this);
        c.add(encode);

        decode = new JButton("Decode");
        decode.setFont(new Font("Arial", Font.PLAIN, 15));
        decode.setSize(100, 20);
        decode.setLocation(100, 390);
        decode.addActionListener(this);
        c.add(decode);

        brute = new JButton("Brute Force");
        brute.setFont(new Font("Arial", Font.PLAIN, 15));
        brute.setSize(100, 20);
        brute.setLocation(100, 430);
        brute.addActionListener(this);
        c.add(brute);

        analize = new JButton("Analyze");
        analize.setFont(new Font("Arial", Font.PLAIN, 15));
        analize.setSize(100, 20);
        analize.setLocation(100, 470);
        analize.addActionListener(this);
        c.add(analize);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == encode) {
            String file1 = tnameFile1.getText();
            String file2 = tnameFile2.getText();
            int k = Integer.parseInt((String) key.getSelectedItem());
            mainController.encodeFile(file1, file2, k);
        } else if (e.getSource() == decode) {
            mainController.decodeFile(tnameFile1.getText(), tnameFile2.getText(), Integer.parseInt((String) key.getSelectedItem()));
        } else if (e.getSource() == brute) {
            mainController.bruteForceFile(tnameFile1.getText(), tnameFile2.getText());
        } else if (e.getSource() == analize) {
            mainController.analyze(tnameFile1.getText(), tnameFile2.getText(), tnameFile3.getText());
        }

    }
}
