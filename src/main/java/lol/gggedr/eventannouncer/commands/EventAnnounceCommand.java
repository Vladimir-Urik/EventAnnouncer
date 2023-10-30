package lol.gggedr.eventannouncer.commands;

import lol.gggedr.eventannouncer.managers.Managers;
import lol.gggedr.eventannouncer.managers.impl.ConfigurationManager;
import lol.gggedr.eventannouncer.utils.MessageUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

import java.util.List;

public class EventAnnounceCommand extends Command {

    public EventAnnounceCommand(String permission) {
        super("eventannounce", permission, "ea");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        Configuration config = Managers.getManager(ConfigurationManager.class).getEventAnnouncerConfig();

        String command = "/"+ config.getString("command");

        List<String> message = config.getStringList("messages.announce");
        String colored = MessageUtils.color(message);

        BaseComponent[] components = MessageUtils.buildClickableMessageCommand(
                colored,
                command
        );

        ProxyServer.getInstance().broadcast(components);
    }
}
