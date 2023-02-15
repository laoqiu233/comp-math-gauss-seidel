package io.dmtri.options;

import io.dmtri.Configuration;

public class HelpOption extends AbstractOption {
    private final OptionsManager optionsList;

    public HelpOption(OptionsManager optionsList) {
        super("help", 'h', 0, "Outputs an overview of all available options");
        this.optionsList = optionsList;
    }

    @Override
    public void execute(Configuration configuration, String[] arguments) {
        for (AbstractOption option : optionsList.getAllOptions()) {
            System.out.println("--" + option.getFullName() + ", -" + option.getShortName() + " " + option.getDescription());
        }
    }
}
