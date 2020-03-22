package com.galacticdiamond.magma.supercraftbros.events.loginandleave;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

public class CheckMovement implements Listener {
    private MagmaCore plugin;
    public CheckMovement(MagmaCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinSetMovementCheck(PlayerJoinEvent ev) {
        Player player = ev.getPlayer();
        player.setCheckMovement(false);
    }
}
