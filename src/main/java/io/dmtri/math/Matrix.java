package io.dmtri.math;

import java.util.Formatter;
import java.util.function.UnaryOperator;

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

    public Matrix multiply(Matrix other) {
        if (getWidth() != other.getHeight()) throw new IllegalArgumentException("Mismatched matrix sizes");

        Matrix result = new Matrix(getHeight(), other.getWidth());

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < other.getWidth(); j++) {
                double res = 0;
                for (int k = 0; k < getWidth(); k++) res += get(i, k) * other.get(k, j);
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

    public Matrix add(Matrix other) {
        if (getHeight() != other.getHeight() || getWidth() != other.getWidth()) throw new IllegalArgumentException("Mismatched matrix sizes");
        Matrix result = new Matrix(getHeight(), getWidth());

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                result.set(i, j, get(i, j) + other.get(i, j));
            }
        }

        return result;
    }

    public void swapRows(int i, int j) {
        for (int k = 0; k < getWidth(); k++) {
            double temp = matrix[i][k];
            matrix[i][k] = matrix[j][k];
            matrix[j][k] = temp;
        }
    }

    public Matrix apply(UnaryOperator<Double> func) {
        Matrix result = new Matrix(getHeight(), getWidth());

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                result.set(i, j, func.apply(get(i, j)));
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return getHeight() + "x" + getWidth() + " matrix";
    }

    public String formatMatrix(int decimalDigits, String rowPrefixFormat) {
        Formatter formatter = new Formatter();

        int[] columnsSize = new int[getWidth()];

        // First pass, get the maximum width of all
        // columns for proper formatting
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                columnsSize[j] = Math.max(columnsSize[j], String.format("%."+decimalDigits+"f", get(i, j)).length());
            }
        }

        for (int i = 0; i < getHeight(); i++) {
            if (rowPrefixFormat != null) formatter.format(rowPrefixFormat, i+1);
            for (int j = 0; j < getWidth(); j++) {
                formatter.format("%"+columnsSize[j]+"."+decimalDigits+"f\t", get(i, j));
            }
            formatter.format("\n");
        }

        return formatter.toString();
    }

    public String formatMatrix(int decimalDigits) {
        return formatMatrix(decimalDigits, null);
    }
}
