package com.galacticdiamond.magma.supercraftbros.commands.locations;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.level.Location;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.lists.CustomLocations;

public class SpawnCommand extends Command implements Listener {

    private CustomLocations loc = new CustomLocations();

    private MagmaCore plugin;
    public SpawnCommand(MagmaCore plugin) {
        super("spawn");
        this.setDescription("Use this to get to spawn!");
        this.setUsage("/<command>");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            Location spawnLoc = new Location(73, 89, 140, plugin.getServer().getLevelByName("world"));
            player.teleport(spawnLoc);
            return true;
        } else {
            sender.sendMessage(TextFormat.RED + "You aren't a player!");
        }
        return false;
    }


}
