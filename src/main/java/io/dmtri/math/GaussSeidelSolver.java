package io.dmtri.math;

import io.dmtri.exceptions.LinearSystemSolvingException;

public class GaussSeidelSolver implements LinearSystemSolver {
    private final double error;
    private final int maxIterations;

    public GaussSeidelSolver(double error, int maxIterations) {
        this.error = error;
        this.maxIterations = maxIterations;
    }

    @Override
    public Matrix solve(LinearSystem system) throws LinearSystemSolvingException {
        Matrix a = system.a();
        Matrix b = system.b();

        if (a.getWidth() != a.getHeight()) throw new LinearSystemSolvingException("Matrix A of the system is not square. Got " + a);
        if (a.getHeight() != b.getHeight()) throw new LinearSystemSolvingException("Matrix A does not have the same height as matrix B, got A: " + a + ", B: " + b);

        Matrix x = new Matrix(a.getHeight(), 1);
        int iterations = 0;

        while (iterations++ < maxIterations && system.getError(x) > error) {
            System.err.println("Iteration No." + iterations + " error = " + system.getError(x));
            for (int i = 0; i < a.getHeight(); i++) {
                double s = b.get(i, 0);

                // Get right side sum
                for (int j = 0; j < a.getWidth(); j++) {
                    // Skip diagonal elements
                    if (i == j) continue;

                    s -= x.get(j, 0) * a.get(i, j);
                }

                x.set(i, 0, s / a.get(i, i));
            }
        }

        return x;
    }

    @Override
    public String toString() {
        return "Gauss-Seidel method using a maximum of " + maxIterations + " iterations and an error of " + error;
    }
}
