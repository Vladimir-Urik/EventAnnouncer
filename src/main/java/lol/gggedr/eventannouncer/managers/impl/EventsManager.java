package lol.gggedr.eventannouncer.managers.impl;

import lol.gggedr.eventannouncer.cons.Event;
import lol.gggedr.eventannouncer.managers.Manager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.scheduler.ScheduledTask;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventsManager implements Manager {

    private final List<Event> events = new CopyOnWriteArrayList<>();
    private ScheduledTask task;

    @Override
    public void onEnable() {
        task = ProxyServer.getInstance().getScheduler().schedule(getPlugin(), () -> {
            for (Event event : events) {
                event.tickEvent();
            }
        }, 0, 1, java.util.concurrent.TimeUnit.SECONDS);
    }

    @Override
    public void onDisable() {
        task.cancel();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

}
