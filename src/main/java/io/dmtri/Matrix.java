package io.dmtri;

import java.util.ArrayList;

public class Matrix {
    private final double[][] matrix;

    public Matrix(int height, int width) {
        matrix = new double[height][width];
    }

    public int getWidth() {
        return matrix[0].length;
    }

    public int getHeight() {
        return matrix.length;
    }

    public double get(int i, int j) {
        return matrix[i][j];
    }

    public void set(int i, int j, double value) {
        matrix[i][j] = value;
    }

    public Matrix multiply(Matrix x) {
        if (getWidth() != x.getHeight()) throw new IllegalArgumentException("Mismatched matrix sizes");

        Matrix result = new Matrix(getHeight(), x.getWidth());

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < x.getWidth(); j++) {
                double res = 0;
                for (int k = 0; k < getWidth(); k++) res += get(i, k) * x.get(k, j);
                result.set(i, j, res);
            }
        }

        return result;
    }

    public Matrix transposed() {
        Matrix result = new Matrix(getWidth(), getHeight());

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                result.set(j, i, get(i, j));
            }
        }

        return result;
    }

    public Matrix negate() {
        Matrix result = new Matrix(getHeight(), getWidth());

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                result.set(i, j, -1 * get(i, j));
            }
        }

        return result;
    }

    public Matrix sum(Matrix x) {
        if (getHeight() != x.getHeight() || getWidth() != x.getWidth()) throw new IllegalArgumentException("Mismatched matrix sizes");
        Matrix result = new Matrix(getHeight(), getWidth());

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                result.set(i, j, get(i, j) + result.get(i, j));
            }
        }

        return result;
    }
}
