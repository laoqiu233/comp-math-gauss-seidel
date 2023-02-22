package io.dmtri.math;

public record LinearSystem(Matrix a, Matrix b) {
    public double getError(Matrix x) {
        Matrix errorColumn = a.multiply(x).add(b.negate()).apply(Math::abs);
        double sumOfErrors = 0;
        for (int i = 0; i < errorColumn.getHeight(); i++) sumOfErrors += errorColumn.get(i, 0);
        return sumOfErrors / errorColumn.getHeight();
    }
}
