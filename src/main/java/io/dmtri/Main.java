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

            System.out.println(a.formatMatrix());
            System.out.println(b.formatMatrix());

            LinearSystemSolver solver = new GaussSeidelSolver(0.0001, 1000);
            Matrix x = solver.solve(system);

            System.out.println(x.formatMatrix());

        } catch (DataInputException e) {
            System.err.println("Failed to load data: " + e.getMessage());
            e.printStackTrace();
        } catch (LinearSystemSolvingException e) {
            System.err.println("An error occurred while trying to solve the system of linear equations: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.getMessage());
        }
    }
}