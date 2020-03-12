package com.galacticdiamond.magma.supercraftbros.events.interactions;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerInteractEvent.Action;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.lists.CustomLocations;
import com.galacticdiamond.magma.supercraftbros.lists.CustomMessages;

public class PlayerInteractionsActions implements Listener {

    //private CustomItems ci = new CustomItems();
    private CustomMessages cm = new CustomMessages();
    private CustomLocations loc = new CustomLocations();

    private MagmaCore plugin;
    public PlayerInteractionsActions(MagmaCore plugin){
        this.plugin = plugin;
    }

    /*@EventHandler
    public void spawnForm(PlayerFormRespondedEvent ev) {
        Player player = ev.getPlayer();
        FormWindow window = ev.getWindow();

        if (ev.getResponse() == null) return;

        if (window instanceof FormWindowSimple) {
            String title = ((FormWindowSimple) ev.getWindow()).getTitle();
            String button = ((FormResponseSimple) ev.getResponse()).getClickedButton().getText();
            if (!ev.wasClosed()) {
                if (title.equals("Hello")) {
                    if (button.equals("Warp to the shop!")) {
                        player.teleport(loc.shop);
                    } else if (button.equals("Test")) {
                        player.sendMessage(cm.prefix + "Successfully tested, thanks " + player.getName() + "!");
                    } else if (button.equals("Capture the Flag")) {
                        player.teleport(loc.ctfLobby);
                        player.getInventory().setItem(8, ci.teamSelector);
                    }
                }
            }
        }
    }*/

    @EventHandler
    public void onInteract(PlayerInteractEvent ev) {
        Action action = ev.getAction();
        Player player = ev.getPlayer();
        Block block = ev.getBlock();
        Item itemHeld = ev.getItem();

        FormWindowSimple spawnForm = new FormWindowSimple("Hello", "this is a test!");
        spawnForm.addButton(new ElementButton("Test"));
        spawnForm.addButton(new ElementButton("Warp to the shop!"));
        spawnForm.addButton(new ElementButton("Capture the Flag"));

        /*if(itemHeld.equals(ci.spawnCompass)) {
            player.showFormWindow(spawnForm);
        }*/

        //Emerald give Heavenly Axe
        /*if(action.equals((Action.LEFT_CLICK_BLOCK))) {
            if(block.getId() == (BlockID.EMERALD_BLOCK)) {
                ci.giveItems(player);
            }
        }*/

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
