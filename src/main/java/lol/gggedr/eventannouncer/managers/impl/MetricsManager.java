package lol.gggedr.eventannouncer.managers.impl;

import lol.gggedr.eventannouncer.managers.Manager;
import lol.gggedr.eventannouncer.metrics.BStats;
import net.md_5.bungee.config.Configuration;

public class MetricsManager implements Manager {

    private final int pluginId = 20181;

    @Override
    public void onEnable() {
        new BStats(getPlugin(), pluginId);
    }
}
