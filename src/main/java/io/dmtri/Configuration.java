package io.dmtri;

import io.dmtri.datainputs.DataInput;
import io.dmtri.math.GaussSeidelSolver;
import io.dmtri.datainputs.RandomInput;
import io.dmtri.math.LinearSystemSolver;

public class Configuration {
    public static final int DEFAULT_WIDTH = 10;
    public static final int DEFAULT_HEIGHT = 10;
    public static final DataInput DEFAULT_DATA_INPUT = new RandomInput(-10, 10);
    public static final LinearSystemSolver DEFAULT_SOLVER = new GaussSeidelSolver(0.0001, 1000);
    public static final int DEBUG_FLAG = 1;
    public static final int TIME_FLAG = 1 << 1;
    public static final int DEFAULT_FLAGS = 0;

    private int width = DEFAULT_WIDTH;
    private int height = DEFAULT_HEIGHT;
    private DataInput dataInput = DEFAULT_DATA_INPUT;
    private LinearSystemSolver solver = DEFAULT_SOLVER;
    private int flags = DEFAULT_FLAGS;

    public static boolean getDefaultFlag(int flag) {
        return (DEFAULT_FLAGS & flag) > 0;
    }

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

    public void setFlag(boolean toggle, int flag) {
        if (toggle) flags |= flag;
        else flags &= ~flag;
    }

    public boolean getFlag(int flag) {
        return (flags & flag) > 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
