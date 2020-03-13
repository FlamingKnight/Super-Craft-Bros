package com.galacticdiamond.magma.supercraftbros.events.interactions;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerInteractEvent.Action;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.lists.CustomItems;
import com.galacticdiamond.magma.supercraftbros.lists.CustomLocations;
import com.galacticdiamond.magma.supercraftbros.lists.CustomMessages;

public class PlayerInteractionsActions implements Listener {

    private CustomItems customItems = new CustomItems();
    private CustomMessages cm = new CustomMessages();
    private CustomLocations loc = new CustomLocations();

    private MagmaCore plugin;
    public PlayerInteractionsActions(MagmaCore plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent ev) {
        Action action = ev.getAction();
        Player player = ev.getPlayer();
        Block block = ev.getBlock();
        Item itemHeld = ev.getItem();

        //Diamond Block Feeds
        if(action.equals((Action.LEFT_CLICK_BLOCK))) {
            if(block.getId() == BlockID.DIAMOND_BLOCK) {
                if(player.getFoodData().getLevel() < 20) {
                    player.sendMessage(cm.prefix + "You have been fed!");
                    player.getFoodData().setLevel(20);
                } else {
                    player.sendMessage(cm.prefix + "You're already full!");
                }
            }
        }

        //Emerald heals you
        if(action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if(block.getId() == BlockID.EMERALD_BLOCK){
                if(player.getHealth() < 20) {
                    player.setHealth(20);
                    player.sendMessage(cm.prefix + TextFormat.GREEN + "You have been healed!");
                } else {
                    player.sendMessage(cm.prefix + TextFormat.RED + "You're already at full health!");
                }
            }
        }
    }
}
