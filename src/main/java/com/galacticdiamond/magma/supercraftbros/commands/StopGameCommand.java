package com.galacticdiamond.magma.supercraftbros.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.event.Listener;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.lists.CustomLocations;

public class StopGameCommand extends Command implements Listener {

    public CustomLocations customLocations = new CustomLocations();
    private MagmaCore plugin;
    public StopGameCommand(MagmaCore plugin) {
        super("stopgame");
        this.setDescription("Stop a game!");
        this.setUsage("/stopgame <GameName>");
        this.plugin = plugin;
        commandParameters.clear();
        commandParameters.put("default", new CommandParameter[]{
                new CommandParameter("Game Name", CommandParamType.STRING, false)
        });
        this.setCommandParameters(commandParameters);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("owner") ||
                    plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("srmod") ||
                    plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("admin") ||
                    player.isOp()) {
                if(args.length != 0) {
                    if(args[0].equalsIgnoreCase("scb")) {
                        for (Player totalPlayers : plugin.getServer().getOnlinePlayers().values()) {
                            totalPlayers.teleport(customLocations.spawnLoc);
                            totalPlayers.getInventory().clearAll();
                            plugin.getCustomHashMaps().knockbackAmount.put(totalPlayers.getUniqueId(), 0.1);
                            plugin.getCustomHashMaps().playerLives.put(totalPlayers.getUniqueId(), 3);
                            totalPlayers.sendTitle(TextFormat.RED + "GAME OVER!!");
                        }
                        plugin.getCustomHashMaps().isGameOn.put("scb", false);
                        return true;
                    } else {
                        sender.sendMessage(TextFormat.RED + "Please specify a game!");
                        return false;
                    }
                } else {
                    sender.sendMessage(TextFormat.RED + "Please specify a game!");
                    return false;
                }
            } else {
                sender.sendMessage(plugin.getCustomMessages().noPermissions);
                return false;
            }
        } else {
            sender.sendMessage(TextFormat.RED + "You aren't a player!");
        }
        return false;
    }
}
