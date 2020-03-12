package com.galacticdiamond.magma.supercraftbros.commands.locations;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.level.Location;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

public class TopCommand extends Command implements Listener {

    private MagmaCore plugin;
    public TopCommand(MagmaCore plugin) {
        super("top");
        this.setDescription("Use this command to go to the highest Y block!");
        this.setUsage("/<command>");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("owner") ||
                    plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("mod") ||
                    plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("srmod") ||
                    plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("admin") ||
                    player.isOp()) {
                int x = (int) (player.getX());
                int z = (int) (player.getZ());
                int topY = player.getLevel().getHighestBlockAt(x, z);
                Location top = new Location(x+0.5, topY+1, z+0.5);
                player.teleport(top);
                return true;
            } else {
                sender.sendMessage(TextFormat.RED + "You don't have permission to use this command!");
            }
        } else {
            sender.sendMessage(TextFormat.RED + "You aren't a player!");
        }
        return false;
    }
}
