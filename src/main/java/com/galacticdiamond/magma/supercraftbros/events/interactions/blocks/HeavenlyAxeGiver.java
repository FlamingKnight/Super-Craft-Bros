package com.galacticdiamond.magma.supercraftbros.events.interactions.blocks;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.lists.CustomItems;

public class HeavenlyAxeGiver implements Listener {

    private MagmaCore plugin;
    private CustomItems customItems = new CustomItems();
    public HeavenlyAxeGiver(MagmaCore plugin) {
        this.plugin  = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent ev) {
        PlayerInteractEvent.Action action = ev.getAction();
        Player player = ev.getPlayer();
        Block block = ev.getBlock();

        //Emerald give Heavenly Axe
        if (action.equals((PlayerInteractEvent.Action.LEFT_CLICK_BLOCK))) {
            if (block.getId() == (BlockID.EMERALD_BLOCK)) {
                player.getInventory().addItem(customItems.mjolnir);
            }
        }
    }
}
