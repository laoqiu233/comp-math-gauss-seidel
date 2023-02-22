package io.dmtri.options;

import io.dmtri.Configuration;
import io.dmtri.math.Matrix;
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
                "[path-to-file] configures the application to read the input from a file, default input is " + Configuration.DEFAULT_DATA_INPUT
        );
    }

    @Override
    public void execute(Configuration configuration, String[] arguments) throws OptionParsingException {
        try {
            FileInputStream in = new FileInputStream(arguments[0]);
            configuration.setDataInput(new StreamInput(in) {
                @Override
                public String toString() {
                    return "input from file file \"" + arguments[0] + "\"";
                }
            });
        } catch (FileNotFoundException e) {
            throw new OptionParsingException("Can not find file with name " + arguments[0]);
        }
    }
}
