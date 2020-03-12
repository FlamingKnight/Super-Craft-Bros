package com.galacticdiamond.magma.supercraftbros.events.motion;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.lists.CustomLocations;

public class PlayerMoveActions implements Listener {

    private MagmaCore plugin;
    private CustomLocations customLocations = new CustomLocations();
    public static String format;
    //private ArrayList<Player> jumpers = new ArrayList<Player>();

    public PlayerMoveActions(MagmaCore plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent ev) {
        Player player = ev.getPlayer();
        player.getFoodData().setLevel(20);
        String knockbackNumber = String.valueOf(plugin.getCustomHashMaps().knockbackAmount.get(player.getUniqueId()));
        player.sendActionBar(TextFormat.RED + knockbackNumber);
    }

    @EventHandler
    public void whenUnderVoid(PlayerMoveEvent ev) {
        Player player = ev.getPlayer();
        if(player.getY() < 20) {
            player.teleport(customLocations.spawnLoc);
        }
    }

    @EventHandler
    public void whenUnderPlatform(PlayerMoveEvent ev) {
        Player player = ev.getPlayer();
        if(player.getY() < 75) {
             int lives = plugin.getCustomHashMaps().playerLives.get(player.getUniqueId());
             if(lives > 0) {
                 plugin.getCustomHashMaps().knockbackAmount.put(player.getUniqueId(), 0.1);
                 plugin.getCustomHashMaps().playerLives.put(player.getUniqueId(), lives-1);
                 player.sendTitle(TextFormat.RED + "YOU LOST A LIFE!", TextFormat.RED + "You have " + lives + " left!");
                 player.teleport(customLocations.scbStart);
             } else {
                 player.teleport(customLocations.spawnLoc);
                 player.sendTitle(TextFormat.DARK_RED + "" + TextFormat.BOLD + "YOU RAN OUT OF LIVES!", TextFormat.RED + "You may spectate from spawn!");
                 plugin.getCustomHashMaps().knockbackAmount.put(player.getUniqueId(), 0.1);
             }
        }
    }
}
