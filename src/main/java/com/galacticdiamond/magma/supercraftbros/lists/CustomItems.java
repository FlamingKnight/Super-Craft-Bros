package com.galacticdiamond.magma.supercraftbros.lists;

import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.item.*;
import cn.nukkit.utils.TextFormat;

import static cn.nukkit.item.ItemID.*;

public class CustomItems implements Listener {

    public CustomMessages customMessages = new CustomMessages();

    public Item welcomeBook = Item.get(WRITTEN_BOOK).setCustomName(TextFormat.RESET + "" + TextFormat.BLUE + "Welcome!").
                            setLore(TextFormat.RESET + "" + TextFormat.GREEN + "\n This book will help you get started with \n Information about the Exotic Minecraft Server!");
    public Item spawnCompass = Item.get(COMPASS).setCustomName(TextFormat.RESET + "" + TextFormat.RED + "Spawn Compass");
    public Item teamSelector = Item.get(NETHER_STAR).setCustomName(TextFormat.RESET + "" + TextFormat.RED + "Capture the Flag");

    public Item blunderbuss = Item.get(DIAMOND_HORSE_ARMOR).setCustomName(TextFormat.RED + "" + TextFormat.BOLD + "BLUNDERBUSS");
    public Item akGun = Item.get(GOLD_HORSE_ARMOR).setCustomName(TextFormat.GOLD + "" + TextFormat.BOLD + "AK47");

    public Item heavenlyAxe = Item.get(DIAMOND_AXE).setCustomName(TextFormat.BLUE + "Heavenly Axe")
                            .setLore(TextFormat.RED + "The almighty weapon wielded to protect the Heavens\n" + TextFormat.GREEN + "This was the first weapon developed!");

    public Item mjolnir = Item.get(DIAMOND_AXE).
            setCustomName(customMessages.mjolnirPrefix)
            .setLore(TextFormat.AQUA + "THE ALMIHGTY HAMMER OF" + TextFormat.RED + " THOR " + TextFormat.AQUA + "\nTO DEFEAT OTHER GODS");

    public Item fragGrenade = Item.get(SLIMEBALL).
            setCustomName(customMessages.grenadePrefix).
            setLore(TextFormat.DARK_GREEN + "A casual " + TextFormat.RED + "Fragmentation Grenade " + TextFormat.GREEN + "\nto knock your enemies down");

    public void giveItems(Player player) {
        heavenlyAxe.setDamage(heavenlyAxe.getDamage() * 10);
        player.getInventory().addItem(heavenlyAxe);
    }
}
