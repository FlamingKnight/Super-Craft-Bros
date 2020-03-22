package com.galacticdiamond.magma.supercraftbros.events.loginandleave;

import cn.nukkit.AdventureSettings;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.inventory.Inventory;
import cn.nukkit.level.Location;
import cn.nukkit.network.protocol.SetLocalPlayerAsInitializedPacket;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.ReadAndWriteFunctions;
import com.galacticdiamond.magma.supercraftbros.lists.CustomLocations;
import com.galacticdiamond.magma.supercraftbros.lists.CustomMessages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FilesAndDirsRegistration implements Listener {

    private CustomMessages cm = new CustomMessages();
    private CustomLocations loc = new CustomLocations();
    private ReadAndWriteFunctions readAndWriteFunctions = new ReadAndWriteFunctions();

    private MagmaCore plugin;
    public FilesAndDirsRegistration(MagmaCore plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) throws IOException {
        Player player = ev.getPlayer();
        player.getInventory().clearAll();

        plugin.getCustomHashMaps().xpAmount.putIfAbsent(player.getUniqueId(), 0);
        plugin.getCustomHashMaps().muted.putIfAbsent(player.getUniqueId(), false);
        plugin.getCustomHashMaps().rank.putIfAbsent(player.getUniqueId(), "member");

        plugin.getCustomHashMaps().knockbackAmount.putIfAbsent(player.getUniqueId(), 0.1);
        plugin.getCustomHashMaps().playerLives.putIfAbsent(player.getUniqueId(), 3);
        plugin.getCustomHashMaps().hasDoubleJumped.putIfAbsent(player.getUniqueId(), false);
        plugin.getCustomHashMaps().hasDoubleJumped.put(player.getUniqueId(), false);

        player.setGamemode(0);
        player.setCheckMovement(false);
        player.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, true);
        player.getAdventureSettings().update();

        String playerUUIDStringified = String.valueOf(player.getUniqueId());

        //Creates a new directory for the player
        Path playerWorlds = Paths.get("worlds/" + playerUUIDStringified);
        Path playerData = Paths.get("scbdata/" + playerUUIDStringified);
        Files.createDirectories(playerWorlds);
        Files.createDirectories(playerData);

        File sourceWorld = new File("worlds/SkyblockIsland");
        File targetWorld = new File("worlds/" + playerUUIDStringified + "-IslandWorld");

        if(!targetWorld.exists()) {
            readAndWriteFunctions.copyFolders(sourceWorld, targetWorld);
        }
        plugin.getServer().loadLevel(playerUUIDStringified + "-IslandWorld");

        File rankFile = new File("scbdata/" + playerUUIDStringified + "/ranks.txt");
        if(!rankFile.exists()) {
            readAndWriteFunctions.writeFile(rankFile, "member");
        }

        //Ranks
        try {
            String rank = readAndWriteFunctions.readFile("scbdata/" + playerUUIDStringified + "/ranks.txt");
            String[] rankList = new String[]{"owner", "mod", "srmod", "asteroid", "diamond", "diamondstar"};
            for(String rankName : rankList) {
                if(rank.contains(rankName)) {
                    plugin.getCustomHashMaps().rank.put(player.getUniqueId(), rankName);
                }
            }
        } catch (Exception e) {
            plugin.getLogger().alert("Rank onPlayerJoin this came from, hmmmmhm");
        }
    }
}
