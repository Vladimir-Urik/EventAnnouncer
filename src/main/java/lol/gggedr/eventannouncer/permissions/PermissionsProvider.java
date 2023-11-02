package lol.gggedr.eventannouncer.permissions;

import net.md_5.bungee.api.ProxyServer;

public interface PermissionsProvider {

    default public String getRequiredPlugin() {
        return null;
    }

    default public boolean isSupported() {
        if(getRequiredPlugin() == null) return true;

        ProxyServer proxy = ProxyServer.getInstance();
        return proxy.getPluginManager().getPlugin(getRequiredPlugin()) != null;
    }

    public String getPlayerPrefix(String player);

    public String getPlayerSuffix(String player);

    public String getPlayerGroup(String player);

}
