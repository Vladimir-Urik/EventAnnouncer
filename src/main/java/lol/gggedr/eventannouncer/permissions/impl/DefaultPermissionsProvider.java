package lol.gggedr.eventannouncer.permissions.impl;

import lol.gggedr.eventannouncer.permissions.PermissionsProvider;
import net.md_5.bungee.api.ProxyServer;

public class DefaultPermissionsProvider implements PermissionsProvider {

    @Override
    public String getPlayerPrefix(String player) {
        return "";
    }

    @Override
    public String getPlayerSuffix(String player) {
        return "";
    }

    @Override
    public String getPlayerGroup(String player) {
        return ProxyServer.getInstance().getPlayer(player).getGroups()
                .stream().findFirst().orElse("");
    }

}
