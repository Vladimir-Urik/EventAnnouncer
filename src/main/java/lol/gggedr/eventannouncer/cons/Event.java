package lol.gggedr.eventannouncer.cons;

import lol.gggedr.eventannouncer.managers.Managers;
import lol.gggedr.eventannouncer.managers.impl.ConfigurationManager;
import lol.gggedr.eventannouncer.managers.impl.EventsManager;
import lol.gggedr.eventannouncer.managers.impl.PluginProvidersManager;
import lol.gggedr.eventannouncer.permissions.PermissionsProvider;
import lol.gggedr.eventannouncer.utils.MessageUtils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.config.Configuration;

import java.util.HashMap;
import java.util.List;

public class Event {

    private final String name;
    private final String eventLeader;
    private final long startTime;

    public Event(String name, String eventLeader, long startTime) {
        this.name = name;
        this.eventLeader = eventLeader;
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public String getEventLeader() {
        return eventLeader;
    }

    public long getStartTime() {
        return startTime;
    }

    public void tickEvent() {
        Configuration configuration = Managers.getManager(ConfigurationManager.class).getEventAnnouncerConfig();
        long seconds = (startTime - System.currentTimeMillis()) / 1000;

        if(seconds == 0) {
            if(!configuration.getBoolean("notifications.start")) return;

            List<String> messages = configuration.getStringList("messages.start");
            String colorizedMessage = MessageUtils.color(messages, getPlaceholders(seconds));
            ProxyServer.getInstance().broadcast(colorizedMessage);

            Managers.getManager(EventsManager.class).removeEvent(this);
            return;
        }

        if(!configuration.getBoolean("notifications.start-soon")) return;

        if(seconds >= 5) {
            if(seconds % 5 != 0) return;

            sendStartSoonMessage(seconds);
            return;
        }

        sendStartSoonMessage(seconds);
    }

    public HashMap<String, String> getPlaceholders(long seconds) {
        HashMap<String, String> placeholders = new HashMap<>();

        placeholders.put("name", name);
        placeholders.put("leader", eventLeader);
        placeholders.put("seconds", String.valueOf((int) seconds));

        PermissionsProvider permissionsProvider = Managers.getManager(PluginProvidersManager.class).getPermissionsProvider();
        placeholders.put("prefix", permissionsProvider.getPlayerPrefix(eventLeader));
        placeholders.put("suffix", permissionsProvider.getPlayerSuffix(eventLeader));
        placeholders.put("group", permissionsProvider.getPlayerGroup(eventLeader));

        return placeholders;
    }

    private void sendStartSoonMessage(long seconds) {
        Configuration config = Managers.getManager(ConfigurationManager.class).getEventAnnouncerConfig();

        String command = "/"+ config.getString("command");
        List<String> message = config.getStringList("messages.announce");
        String colored = MessageUtils.color(message, getPlaceholders(seconds));

        BaseComponent[] components = MessageUtils.buildClickableMessageCommand(
                colored,
                command
        );

        ProxyServer.getInstance().broadcast(components);
    }

    public void sendAnnounceMessage() {
        Configuration config = Managers.getManager(ConfigurationManager.class).getEventAnnouncerConfig();

        if(!config.getBoolean("notifications.announce")) return;

        long seconds = (startTime - System.currentTimeMillis()) / 1000;
        String command = "/"+ config.getString("command");
        List<String> message = config.getStringList("messages.announce");
        String colored = MessageUtils.color(message, getPlaceholders(seconds));

        BaseComponent[] components = MessageUtils.buildClickableMessageCommand(
                colored,
                command
        );

        ProxyServer.getInstance().broadcast(components);
    }

}
