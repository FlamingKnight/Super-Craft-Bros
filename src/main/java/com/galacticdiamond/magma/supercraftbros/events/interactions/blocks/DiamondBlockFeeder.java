package com.galacticdiamond.magma.supercraftbros.events.interactions.blocks;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

public class DiamondBlockFeeder implements Listener {

    private MagmaCore plugin;
    public DiamondBlockFeeder(MagmaCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent ev) {
        PlayerInteractEvent.Action action = ev.getAction();
        Player player = ev.getPlayer();
        Block block = ev.getBlock();

        if (action.equals((PlayerInteractEvent.Action.LEFT_CLICK_BLOCK))) {
            if (block.getId() == BlockID.DIAMOND_BLOCK) {
                if (player.getFoodData().getLevel() < 20) {
                    player.sendMessage(plugin.getCustomMessages().prefix + "You have been fed!");
                    player.getFoodData().setLevel(20);
                } else {
                    player.sendMessage(plugin.getCustomMessages().prefix + "You're already full!");
                }
            }
        }
    }
}
