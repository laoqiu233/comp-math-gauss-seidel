package io.dmtri.math;

import io.dmtri.Configuration;
import io.dmtri.exceptions.LinearSystemSolvingException;

public class GaussSeidelSolver implements IterativeLinearSystemSolver {
    private final double eps;
    private final int maxIterations;
    private int iterations = 0;

    public GaussSeidelSolver(double eps, int maxIterations) {
        this.eps = eps;
        this.maxIterations = maxIterations;
    }

    private int getDominantElementInRow(Matrix m, int row) {
        double sum = 0;
        for (int i = 0; i < m.getWidth(); i++) sum += Math.abs(m.get(row, i));

        for (int i = 0; i < m.getWidth(); i++) {
            if (sum <= 2 * Math.abs(m.get(row, i))) return i;
        }

        return -1;
    }

    /**
     * Tries to transform a given system of linear equations into a diagonally dominant state
     * @param system - the system of linear equations
     * @return whether the operation was successful
     */
    private boolean makeDiagonallyDominant(LinearSystem system) {
        // Selection sort lol
        for (int i = 0; i < system.a().getHeight(); i++) {
            int iDom = getDominantElementInRow(system.a(), i);
            if (iDom < 0) return false;

            int minRow = i;
            int minCol = iDom;

            for (int j = i + 1; j < system.a().getHeight(); j++) {
                int jDom = getDominantElementInRow(system.a(), j);
                if (jDom < 0) return false;

                if (minCol > jDom) {
                    minRow = j;
                    minCol = jDom;
                }
            }

            // Check if the element is on the diagonal
            if (minCol != i) return false;

            system.a().swapRows(i, minRow);
            system.b().swapRows(i, minRow);
        }

        return true;
    }

    @Override
    public Matrix solve(LinearSystem system, Configuration config) throws LinearSystemSolvingException {
        Matrix a = system.a();
        Matrix b = system.b();

        if (a.getWidth() != a.getHeight()) throw new LinearSystemSolvingException("Matrix A of the system is not square. Got " + a);
        if (a.getHeight() != b.getHeight()) throw new LinearSystemSolvingException("Matrix A does not have the same height as matrix B, got A: " + a + ", B: " + b);

        if (!makeDiagonallyDominant(system)) throw new LinearSystemSolvingException("Matrix A is not and can not be transformed to a diagonally dominant form");

        boolean reachedEpsilon = false;
        Matrix x = new Matrix(a.getHeight(), 1);
        iterations = 0;

        while (iterations < maxIterations && !reachedEpsilon) {
            iterations++;
            reachedEpsilon = true;

            if (config.getFlag(Configuration.DEBUG_FLAG)) System.err.println("Iteration No." + iterations + " error = " + system.getError(x));

            for (int i = 0; i < a.getHeight(); i++) {
                double s = b.get(i, 0);

                // Get right side sum
                for (int j = 0; j < a.getWidth(); j++) {
                    // Skip diagonal elements
                    if (i == j) continue;

                    s -= x.get(j, 0) * a.get(i, j);
                }

                s /= a.get(i, i);
                if (Math.abs(s - x.get(i, 0)) > eps) reachedEpsilon = false;
                x.set(i, 0, s);
            }
        }

        return x;
    }

    public int getIterations() {
        return iterations;
    }

    @Override
    public String toString() {
        return "Gauss-Seidel method using a maximum of " + maxIterations + " iterations and an epsilon value of " + eps;
    }
}
