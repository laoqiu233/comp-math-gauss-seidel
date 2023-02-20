package io.dmtri.math;

import io.dmtri.exceptions.LinearSystemSolvingException;

public interface LinearSystemSolver {
    public Matrix solve(LinearSystem system) throws LinearSystemSolvingException;
}
