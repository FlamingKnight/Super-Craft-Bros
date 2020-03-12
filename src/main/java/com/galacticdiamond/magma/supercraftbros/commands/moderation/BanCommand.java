package com.galacticdiamond.magma.supercraftbros.commands.moderation;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.event.Listener;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.lists.CustomMessages;

public class BanCommand extends Command implements Listener {

    private MagmaCore plugin;
    private CustomMessages cm = new CustomMessages();

    public BanCommand(MagmaCore plugin) {
        super("punish");
        this.setDescription("Use this command to ban a player!");
        this.setUsage("/<command>");
        this.plugin = plugin;
        commandParameters.clear();
        commandParameters.put("default", new CommandParameter[]{
                new CommandParameter("Player Name", CommandParamType.TARGET, false),
                new CommandParameter("Reason for Ban", CommandParamType.STRING, false)
        });
        this.setCommandParameters(commandParameters);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {


        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("owner") ||
                    plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("srmod") ||
                    plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("mod") ||
                    plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("admin") ||
                    player.isOp()) {
                if(args.length == 0) {
                    sender.sendMessage(plugin.getCustomMessages().specifyPlayer);
                    return false;
                } else {
                    Player toban = plugin.getServer().getPlayer(args[0]);
                    if(toban == null) {
                        sender.sendMessage(plugin.getCustomMessages().notPlayer);
                        return false;
                    } else {
                        if(args.length == 1) {
                            sender.sendMessage(TextFormat.RED + "Please specify a reason! (Must be more than 1 word!)");
                            return false;
                        } else {
                            if(toban.isBanned()) {
                                sender.sendMessage(TextFormat.RED + "That player is already banned!");
                                return false;
                            } else {
                                if(plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("owner")) {
                                    sender.sendMessage(TextFormat.GREEN + "You successfully banned: "
                                                        + toban.getName() + " for " + args[1]);
                                    toban.kick(TextFormat.RED + args[1]);
                                    toban.setBanned(true);
                                    String banMessage = (TextFormat.RED + "You were banned for: " + args[1] + " by " + player.getName());
                                    plugin.getCustomHashMaps().banReason.putIfAbsent(toban.getUniqueId(), banMessage);
                                    plugin.getCustomHashMaps().banReason.put(toban.getUniqueId(), args[1]);
                                    return true;
                                }
                                if(plugin.getCustomHashMaps().rank.get(toban.getUniqueId()).equalsIgnoreCase("owner") ||
                                   plugin.getCustomHashMaps().rank.get(toban.getUniqueId()).equalsIgnoreCase("srmod") ||
                                    plugin.getCustomHashMaps().rank.get(toban.getUniqueId()).equalsIgnoreCase("mod") ||
                                    plugin.getCustomHashMaps().rank.get(toban.getUniqueId()).equalsIgnoreCase("admin")) {
                                    sender.sendMessage(TextFormat.RED + "You can't ban other Staff members");
                                    return false;
                                } else {
                                    sender.sendMessage(TextFormat.GREEN + "You successfully banned: "
                                            + toban.getName() + " for " + args[1]);
                                    player.kick(TextFormat.RED + args[1]);
                                    player.setBanned(true);
                                    return true;
                                }
                            }
                        }
                    }
                }
            } else {
                sender.sendMessage(plugin.getCustomMessages().noPermissions);
                return false;
            }
        } else {

        }
        return false;
    }
}
