package lol.gggedr.eventannouncer.utils;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.HashMap;
import java.util.List;

public class MessageUtils {

    public static String color(String message) {
        return message.replace("&", "§");
    }

    public static String color(List<String> message) {
        return color(Strings.join(message, "\n&r"));
    }

    public static String color(List<String> message, HashMap<String, String> placeholders) {
        String newMessage = Strings.join(message, "\n&r");
        for(String placeholder : placeholders.keySet()) {
            newMessage = newMessage.replace("%"+ placeholder +"%", placeholders.get(placeholder));
        }

        return color(newMessage);
    }

    public static String color(String message, HashMap<String, String> placeholders) {
        String newMessage = message;
        for(String placeholder : placeholders.keySet()) {
            newMessage = newMessage.replace("%"+ placeholder +"%", placeholders.get(placeholder));
        }

        return color(newMessage);
    }

    public static BaseComponent[] buildClickableMessage(String message, String url) {
        BaseComponent[] components = TextComponent.fromLegacyText(color(message));
        for(BaseComponent component : components) {
            component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        }

        return components;
    }

    public static BaseComponent[] buildClickableMessageCommand(String message, String command) {
        BaseComponent[] components = TextComponent.fromLegacyText(color(message));
        for(BaseComponent component : components) {
            component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        }

        return components;
    }
}
