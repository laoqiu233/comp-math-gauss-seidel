package io.dmtri.options;

import io.dmtri.Configuration;
import io.dmtri.Matrix;
import io.dmtri.datainputs.DataInput;
import io.dmtri.datainputs.StreamInput;
import io.dmtri.exceptions.DataInputException;
import io.dmtri.exceptions.OptionParsingException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputOption extends AbstractOption{
    public FileInputOption() {
        super(
                "file",
                'f',
                1,
                "[path-to-file] - configures the application to read the input from a file, default input is " + Configuration.DEFAULT_DATA_INPUT
        );
    }

    @Override
    public void execute(Configuration configuration, String[] arguments) throws OptionParsingException {
        configuration.setDataInput(new DataInput() {
            @Override
            public Matrix getData(int height, int width) throws DataInputException {
                try (FileInputStream in = new FileInputStream(arguments[0])) {
                    return new StreamInput(in).getData(height, width);
                } catch (FileNotFoundException e) {
                    throw new DataInputException("File " + arguments[0] + " was not found", e);
                } catch (IOException e) {
                    throw new DataInputException("Failed to read data from file " + arguments[0], e);
                }
            }
        });
    }
}
