/* CrownPlugins - ObeySoaring */
/* 01.08.2024 - 18:47 */

package de.obey.crown.command;

import de.obey.crown.core.data.plugin.Messanger;
import de.obey.crown.noobf.PluginConfig;
import de.obey.crown.noobf.TogglePortals;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor @NonNull
public final class TogglePortalCommand implements CommandExecutor, TabCompleter {

    private final String hi = "https://dsc.gg/crownplugins";
    private final String how = "https://dsc.gg/crownplugins";
    private final String are = "https://dsc.gg/crownplugins";
    private final String you = "https://dsc.gg/crownplugins";
    private final String doing = "https://dsc.gg/crownplugins";

    private final PluginConfig pluginConfig;
    private final Messanger messanger;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!messanger.hasPermission(sender, "command.toggleportals", false)) {
            messanger.sendNonConfigMessage(sender, "%prefix% %white%[ToggleCommand] made by %accent%@Obeeyyyy%white%.");
            return false;
        }

        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("end")) {
                pluginConfig.setEndPortal(!pluginConfig.isEndPortal());
                pluginConfig.saveConfig();

                final String state = pluginConfig.isEndPortal() ? "§a§lᴀᴄᴛɪᴠᴀᴛᴇᴅ" : "§c§lᴅᴇᴀᴄᴛɪᴠᴀᴛᴇᴅ";
                messanger.broadcastMessage("toggled",
                        new String[]{"state", "player", "type"},
                        state, sender.getName(), messanger.getMessage("end"));

                return false;
            }

            if(args[0].equalsIgnoreCase("nether")) {
                pluginConfig.setNetherPortal(!pluginConfig.isNetherPortal());
                pluginConfig.saveConfig();

                final String state = pluginConfig.isNetherPortal() ? "§a§lᴀᴄᴛɪᴠᴀᴛᴇᴅ" : "§c§lᴅᴇᴀᴄᴛɪᴠᴀᴛᴇᴅ";

                messanger.broadcastMessage("toggled",
                        new String[]{"state", "player", "type"},
                        state, sender.getName(), messanger.getMessage("nether"));

                return false;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        final ArrayList<String> list = new ArrayList<>();

        if(args.length == 1) {
            list.add("nether");
            list.add("end");
        }

        final String argument = args[args.length - 1];
        if (!argument.isEmpty())
            list.removeIf(value -> !value.toLowerCase().startsWith(argument.toLowerCase()));

        Collections.sort(list);

        return list;
    }
}
