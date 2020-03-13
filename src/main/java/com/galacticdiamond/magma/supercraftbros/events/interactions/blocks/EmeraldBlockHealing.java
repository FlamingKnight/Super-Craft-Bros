package com.galacticdiamond.magma.supercraftbros.events.interactions.blocks;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

public class EmeraldBlockHealing implements Listener {

    private MagmaCore plugin;
    public EmeraldBlockHealing(MagmaCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent ev) {
        PlayerInteractEvent.Action action = ev.getAction();
        Player player = ev.getPlayer();
        Block block = ev.getBlock();

        //Emerald heals you
        if(action.equals(PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)) {
            if(block.getId() == BlockID.EMERALD_BLOCK){
                if(player.getHealth() < 20) {
                    player.setHealth(20);
                    player.sendMessage(plugin.getCustomMessages().prefix + TextFormat.GREEN + "You have been healed!");
                } else {
                    player.sendMessage(plugin.getCustomMessages().prefix + TextFormat.RED + "You're already at full health!");
                }
            }
        }
    }
}
