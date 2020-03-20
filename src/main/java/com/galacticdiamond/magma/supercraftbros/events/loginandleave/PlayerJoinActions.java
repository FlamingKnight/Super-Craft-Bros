package com.galacticdiamond.magma.supercraftbros.events.loginandleave;

import cn.nukkit.AdventureSettings;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.network.protocol.SetLocalPlayerAsInitializedPacket;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.ReadAndWriteFunctions;
import com.galacticdiamond.magma.supercraftbros.lists.CustomLocations;
import com.galacticdiamond.magma.supercraftbros.lists.CustomMessages;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;


public class PlayerJoinActions implements Listener {

    private CustomMessages cm = new CustomMessages();
    private CustomLocations loc = new CustomLocations();
    private ReadAndWriteFunctions readAndWriteFunctions = new ReadAndWriteFunctions();

    private MagmaCore plugin;
    public PlayerJoinActions(MagmaCore plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        Player player = ev.getPlayer();
        player.getInventory().clearAll();

        plugin.getCustomHashMaps().xpAmount.putIfAbsent(player.getUniqueId(), 0);
        plugin.getCustomHashMaps().muted.putIfAbsent(player.getUniqueId(), false);
        plugin.getCustomHashMaps().moneyAmount.putIfAbsent(player.getUniqueId(), 0);
        plugin.getCustomHashMaps().rank.putIfAbsent(player.getUniqueId(), "member");
        plugin.getCustomHashMaps().playerHome.putIfAbsent(player.getUniqueId(), loc.spawn);
        plugin.getCustomHashMaps().knockbackAmount.putIfAbsent(player.getUniqueId(), 0.1);
        plugin.getCustomHashMaps().playerLives.putIfAbsent(player.getUniqueId(), 3);
        plugin.getCustomHashMaps().hasDoubleJumped.putIfAbsent(player.getUniqueId(), false);
        plugin.getCustomHashMaps().hasDoubleJumped.put(player.getUniqueId(), false);
        player.setGamemode(0);

        player.setCheckMovement(false);
        player.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, true);
        player.getAdventureSettings().update();

        try {
            String playerUUIDStringified = String.valueOf(player.getUniqueId());
            String rank = readAndWriteFunctions.readFile("scbdata/" + playerUUIDStringified + ".txt");
            String[] rankList = new String[]{"owner", "mod", "srmod", "asteroid", "diamond", "diamondstar"};
            for(String rankName : rankList) {
                if(rank.contains(rankName)) {
                    plugin.getCustomHashMaps().rank.put(player.getUniqueId(), rankName);
                }
            }
        } catch (Exception e) {
            String playerUUIDStringified = String.valueOf(player.getUniqueId());
            File file = new File("scbdata/" + playerUUIDStringified + ".txt");
            readAndWriteFunctions.writeFile(file, "Rank: member");
        }

        if(!(player.hasPlayedBefore())) {
            ev.setJoinMessage(cm.prefix + TextFormat.GRAY + "Everyone welcome " + TextFormat.GREEN + player.getName() + TextFormat.GRAY + " to the server for the first time!");
        } else {
            ev.setJoinMessage(cm.prefix + TextFormat.GREEN + player.getName() + TextFormat.GRAY + ", has joined!");
        }
        player.sendMessage(cm.prefix + TextFormat.GRAY + "Hello " + TextFormat.GREEN + player.getName() + TextFormat.GRAY + ", welcome to the Exotic Minecraft Server!");
    }

    //Teleports player to Spawn
    @EventHandler
    public void onPacketReceive( DataPacketReceiveEvent e ) {
        final Player player = e.getPlayer();

        if(e.getPacket() instanceof SetLocalPlayerAsInitializedPacket) {
            player.teleport(loc.spawnLoc);
        }
    }

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent ev){
        Player player = ev.getPlayer();
        ev.setQuitMessage(cm.prefix + TextFormat.RED + "Goodbye " + player.getName() + "!");
    }

    @EventHandler
    public void onJoinSetMovementCheck(PlayerJoinEvent ev) {
        Player player = ev.getPlayer();
        player.setCheckMovement(false);
    }
}
