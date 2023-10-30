package lol.gggedr.eventannouncer.utils;

import lol.gggedr.eventannouncer.EventAnnouncer;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileUtils {

    public static void copyFileFromJar(String file, File target) {
        if(target.exists()) {
            return;
        }

        try (InputStream is = EventAnnouncer.getInstance().getResourceAsStream(file)) {
            if (is == null) {
                throw new RuntimeException("File not found in jar: " + file);
            }

            Files.copy(is, target.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Configuration loadConfiguration(File file) {
        return loadConfiguration(file, true);
    }

    public static Configuration loadConfiguration(File file, boolean copyFromJar) {
        Configuration configuration;
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(copyFromJar) {
            try (InputStream is = EventAnnouncer.getInstance().getResourceAsStream(file.getName())) {
                Configuration defaultConfiguration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(is);

                AtomicBoolean save = new AtomicBoolean(false);
                ConfigUtils.getKeysDeep(defaultConfiguration).forEach(key -> {
                    if (key.startsWith("groups.")) {
                        return;
                    }

                    if (key.startsWith("server-commands.")) {
                        return;
                    }

                    if (!configuration.contains(key)) {
                        configuration.set(key, defaultConfiguration.get(key));
                        save.set(true);
                    }
                });

                if (save.get()) {
                    ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return configuration;
    }

    public static String loadFileContent(File file) {
        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveConfig(Configuration configuration, File file) {
        try {
            file.createNewFile();
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveData(String data, File file) {
        try {
            Files.write(file.toPath(), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
