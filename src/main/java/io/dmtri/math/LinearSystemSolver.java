package io.dmtri.math;

import io.dmtri.Configuration;
import io.dmtri.exceptions.LinearSystemSolvingException;

public interface LinearSystemSolver {
    public Matrix solve(LinearSystem system, Configuration config) throws LinearSystemSolvingException;
}
