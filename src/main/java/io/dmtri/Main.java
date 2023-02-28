package io.dmtri;

import io.dmtri.datainputs.DataInput;
import io.dmtri.exceptions.LinearSystemSolvingException;
import io.dmtri.math.GaussSeidelSolver;
import io.dmtri.math.LinearSystem;
import io.dmtri.exceptions.DataInputException;
import io.dmtri.exceptions.OptionParsingException;
import io.dmtri.math.LinearSystemSolver;
import io.dmtri.math.Matrix;

public class Main {
    private static long start = 0;

    private static void timerStart() {
        start = System.currentTimeMillis();
    }

    private static void printTimeElapsed() {
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + " ms");
    }

    public static void main(String[] args) {
        Configuration config = new Configuration();
        try {
            OptionsParser.parse(args, config);
        } catch (OptionParsingException e) {
            System.err.println(e.getMessage());
            return;
        }

        try (DataInput input = config.getDataInput()) {
            Matrix a = input.getData(config.getHeight(), config.getWidth());
            Matrix b = input.getData(config.getHeight(), 1);
            LinearSystem system = new LinearSystem(a, b);

            if (config.getFlag(Configuration.DEBUG_FLAG)) {
                System.out.println("Matrix A:");
                System.out.println(a.formatMatrix(2));
                System.out.println("Matrix B:");
                System.out.println(b.formatMatrix(2));
            }

            timerStart();
            LinearSystemSolver solver = config.getSolver();
            Matrix x = solver.solve(system, config);
            // TODO: indexes, iterations
            System.out.println(x.formatMatrix(2));
            if (config.getFlag(Configuration.TIME_FLAG)) printTimeElapsed();

            System.out.println("Final mean error: " + system.getError(x));
        } catch (DataInputException e) {
            System.err.println("Failed to load data:\n" + e.getMessage());
            e.printStackTrace();
        } catch (LinearSystemSolvingException e) {
            System.err.println("An error occurred while trying to solve the system of linear equations:\n" + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unknown error:\n" + e.getMessage());
            e.printStackTrace();
        }
    }
}