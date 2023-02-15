package io.dmtri.datainputs;

import io.dmtri.Matrix;
import io.dmtri.exceptions.DataInputException;

public interface DataInput {
    Matrix getData(int height, int width) throws DataInputException;
}
