package com.galacticdiamond.magma.supercraftbros.events.blocksevents;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.lists.CustomMessages;

public class PlayerPlaceAndBreakActions implements Listener {

    private MagmaCore plugin;
    private CustomMessages cm = new CustomMessages();

    public PlayerPlaceAndBreakActions(MagmaCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlace(BlockBreakEvent ev) {
        Block block = ev.getBlock();
        Player player = ev.getPlayer();
        if(!player.isOp()) {
            ev.setCancelled();
        }
    }
}
