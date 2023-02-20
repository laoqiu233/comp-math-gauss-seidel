package io.dmtri.datainputs;

import io.dmtri.math.Matrix;
import io.dmtri.exceptions.DataInputException;

public interface DataInput extends AutoCloseable {
    Matrix getData(int height, int width) throws DataInputException;
}
