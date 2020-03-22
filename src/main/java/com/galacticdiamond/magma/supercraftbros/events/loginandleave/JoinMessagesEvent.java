package com.galacticdiamond.magma.supercraftbros.events.loginandleave;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

public class JoinMessagesEvent implements Listener {
    private MagmaCore plugin;
    public JoinMessagesEvent(MagmaCore plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onMessageWhenPlayerJoins(PlayerJoinEvent ev) {
        Player player = ev.getPlayer();

        if(!(player.hasPlayedBefore())) {
            ev.setJoinMessage(TextFormat.GRAY + "Everyone welcome " + TextFormat.GREEN + player.getName() + TextFormat.GRAY + " to the server for the first time!");
        } else {
            ev.setJoinMessage(TextFormat.GREEN + player.getName() + TextFormat.GRAY + ", has joined!");
        }
        player.sendMessage(TextFormat.GRAY + "Hello " + TextFormat.GREEN + player.getName() + TextFormat.GRAY + ", welcome to the Exotic Minecraft Server!");

    }
}
