package lol.gggedr.eventannouncer.commands;

import lol.gggedr.eventannouncer.cons.Event;
import lol.gggedr.eventannouncer.managers.Managers;
import lol.gggedr.eventannouncer.managers.impl.ConfigurationManager;
import lol.gggedr.eventannouncer.managers.impl.EventsManager;
import lol.gggedr.eventannouncer.utils.MessageUtils;
import net.md_5.bungee.api.CommandSender;
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

        if(args.length < 2) {
            sendUsage(sender, config);
            return;
        }

        String name = args[0];
        long seconds;
        try {
            seconds = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            sendUsage(sender, config);
            return;
        }

        Event event = new Event(name, sender.getName(), System.currentTimeMillis() + (seconds * 1000));
        event.sendAnnounceMessage();
        Managers.getManager(EventsManager.class).addEvent(event);

        sender.sendMessage(MessageUtils.color(config.getStringList("messages.created")));
    }

    private void sendUsage(CommandSender sender, Configuration config) {
        List<String> message = config.getStringList("messages.usage");
        String colored = MessageUtils.color(message);

        sender.sendMessage(colored);
    }
}
