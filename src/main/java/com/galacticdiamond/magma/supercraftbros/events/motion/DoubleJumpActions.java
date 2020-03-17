package com.galacticdiamond.magma.supercraftbros.events.motion;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.event.player.PlayerToggleFlightEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.math.Vector3;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.PlayerActionPacket;
import cn.nukkit.network.protocol.ProtocolInfo;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

public class DoubleJumpActions implements Listener {

    private MagmaCore plugin;
    public DoubleJumpActions(MagmaCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerAttemptToJump(PlayerToggleFlightEvent ev) {
        Player player = ev.getPlayer();
        if(player.gamemode == 0) {
            if(!plugin.getCustomHashMaps().hasDoubleJumped.get(player.getUniqueId())) {
                plugin.getCustomHashMaps().hasDoubleJumped.put(player.getUniqueId(), true);
                Vector3 playerMovement = new Vector3(player.getDirectionVector().getX() * 2, 0.75, player.getDirectionVector().getZ() * 2);
                player.setMotion(playerMovement);
            }
        }
    }
}
