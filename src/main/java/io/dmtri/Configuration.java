package io.dmtri;

import io.dmtri.datainputs.DataInput;
import io.dmtri.math.GaussSeidelSolver;
import io.dmtri.math.LinearSystem;
import io.dmtri.datainputs.RandomInput;
import io.dmtri.exceptions.DataInputException;
import io.dmtri.math.LinearSystemSolver;

public class Configuration {
    public static final int DEFAULT_WIDTH = 10;
    public static final int DEFAULT_HEIGHT = 10;
    public static final DataInput DEFAULT_DATA_INPUT = new RandomInput(-10, 10);
    public static final LinearSystemSolver DEFAULT_SOLVER = new GaussSeidelSolver(0.0001, 1000);

    private int width = DEFAULT_WIDTH;
    private int height = DEFAULT_HEIGHT;
    private DataInput dataInput = DEFAULT_DATA_INPUT;
    private LinearSystemSolver solver = DEFAULT_SOLVER;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDataInput(DataInput dataInput) {
        this.dataInput = dataInput;
    }

    public DataInput getDataInput() {
        return dataInput;
    }

    public void setSolver(LinearSystemSolver solver) {
        this.solver = solver;
    }

    public LinearSystemSolver getSolver() {
        return solver;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
