package com.galacticdiamond.magma.supercraftbros.events.loginandleave;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.level.Location;
import cn.nukkit.network.protocol.SetLocalPlayerAsInitializedPacket;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

public class TeleportOnJoin implements Listener {

    private MagmaCore plugin;
    public TeleportOnJoin(MagmaCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void whenPlayerJoins(DataPacketReceiveEvent ev) {
        final Player player = ev.getPlayer();

        if(ev.getPacket() instanceof SetLocalPlayerAsInitializedPacket) {
            Location spawnLoc = new Location(73, 89, 140, plugin.getServer().getLevelByName("world"));
            player.teleport(spawnLoc);
        }
    }
}
