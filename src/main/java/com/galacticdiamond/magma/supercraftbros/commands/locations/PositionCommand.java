package com.galacticdiamond.magma.supercraftbros.commands.locations;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.level.Location;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

public class PositionCommand extends Command implements Listener {

    private MagmaCore plugin;

    public PositionCommand(MagmaCore plugin){
        super("position");
        this.setDescription("Use this to check your position!");
        this.setUsage("/<command>");
        this.setAliases(new String[]{"pos"});
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if(sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            Location playerLocation = player.getLocation();
            sender.sendMessage(plugin.getCustomMessages().prefix + TextFormat.GOLD + "Your coordinates are: " + "\n" + TextFormat.BLUE + "X: " + TextFormat.WHITE + playerLocation.getX() + "\n" + TextFormat.BLUE + "Y: " + TextFormat.WHITE + playerLocation.getY() + "\n" + TextFormat.BLUE + "Z: " + TextFormat.WHITE + playerLocation.getZ());
            return true;
        } else {
            sender.sendMessage(TextFormat.RED + "You aren't a player!");
        }
        return false;
    }
}
