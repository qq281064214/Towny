package com.palmergames.bukkit.towny.listeners;

import com.palmergames.bukkit.towny.ChunkNotification;
import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.command.TownCommand;
import com.palmergames.bukkit.towny.command.TownyCommand;
import com.palmergames.bukkit.towny.event.PlayerChangePlotEvent;
import com.palmergames.bukkit.towny.object.CellBorder;
import com.palmergames.bukkit.towny.object.WorldCoord;
import com.palmergames.bukkit.towny.util.BorderUtil;
import com.palmergames.bukkit.util.DrawSmokeTask;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * Author: Chris H (Zren / Shade)
 * Date: 4/15/12
 */
public class TownyCustomListener implements Listener {
    private final Towny plugin;

    public TownyCustomListener(Towny instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChangePlotEvent(PlayerChangePlotEvent event) {
        Player player = event.getPlayer();
        WorldCoord from = event.getFrom();
        WorldCoord to = event.getTo();

        // TODO: Player mode
        if (plugin.hasPlayerMode(player, "townclaim"))
            TownCommand.parseTownClaimCommand(player, new String[]{});
        if (plugin.hasPlayerMode(player, "townunclaim"))
            TownCommand.parseTownUnclaimCommand(player, new String[] {});
        if (plugin.hasPlayerMode(player, "map"))
            TownyCommand.showMap(player);

        // claim: attempt to claim area
        // claim remove: remove area from town

        // Check if player has entered a new town/wilderness
        if (to.getTownyWorld().isUsingTowny() && TownySettings.getShowTownNotifications()) {
            ChunkNotification chunkNotifier = new ChunkNotification(from, to);
            String msg = chunkNotifier.getNotificationString();
            if (msg != null)
                player.sendMessage(msg);
        }

        if (plugin.hasPlayerMode(player, "plotborder")) {
            CellBorder cellBorder = BorderUtil.getPlotBorder(to);
            cellBorder.runBorderedOnSurface(1, 2, DrawSmokeTask.DEFAULT);
        }
    }
}
