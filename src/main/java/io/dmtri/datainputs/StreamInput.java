package io.dmtri.datainputs;

import io.dmtri.Matrix;
import io.dmtri.exceptions.DataInputException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Supplier;

public class StreamInput implements DataInput {
    private final InputStream in;

    public StreamInput(InputStream in) {
        this.in = in;
    }

    @Override
    public Matrix getData(int height, int width) throws DataInputException {
        try (Scanner scanner = new Scanner(in)) {
            final Matrix result = new Matrix(height, width);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    result.set(i, j, scanner.nextDouble());
                }
            }

            return result;
        } catch (InputMismatchException e) {
            throw new DataInputException("Invalid data provided", e);
        }
    }
}
