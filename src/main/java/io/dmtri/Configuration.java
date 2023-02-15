package io.dmtri;

import io.dmtri.datainputs.DataInput;
import io.dmtri.datainputs.RandomInput;
import io.dmtri.exceptions.DataInputException;

public class Configuration {
    public static final int DEFAULT_WIDTH = 10;
    public static final int DEFAULT_HEIGHT = 10;
    public static final DataInput DEFAULT_DATA_INPUT = new RandomInput(-10, 10);

    private int width = DEFAULT_WIDTH;
    private int height = DEFAULT_HEIGHT;
    private DataInput dataInput = DEFAULT_DATA_INPUT;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDataInput(DataInput dataInput) {
        this.dataInput = dataInput;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Matrix getDataFromConfiguration() throws DataInputException {
        return dataInput.getData(getHeight(), getWidth());
    }
}
