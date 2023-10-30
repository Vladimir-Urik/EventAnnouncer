package lol.gggedr.eventannouncer;

import lol.gggedr.eventannouncer.managers.Managers;
import lol.gggedr.eventannouncer.managers.impl.CommandsManager;
import lol.gggedr.eventannouncer.managers.impl.ConfigurationManager;
import lol.gggedr.eventannouncer.managers.impl.MetricsManager;
import net.md_5.bungee.api.plugin.Plugin;

public final class EventAnnouncer extends Plugin {

    private static EventAnnouncer instance;

    @Override
    public void onEnable() {
        instance = this;

        Managers.register(ConfigurationManager.class);
        Managers.register(CommandsManager.class);
        Managers.register(MetricsManager.class);

        Managers.onEnable();
    }

    @Override
    public void onDisable() {
        Managers.onDisable();
    }

    public static EventAnnouncer getInstance() {
        return instance;
    }
}
