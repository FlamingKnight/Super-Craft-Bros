package com.galacticdiamond.magma.supercraftbros.events.loginandleave;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerRespawnEvent;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.lists.CustomLocations;
import com.galacticdiamond.magma.supercraftbros.lists.CustomMessages;

public class PlayerDeathActions implements Listener {

    private CustomMessages cm = new CustomMessages();
    private CustomLocations customLocations = new CustomLocations();
    private MagmaCore plugin;
    public PlayerDeathActions(MagmaCore plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent ev) {
        //TODO: Check for specific cases on deaths
        Player player = ev.getEntity().getPlayer();
        ev.setDeathMessage(cm.prefix + TextFormat.DARK_RED + player.getName() + TextFormat.GRAY + " was killed by: " + TextFormat.DARK_RED + player.getKiller() + "!");
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent ev) {
        Player player = ev.getPlayer();
        ev.setRespawnPosition(customLocations.spawnLoc);
    }
}
