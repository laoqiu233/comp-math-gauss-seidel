package io.dmtri.options;

import io.dmtri.Configuration;
import io.dmtri.math.Matrix;
import io.dmtri.datainputs.StreamInput;
import io.dmtri.exceptions.DataInputException;
import io.dmtri.exceptions.OptionParsingException;

public class StdinInputOption extends AbstractOption {
    public StdinInputOption() {
        super(
                "stdin",
                'c',
                0,
                "- configures the application to read the input from a file, default input is " + Configuration.DEFAULT_DATA_INPUT
        );
    }

    @Override
    public void execute(Configuration configuration, String[] arguments) throws OptionParsingException {
        configuration.setDataInput(new StreamInput(System.in) {
            @Override
            public Matrix getData(int height, int width) throws DataInputException {
                System.out.println("Please enter a matrix with " + height + " rows and " + width + " columns:");
                return super.getData(height, width);
            }

            @Override
            public String toString() {
                return "standard input";
            }
        });
    }
}
