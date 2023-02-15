package io.dmtri.options;

import io.dmtri.Configuration;
import io.dmtri.datainputs.RandomInput;
import io.dmtri.exceptions.OptionParsingException;

public class RandomInputOption extends AbstractOption {
    public RandomInputOption() {
        super(
                "random",
                'r',
                2,
                "[lower-bound] [upper-bound] generates input data randomly, all numbers will be within the specified range, default input is " + Configuration.DEFAULT_DATA_INPUT
        );
    }

    @Override
    public void execute(Configuration configuration, String[] arguments) throws OptionParsingException {
        double lowerBound, upperBound;

        try {
            lowerBound = Double.parseDouble(arguments[0]);
            upperBound = Double.parseDouble(arguments[1]);
        } catch (NumberFormatException e) {
            throw new OptionParsingException("Invalid arguments provided for random data generation", e);
        }

        configuration.setDataInput(new RandomInput(lowerBound, upperBound));
    }
}
