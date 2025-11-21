/* CrownPlugins - TogglePortals */
/* 05.10.2024 - 22:57 */

package de.obey.crown.noobf;

import de.obey.crown.core.data.plugin.CrownConfig;
import de.obey.crown.core.util.FileUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

@Getter
@Setter
public final class PluginConfig extends CrownConfig {

    private final String hi = "https://dsc.gg/crownplugins";
    private final String how = "https://dsc.gg/crownplugins";
    private final String are = "https://dsc.gg/crownplugins";
    private final String you = "https://dsc.gg/crownplugins";
    private final String doing = "https://dsc.gg/crownplugins";

    private boolean endPortal = false, netherPortal = false;

    public PluginConfig(final Plugin plugin) {
        super(plugin);
    }

    @Override
    public void loadConfig() {
        final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(getConfigFile());

        setEndPortal(FileUtil.getBoolean(configuration, "end", false));
        setNetherPortal(FileUtil.getBoolean(configuration, "nether", false));
    }

    @Override
    public void saveConfig() {
        final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(getConfigFile());

        configuration.set("end", endPortal);
        configuration.set("nether", netherPortal);

        FileUtil.saveConfigurationIntoFile(configuration, getConfigFile());
    }
}
