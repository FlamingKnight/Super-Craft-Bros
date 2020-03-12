package com.galacticdiamond.magma.supercraftbros.commands.vanilla;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

public class ClearCommand extends Command implements Listener {

    private MagmaCore plugin;
    public ClearCommand(MagmaCore plugin) {
        super("clear");
        this.setDescription("Clear your inventory!");
        this.setUsage("/<command>");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            player.getInventory().clearAll();
            return true;
        } else {
            sender.sendMessage(TextFormat.RED + "You aren't a player!");
        }
        return false;
    }
}
