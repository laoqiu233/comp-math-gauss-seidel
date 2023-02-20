package io.dmtri.datainputs;

import io.dmtri.math.Matrix;
import io.dmtri.exceptions.DataInputException;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StreamInput implements DataInput {
    private final Scanner scanner;

    public StreamInput(InputStream in) {
        this.scanner = new Scanner(in);
    }

    @Override
    public Matrix getData(int height, int width) throws DataInputException {
        try {
            final Matrix result = new Matrix(height, width);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    result.set(i, j, scanner.nextDouble());
                }
            }

            return result;
        } catch (InputMismatchException e) {
            throw new DataInputException("Invalid data provided", e);
        } catch (NoSuchElementException e) {
            throw new DataInputException("Not enough data provided", e);
        }
    }

    @Override
    public void close() {
        scanner.close();
    }
}
