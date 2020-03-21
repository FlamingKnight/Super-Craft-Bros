package com.galacticdiamond.magma.supercraftbros.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

import java.nio.file.StandardCopyOption;

public class WarpPlayerToIsland extends Command {
    private MagmaCore plugin;
    public WarpPlayerToIsland(MagmaCore plugin) {
        super("island");
        this.setDescription("Go to your island!");
        this.setUsage("/<command>");
        this.setAliases(new String[]{"is"});
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            String playerUUIDStringified = String.valueOf(player.getUniqueId());
            plugin.getServer().loadLevel(playerUUIDStringified + "-IslandWorld");
            Level playerIsland = plugin.getServer().getLevelByName(playerUUIDStringified + "-IslandWorld");
            Vector3 spawn = new Vector3(0, 125, 0);
            playerIsland.setSpawnLocation(spawn);
            Location location = new Location(0, 127, 0, playerIsland);
            player.teleport(location);
        } else {
            sender.sendMessage(TextFormat.RED + "You aren't a player!");
        }
        return false;
    }
}
