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
            for (int j = 0; j < width; j++) {
                result.set(i, j, r.nextDouble(lowerBound, upperBound));
            }
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
