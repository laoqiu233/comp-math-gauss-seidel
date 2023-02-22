package io.dmtri.options;

import io.dmtri.Configuration;
import io.dmtri.exceptions.OptionParsingException;

public class DebugOption extends AbstractOption {
    public DebugOption() {
        super(
            "debug",
                'd',
                0,
                "specify this option to output debug information, by default this option is " + (Configuration.getDefaultFlag(Configuration.DEBUG_FLAG) ? "on" : "off")
        );


    }

    @Override
    public void execute(Configuration configuration, String[] arguments) {
        configuration.setFlag(true, Configuration.DEBUG_FLAG);
    }
}
