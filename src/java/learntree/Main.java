package src.java.learntree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String csvFile = "../../../mini-project/mnist_test.csv";
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

    }

}