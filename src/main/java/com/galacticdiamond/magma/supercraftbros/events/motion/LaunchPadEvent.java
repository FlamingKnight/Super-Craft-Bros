package com.galacticdiamond.magma.supercraftbros.events.motion;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import cn.nukkit.math.Vector3;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

public class LaunchPadEvent implements Listener {

    private MagmaCore plugin;
    public LaunchPadEvent(MagmaCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEventofPlayer(PlayerInteractEvent ev) {
        //TODO: Check what's causing the error that's making everything freak out
        Player player = ev.getPlayer();
        Item item = ev.getItem();
        PlayerInteractEvent.Action action = ev.getAction();
        if(action.equals(PlayerInteractEvent.Action.PHYSICAL)) {
            Vector3 playerLaunch = new Vector3(player.getDirectionVector().getX()*2, 1, player.getDirectionVector().getZ()*2);
            player.setMotion(playerLaunch);
        }
    }
}
