package io.dmtri.options;

import io.dmtri.Configuration;
import io.dmtri.exceptions.OptionParsingException;

public abstract class AbstractOption {
    private final String fullName;
    private final char shortName;
    private final int argumentsCount;
    private final String description;

    public AbstractOption(String fullName, char shortName, int argumentsCount, String description) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.argumentsCount = argumentsCount;
        this.description = description;
    }

    public String getFullName() {
        return fullName;
    }

    public char getShortName() {
        return shortName;
    }

    public int getArgumentsCount() {
        return argumentsCount;
    }

    public String getDescription() {
        return description;
    }

    public abstract void execute(Configuration configuration, String[] arguments) throws OptionParsingException;
}
