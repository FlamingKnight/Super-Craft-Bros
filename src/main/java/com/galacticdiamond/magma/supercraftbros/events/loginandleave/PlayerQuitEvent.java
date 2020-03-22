package com.galacticdiamond.magma.supercraftbros.events.loginandleave;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

public class PlayerQuitEvent implements Listener {
    private MagmaCore plugin;
    public PlayerQuitEvent(MagmaCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeave(cn.nukkit.event.player.PlayerQuitEvent ev){
        Player player = ev.getPlayer();
        String playerUUIDStringified = String.valueOf(player.getUniqueId());
        if(player.getLevel().equals(plugin.getServer().getLevelByName(playerUUIDStringified + "-IslandWorld"))) {
            plugin.getCustomHashMaps().islandInventory.put(player.getUniqueId(), player.getInventory());
        }
        ev.setQuitMessage(plugin.getCustomMessages().prefix + TextFormat.RED + "Goodbye " + player.getName() + "!");
    }
}
