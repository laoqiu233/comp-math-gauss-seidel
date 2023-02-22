package io.dmtri.datainputs;

import io.dmtri.math.Matrix;

import java.util.Random;

public class RandomInput implements DataInput {
    private final double lowerBound;
    private final double upperBound;

    public RandomInput(double lowerBound, double upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public Matrix getData(int height, int width) {
        Matrix result = new Matrix(height, width);
        Random r = new Random();

        for (int i = 0; i < height; i++) {
            double rowSum = 0;
            for (int j = 0; j < width; j++) {
                result.set(i, j, r.nextDouble(lowerBound, upperBound));
                rowSum += Math.abs(result.get(i, j));
            }

            // This will make the matrix diagonally dominant, though values on
            // the diagonal will be out of the specified range.
            if (i < width) result.set(i, i, rowSum);
        }

        return result;
    }

    @Override
    public String toString() {
        return "random numbers from " + lowerBound + " to " + upperBound;
    }

    @Override
    public void close() {
        // Do nothing
    }
}
