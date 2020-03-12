package com.galacticdiamond.magma.supercraftbros.events.loginandleave;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerPreLoginEvent;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.lists.CustomMessages;

public class PlayerBanBlockedMessages implements Listener {

    private CustomMessages customMessages = new CustomMessages();
    private MagmaCore plugin;
    public PlayerBanBlockedMessages(MagmaCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerPreLoginActions(PlayerPreLoginEvent ev) {
        //TODO: Add reasons for bans to appear
        Player player = ev.getPlayer();
        if(player.isBanned()) {
            player.kick(plugin.getCustomHashMaps().banReason.get(player.getUniqueId()));
        }
        if(plugin.getServer().hasWhitelist()) {
            if(!player.isWhitelisted()) {
                player.kick(TextFormat.RED + "You aren't on the whitelist!");
            }
        }
    }
}
