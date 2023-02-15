package io.dmtri;

import io.dmtri.exceptions.OptionParsingException;
import io.dmtri.options.AbstractOption;
import io.dmtri.options.OptionsManager;

public class OptionsParser {
    public static void parse(String[] args, Configuration configuration) throws OptionParsingException {
        OptionsManager options = new OptionsManager();

        for (int i = 0; i < args.length; i++) {
            AbstractOption optionToExecute;
            if (args[i].startsWith("--")) {
                optionToExecute = options.getOptionByFullName(args[i].substring(2));
            } else if (args[i].startsWith("-") && args[i].length() == 2) {
                optionToExecute = options.getOptionByShortName(args[i].charAt(1));
            } else throw new OptionParsingException("Invalid option " + args[i] + ", all options must start with either - or --");

            if (optionToExecute == null) throw new OptionParsingException("Invalid option " + args[i]);

            if (i + optionToExecute.getArgumentsCount() >= args.length)
                throw new OptionParsingException("Not enough arguments, option " + args[i] + " requires " + optionToExecute + " parameters");

            String[] arguments = new String[optionToExecute.getArgumentsCount()];

            for (int j = 0; j < optionToExecute.getArgumentsCount(); j++) {
                arguments[j] = args[i+j+1];
            }

            optionToExecute.execute(configuration, arguments);

            i += optionToExecute.getArgumentsCount();
        }
    }
}
