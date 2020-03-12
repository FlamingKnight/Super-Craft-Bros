package com.galacticdiamond.magma.supercraftbros.events.loginandleave;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.network.protocol.SetLocalPlayerAsInitializedPacket;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.lists.CustomLocations;
import com.galacticdiamond.magma.supercraftbros.lists.CustomMessages;


public class PlayerJoinActions implements Listener {

    private CustomMessages cm = new CustomMessages();
    private CustomLocations loc = new CustomLocations();

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
        player.setGamemode(0);

        player.setCheckMovement(false);

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
