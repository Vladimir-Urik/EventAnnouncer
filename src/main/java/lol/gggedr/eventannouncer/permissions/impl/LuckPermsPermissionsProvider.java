package lol.gggedr.eventannouncer.permissions.impl;

import lol.gggedr.eventannouncer.permissions.PermissionsProvider;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;

public class LuckPermsPermissionsProvider implements PermissionsProvider {

    private final LuckPerms luckPerms;

    public LuckPermsPermissionsProvider() {
        this.luckPerms = LuckPermsProvider.get();
    }

    @Override
    public String getRequiredPlugin() {
        return "LuckPerms";
    }

    @Override
    public String getPlayerPrefix(String player) {
        User user = getUser(player);
        if (user == null) return "";

        return user.getCachedData().getMetaData().getPrefix();
    }

    @Override
    public String getPlayerSuffix(String player) {
        User user = getUser(player);
        if (user == null) return "";

        return user.getCachedData().getMetaData().getSuffix();
    }

    @Override
    public String getPlayerGroup(String player) {
        User user = getUser(player);
        if (user == null) return "";

        return user.getPrimaryGroup();
    }

    private User getUser(String player) {
        return luckPerms.getUserManager().getUser(player);
    }
}
