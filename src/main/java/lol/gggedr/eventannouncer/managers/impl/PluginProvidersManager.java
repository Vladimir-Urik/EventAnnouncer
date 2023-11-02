package lol.gggedr.eventannouncer.managers.impl;

import lol.gggedr.eventannouncer.managers.Manager;
import lol.gggedr.eventannouncer.permissions.PermissionsProvider;
import lol.gggedr.eventannouncer.permissions.impl.DefaultPermissionsProvider;
import lol.gggedr.eventannouncer.permissions.impl.LuckPermsPermissionsProvider;
import lol.gggedr.eventannouncer.utils.Lists;

import java.util.List;

public class PluginProvidersManager implements Manager {


    private final List<PermissionsProvider> supportedPermissionsProviders = Lists.of(
            new LuckPermsPermissionsProvider()
    );

    private PermissionsProvider permissionsProvider;

    @Override
    public void onEnable() {
        PermissionsProvider permissionsProvider = null;
        for (PermissionsProvider provider : supportedPermissionsProviders) {
            if (provider.isSupported()) {
                permissionsProvider = provider;
                break;
            }
        }

        if (permissionsProvider == null) {
            permissionsProvider = new DefaultPermissionsProvider();
        }

        this.permissionsProvider = permissionsProvider;
    }

    public PermissionsProvider getPermissionsProvider() {
        return permissionsProvider;
    }

}
