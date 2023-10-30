package lol.gggedr.eventannouncer.utils;

import net.md_5.bungee.config.Configuration;

import java.util.List;

public class ConfigUtils {

    public static List<String> getKeysDeep(Configuration configuration) {
        List<String> keys = Lists.of();
        for(String key : configuration.getKeys()) {
            keys.add(key);
            if(configuration.get(key) instanceof Configuration) {
                String keysPrefix = key + ".";
                for(String keyDeep : getKeysDeep(configuration.getSection(key))) {
                    keys.add(keysPrefix + keyDeep);
                }
            }
        }

        return keys;
    }

}
