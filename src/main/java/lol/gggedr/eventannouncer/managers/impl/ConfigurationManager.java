package lol.gggedr.eventannouncer.managers.impl;

import lol.gggedr.eventannouncer.managers.Manager;
import lol.gggedr.eventannouncer.utils.FileUtils;
import net.md_5.bungee.config.Configuration;

import java.io.File;

public class ConfigurationManager implements Manager {

    private Configuration eventAnnouncerConfig;

    public void onEnable() {
        getPlugin().getDataFolder().mkdirs();

        File eventAnnouncerFile = new File(getPlugin().getDataFolder(), "config.yml");
        FileUtils.copyFileFromJar(eventAnnouncerFile.getName(), eventAnnouncerFile);
        eventAnnouncerConfig = FileUtils.loadConfiguration(eventAnnouncerFile);
    }

    public Configuration getEventAnnouncerConfig() {
        return eventAnnouncerConfig;
    }

}
