package io.dmtri.options;

import io.dmtri.Configuration;
import io.dmtri.exceptions.OptionParsingException;

public class SizeOption extends AbstractOption{
    public SizeOption() {
        super(
                "size",
                's',
                2,
                "[height] [width] - configures the dimensions of the matrix"
        );
    }

    @Override
    public void execute(Configuration configuration, String[] arguments) throws OptionParsingException {
        int height, width;
        try {
            height = Integer.parseInt(arguments[0]);
            width = Integer.parseInt(arguments[1]);
        } catch (NumberFormatException e) {
            throw new OptionParsingException("Invalid size", e);
        }

        configuration.setHeight(height);
        configuration.setWidth(width);
    }
}
