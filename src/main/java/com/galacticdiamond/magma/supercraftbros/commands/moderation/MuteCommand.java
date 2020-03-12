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

public class MuteCommand extends Command implements Listener {

    private CustomMessages cm = new CustomMessages();
    private MagmaCore plugin;

    public MuteCommand(MagmaCore plugin) {
        super("mute");
        this.setDescription("Use this command to mute a player!");
        this.setUsage("/<command>");
        this.plugin = plugin;
        commandParameters.clear();
        commandParameters.put("default", new CommandParameter[] {
                new CommandParameter("Player Name", CommandParamType.TARGET, false),
                new CommandParameter("Mute Reason", CommandParamType.STRING, false)
        });
        this.setCommandParameters(commandParameters);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("owner") ||
                    plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("srmod") ||
                    plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("mod") ||
                    plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("admin") ||
            player.isOp()) {
                if (args.length == 0) {
                    sender.sendMessage(plugin.getCustomMessages().specifyPlayer);
                    return false;
                } else {
                    Player toMute = plugin.getServer().getPlayer(args[0]);
                    if (toMute == null) {
                        sender.sendMessage(plugin.getCustomMessages().notPlayer);
                        return false;
                    } else {
                        if (args.length == 1) {
                            sender.sendMessage(TextFormat.RED + "Please specify a reason!");
                            return false;
                        }
                        if (plugin.getCustomHashMaps().muted.get(toMute.getUniqueId())) {
                            sender.sendMessage(TextFormat.RED + "That person is already muted!");
                            return false;
                        } else {
                            sender.sendMessage(TextFormat.GREEN + "You have successfully muted: "
                                    + toMute.getName() + " for " + args[1]);
                            plugin.getCustomHashMaps().muted.put(toMute.getUniqueId(), true);
                            plugin.getCustomHashMaps().muteReason.putIfAbsent(toMute.getUniqueId(), args[1]);
                            toMute.sendMessage(TextFormat.RED + "You were muted by: " + player.getName()
                                    + " for " + args[1]);
                            return true;
                        }
                    }
                }
            } else {
                sender.sendMessage(plugin.getCustomMessages().noPermissions);
                return false;
            }
        }
        return false;
    }
}
