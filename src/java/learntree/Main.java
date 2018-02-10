package src.java.learntree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String csvFile = "mnist_test.csv";
        String line = "";
        String cvsSplitBy = ",";

        int i = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                Example example = new Example(line.split(cvsSplitBy));

                System.out.println("label" + i + ": "  + example.getLabel() + ", pixel 100: " + example.getPixle(3, 15));

                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.out.println("args[0]: " + args[0]);
        // System.out.println("args[1]: " + args[1]);
        // System.out.println("args[2]: " + args[2]);
    }

}