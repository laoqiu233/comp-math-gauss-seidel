package io.dmtri.options;

import io.dmtri.Configuration;
import io.dmtri.exceptions.OptionParsingException;
import io.dmtri.math.GaussSeidelSolver;

public class GaussSeidelOption extends AbstractOption {
    public GaussSeidelOption() {
        super(
                "gauss-seidel",
                'G',
                2,
                "[error-threshold] [max-iterations] - specify this option to use the Gauss-Seidel iterative method to solve, default method is " + Configuration.DEFAULT_SOLVER
        );
    }

    @Override
    public void execute(Configuration configuration, String[] arguments) throws OptionParsingException {
        try {
            double error = Double.parseDouble(arguments[0]);
            int maxIterations = Integer.parseInt(arguments[1]);

            configuration.setSolver(new GaussSeidelSolver(error, maxIterations));
        } catch (NumberFormatException e) {
            throw new OptionParsingException("Invalid parameter provided for Gauss-Seidel method\n" + e.getMessage());
        }
    }
}
