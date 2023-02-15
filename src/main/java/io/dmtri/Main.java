package io.dmtri;

import io.dmtri.exceptions.DataInputException;
import io.dmtri.exceptions.OptionParsingException;

public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        try {
            OptionsParser.parse(args, config);
        } catch (OptionParsingException e) {
            System.out.println("Failed to parse arguments:");
            System.out.println(e.getMessage());
        }

        try {
            Matrix matrix = config.getDataFromConfiguration();

            for (int i = 0; i < matrix.getHeight(); i++) {
                for (int j = 0; j < matrix.getWidth(); j++) {
                    System.out.print(Math.round(matrix.get(i, j) * 100) / 100.0d + "\t\t\t");
                }
                System.out.println();
            }
        } catch (DataInputException e) {
            System.out.println("Failed to load data" + e.getMessage());
            e.printStackTrace();
        }
    }
}