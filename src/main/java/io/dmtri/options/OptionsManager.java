package io.dmtri.options;

import java.util.HashMap;
import java.util.Map;

public class OptionsManager {
    private final Map<String, AbstractOption> optionsFullName = new HashMap<>();
    private final Map<Character, AbstractOption> optionsShortName = new HashMap<>();
    private final AbstractOption[] allOptions;

    public OptionsManager() {
        allOptions = new AbstractOption[]{
                new HelpOption(this),
                new SizeOption(),
                new RandomInputOption(),
                new StdinInputOption(),
                new FileInputOption(),
                new GaussSeidelOption()
        };

        for (AbstractOption arg : allOptions) {
            optionsFullName.put(arg.getFullName(), arg);
            optionsShortName.put(arg.getShortName(), arg);
        }
    }

    public AbstractOption getOptionByShortName(char name) {
        return optionsShortName.get(name);
    }

    public AbstractOption getOptionByFullName(String name) {
        return optionsFullName.get(name);
    }

    public AbstractOption[] getAllOptions() {
        return allOptions;
    }
}
