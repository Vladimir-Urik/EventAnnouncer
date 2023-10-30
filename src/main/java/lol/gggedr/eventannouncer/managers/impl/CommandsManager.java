package lol.gggedr.eventannouncer.managers.impl;

import lol.gggedr.eventannouncer.commands.EventAnnounceCommand;
import lol.gggedr.eventannouncer.managers.Manager;
import net.md_5.bungee.config.Configuration;

public class CommandsManager implements Manager {

    public void onEnable() {
        Configuration config = getManager(ConfigurationManager.class).getEventAnnouncerConfig();

        String permission = config.getString("permissions.announce");
        EventAnnounceCommand command = new EventAnnounceCommand(permission);

        getPlugin().getProxy().getPluginManager().registerCommand(getPlugin(), command);
    }

}
