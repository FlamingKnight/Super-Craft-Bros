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

public class StartGameCommand extends Command implements Listener {

    public CustomLocations customLocations = new CustomLocations();
    private MagmaCore plugin;
    public StartGameCommand(MagmaCore plugin) {
        super("startgame");
        this.setDescription("Start a game!");
        this.setUsage("/startgame <GameName>");
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
                            plugin.getCustomHashMaps().knockbackAmount.put(totalPlayers.getUniqueId(), 0.1);
                            totalPlayers.teleport(customLocations.scbStart);
                            totalPlayers.sendTitle(TextFormat.GREEN + "" + TextFormat.BOLD + "GO!", TextFormat.GREEN + "Knockback is above your hotbar!");
                            plugin.getCustomHashMaps().playerLives.put(totalPlayers.getUniqueId(), 3);
                        }
                        plugin.getCustomHashMaps().isGameOn.putIfAbsent("scb", true);
                        plugin.getCustomHashMaps().isGameOn.put("scb", true);
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
