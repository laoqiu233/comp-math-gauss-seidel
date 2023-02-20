package io.dmtri.math;

public record LinearSystem(Matrix a, Matrix b) {
    public double getError(Matrix x) {
        Matrix errorColumn = a.multiply(x).add(b.negate());
        double sumOfErrors = 0;
        for (int i = 0; i < errorColumn.getHeight(); i++) sumOfErrors += errorColumn.get(i, 0);
        return Math.abs(sumOfErrors / errorColumn.getHeight());
    }
}
