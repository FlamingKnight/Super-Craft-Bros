package com.galacticdiamond.magma.supercraftbros.events.chatting;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.lists.CustomMessages;

public class PlayerChatEvents implements Listener {

    private CustomMessages cm = new CustomMessages();

    private MagmaCore plugin;
    public PlayerChatEvents(MagmaCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(PlayerChatEvent ev) {
        //TODO: Add Staff prefixes
        Player player = ev.getPlayer();
        boolean isMuted = plugin.getCustomHashMaps().muted.get(player.getUniqueId());
        String hasRank = plugin.getCustomHashMaps().rank.get(player.getUniqueId());

        if(isMuted) {
            ev.setCancelled(true);
            player.sendMessage(TextFormat.RED + "You were muted for: " + plugin.getCustomHashMaps().muteReason.get(player.getUniqueId()));
            return;
        }

        if(ev.getMessage().contains("fuck")) {
            player.sendMessage("DO NOT SWEAR");
            ev.setCancelled();
        } else {
            if(hasRank.equals("member")) {
                plugin.getServer().broadcastMessage(cm.memberPrefix + player.getName() + ": " + ev.getMessage());
                ev.setCancelled();
            } else if(hasRank.equals("mod")) {
                plugin.getServer().broadcastMessage(cm.modPrefix + player.getName() + ": " + ev.getMessage());
                ev.setCancelled();
            } else if(hasRank.equals("srmod")) {
                plugin.getServer().broadcastMessage(cm.srModPrefix + player.getName() + ": " + ev.getMessage());
                ev.setCancelled();
            } else if(hasRank.equals("owner")) {
                plugin.getServer().broadcastMessage(cm.ownerPrefix + player.getName() + ": " + ev.getMessage());
                ev.setCancelled();
            } else if(hasRank.equals("diamondstar")) {
                plugin.getServer().broadcastMessage(cm.diamondStarPrefix + player.getName() + ": " + ev.getMessage());
                ev.setCancelled();
            } else if(hasRank.equals("diamond")) {
                plugin.getServer().broadcastMessage(cm.diamondPrefix + player.getName() + ": " + ev.getMessage());
                ev.setCancelled();
            } else if(hasRank.equals("asteroid")) {
                plugin.getServer().broadcastMessage(cm.asteroidPrefix + player.getName() + ": " + ev.getMessage());
                ev.setCancelled();
            } else {
                ev.setCancelled();
                player.sendMessage(TextFormat.RED + "Hmmm, something went wrong! Please report this immediately to a Developer!!!");
            }
        }
    }
}
