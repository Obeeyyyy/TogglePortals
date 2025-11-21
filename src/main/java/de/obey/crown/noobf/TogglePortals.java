package de.obey.crown.noobf;

import de.obey.crown.command.TogglePortalCommand;
import de.obey.crown.core.data.plugin.Messanger;
import de.obey.crown.listener.CoreStart;
import de.obey.crown.listener.Portal;
import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class TogglePortals extends JavaPlugin {

    private PluginConfig pluginConfig;
    private Messanger messanger;


    public static TogglePortals getInstance() {
        return getPlugin(TogglePortals.class);
    }

    @Override
    public void onEnable() {
        pluginConfig = new PluginConfig(this);
        messanger = pluginConfig.getMessanger();

        final PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new CoreStart(this), this);
        pluginManager.registerEvents(new Portal(pluginConfig, messanger), this);

        final TogglePortalCommand togglePortalCommand = new TogglePortalCommand(pluginConfig, messanger);
        getCommand("toggleportal").setExecutor(togglePortalCommand);
        getCommand("toggleportal").setTabCompleter(togglePortalCommand);

        initializeBStats();
    }

    private void initializeBStats() {
        new Metrics(this, 28078);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
