package io.dmtri.options;

import io.dmtri.Configuration;
import io.dmtri.exceptions.OptionParsingException;

public class TimeOption extends AbstractOption {
    public TimeOption() {
        super(
                "time",
                't',
                0,
                "toggles the timer to test the performance of the program, by default this option is " + (Configuration.getDefaultFlag(Configuration.TIME_FLAG) ? "on" : "off")
        );
    }

    @Override
    public void execute(Configuration configuration, String[] arguments) {
        configuration.setFlag(true, Configuration.TIME_FLAG);
    }
}
