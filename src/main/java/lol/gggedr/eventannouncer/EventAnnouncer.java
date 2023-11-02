package lol.gggedr.eventannouncer;

import lol.gggedr.eventannouncer.managers.Managers;
import lol.gggedr.eventannouncer.managers.impl.*;
import lol.gggedr.eventannouncer.permissions.PermissionsProvider;
import net.md_5.bungee.api.plugin.Plugin;

public final class EventAnnouncer extends Plugin {

    private static EventAnnouncer instance;

    @Override
    public void onEnable() {
        instance = this;

        Managers.register(ConfigurationManager.class);
        Managers.register(CommandsManager.class);
        Managers.register(MetricsManager.class);
        Managers.register(PluginProvidersManager.class);
        Managers.register(EventsManager.class);

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
