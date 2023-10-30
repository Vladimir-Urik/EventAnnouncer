package lol.gggedr.eventannouncer.managers;

import lol.gggedr.eventannouncer.EventAnnouncer;

public interface Manager {

    /**
     * This function is called when the manager is enabled
     */
    default void onEnable() {
    }

    /**
     * This function is called when the manager is disabled
     */
    default void onDisable() {

    }

    /**
     * This function is called when the plugin is reloaded
     */
    default void onReload() {

    }

    /**
     * It returns the instance of the plugin
     *
     * @return The instance of the plugin.
     */
    default EventAnnouncer getPlugin() {
        return EventAnnouncer.getInstance();
    }

    /**
     * Get the manager of the given class.
     *
     * @param clazz The class of the manager you want to get.
     * @return The manager of the given class.
     */
    default <T extends Manager> T getManager(Class<T> clazz) {
        return Managers.getManager(clazz);
    }

}
