/* CrownPlugins - ObeySoaring */
/* 01.08.2024 - 18:15 */

package de.obey.crown.listener;

import com.google.common.collect.Maps;
import de.obey.crown.core.data.plugin.Messanger;
import de.obey.crown.noobf.PluginConfig;
import lombok.RequiredArgsConstructor;
import org.bukkit.PortalType;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.util.Vector;

import java.util.Map;

@RequiredArgsConstructor
public final class Portal implements Listener {

    private final PluginConfig pluginConfig;
    private final Messanger messenger;

    private final Map<Player, Long> times = Maps.newConcurrentMap();

    @EventHandler
    public void on(final EntityPortalEvent event) {
        final Entity entity = event.getEntity();

        if(event.getPortalType() == PortalType.NETHER) {

            if(pluginConfig.isNetherPortal())
                return;

            event.setCancelled(true);

            return;
        }

        if(event.getPortalType() == PortalType.ENDER) {

            if(pluginConfig.isEndPortal())
                return;

            event.setCancelled(true);

            return;
        }
    }

    @EventHandler
    public void on(final PlayerPortalEvent event) {
        final Player player = event.getPlayer();

        if(event.getCause() == PlayerTeleportEvent.TeleportCause.END_PORTAL) {
            if(!pluginConfig.isEndPortal()) {
                if(times.containsKey(player)) {
                    if(times.get(player) <= System.currentTimeMillis())  {
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 0.5f, 0.5f);
                        messenger.sendMessage(player, "portal-disabled", new String[]{"type"}, messenger.getMessage("end"));
                        times.put(player, System.currentTimeMillis() + 1000);
                    }
                } else {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 0.5f, 0.5f);
                    messenger.sendMessage(player, "portal-disabled", new String[]{"type"}, messenger.getMessage("end"));
                    times.put(player, System.currentTimeMillis() + 1000);
                }

                event.setCancelled(true);

                final Vector direction = player.getLocation().getDirection();
                direction.multiply(-1.8);
                direction.setY(1.8);
                direction.normalize();
                player.setVelocity(direction);
            }
            return;
        }

        if(event.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
            if(!pluginConfig.isNetherPortal()) {
                if(times.containsKey(player)) {
                    if(times.get(player) <= System.currentTimeMillis())  {
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 0.5f, 0.5f);
                        messenger.sendMessage(player, "portal-disabled", new String[]{"type"}, messenger.getMessage("nether"));
                        times.put(player, System.currentTimeMillis() + 1000);
                    }
                } else {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 0.5f, 0.5f);
                    messenger.sendMessage(player, "portal-disabled", new String[]{"type"}, messenger.getMessage("nether"));
                    times.put(player, System.currentTimeMillis() + 1000);
                }

                event.setCancelled(true);

                final Vector direction = player.getLocation().getDirection();
                direction.multiply(-1.8);
                direction.setY(1.8);
                direction.normalize();
                player.setVelocity(direction);
            }
        }
    }

}
