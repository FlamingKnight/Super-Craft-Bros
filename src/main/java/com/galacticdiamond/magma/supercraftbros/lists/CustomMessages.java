package com.galacticdiamond.magma.supercraftbros.lists;

import cn.nukkit.event.Listener;
import cn.nukkit.utils.TextFormat;

public class CustomMessages implements Listener {
    public String prefix = (TextFormat.DARK_GRAY + "[" + TextFormat.DARK_RED + "Galactic Diamond" + TextFormat.DARK_GRAY + "] ");

    public String banMessage = (TextFormat.RED + "You're already banned!");

    public String memberPrefix = (TextFormat.GRAY + "[Member] ");
    public String modPrefix = (TextFormat.DARK_GRAY + "[" + TextFormat.GREEN + "MOD" + TextFormat.DARK_GRAY + "] " + TextFormat.GREEN);
    public String srModPrefix = (TextFormat.DARK_GRAY + "[" + TextFormat.DARK_GREEN + "SR. MOD" + TextFormat.DARK_GRAY + "] " + TextFormat.DARK_GREEN);
    public String ownerPrefix = (TextFormat.DARK_GRAY + "[" + TextFormat.AQUA + TextFormat.BOLD + "OWNER" + TextFormat.DARK_GRAY + "] " + TextFormat.RED);
    public String diamondStarPrefix = (TextFormat.DARK_GRAY + "[" + TextFormat.AQUA + "Diamond Star" + TextFormat.DARK_GRAY + "] " + TextFormat.AQUA);
    public String diamondPrefix = (TextFormat.DARK_GRAY + "[" + TextFormat.BLUE + "Diamond" + TextFormat.DARK_GRAY + "] " + TextFormat.BLUE );
    public String asteroidPrefix = (TextFormat.DARK_GRAY + "[" + TextFormat.GOLD + "Asteroid" + TextFormat.DARK_GRAY + "] " + TextFormat.GOLD );

    public String noPermissions = (TextFormat.RED + "You don't have permission to use this command!");

    public String specifyPlayer = (TextFormat.RED + "Please enter a player's name!");
    public String notPlayer = (TextFormat.RED + "That isn't a player!");
    /**------------------Announcements----------------------*/


    /**------------------Item Names------------------------*/
    public String mjolnirPrefix = (TextFormat.RESET + "" + TextFormat.BLUE + "" + TextFormat.BOLD + "MJOLNIR");
    public String grenadePrefix = (TextFormat.RESET + "" + TextFormat.RED + "Frag Grenade");

    /* TODO: Add Staff Ranks
    public String TraineePrefix
    public String HelperPrefix
    public String ModPrefix
    public String DeveloperPrefix
    public String AdminPrefix
    public String OwnerPrefix
     */
}
