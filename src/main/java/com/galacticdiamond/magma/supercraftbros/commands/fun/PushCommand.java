package com.galacticdiamond.magma.supercraftbros.commands.fun;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.event.Listener;
import cn.nukkit.math.Vector3;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

public class PushCommand extends Command implements Listener {
    private MagmaCore plugin;
    public PushCommand(MagmaCore plugin) {
        super("push");
        this.setDescription("Push someone away from the direction their looking!");
        this.setUsage("/<command> <player>");
        this.plugin = plugin;
        commandParameters.clear();
        commandParameters.put("default", new CommandParameter[] {
                new CommandParameter("Player Name", CommandParamType.TARGET, false)
        });
        this.setCommandParameters(commandParameters);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("owner") ||
                    plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("srmod") ||
                    plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("admin") ||
                    player.isOp()) {
                if(args.length == 0) {
                    sender.sendMessage(TextFormat.RED + "You need to specify a player!");
                    return false;
                } else {
                    Player toPush = plugin.getServer().getPlayer(args[0]);
                    if(toPush == null) {
                        sender.sendMessage(TextFormat.RED + "That isn't a player!");
                        return false;
                    } else {
                        Vector3 toPushLookDirection = new Vector3(toPush.getDirectionVector().getX()*-5, toPush.getDirectionVector().getY()*-5, toPush.getDirectionVector().getZ()*-5);
                        toPush.setMotion(toPushLookDirection);
                        sender.sendMessage(TextFormat.GREEN + "Ha you pushed them!");
                        return true;
                    }
                }
            } else {
                sender.sendMessage(plugin.getCustomMessages().noPermissions);
            }
        } else {
            if(args.length == 0) {
                sender.sendMessage(TextFormat.RED + "You need to specify a player!");
                return false;
            } else {
                Player toPush = plugin.getServer().getPlayer(args[0]);
                if(toPush == null) {
                    sender.sendMessage(TextFormat.RED + "That isn't a player!");
                    return false;
                } else {
                    Vector3 toPushLookDirection = new Vector3(toPush.getDirectionVector().getX()*-5, toPush.getDirectionVector().getY()*-5, toPush.getDirectionVector().getZ()*-5);
                    toPush.setMotion(toPushLookDirection);
                    sender.sendMessage(TextFormat.GREEN + "Ha you pushed them!");
                }
            }
        }

        return false;
    }
}
