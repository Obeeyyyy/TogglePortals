/* CrownPlugins - TogglePortals */
/* 05.10.2024 - 23:12 */

package de.obey.crown.listener;

import de.obey.crown.core.event.CoreStartEvent;
import de.obey.crown.noobf.TogglePortals;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public final class CoreStart implements Listener {

    private final TogglePortals togglePortals;

    @EventHandler
    public void on(final CoreStartEvent event) {
        event.sendStartupMessage(togglePortals);
    }

}
