package com.galacticdiamond.magma.supercraftbros.commands.fun;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.event.Listener;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

public class SetKnockbackCommand extends Command implements Listener {


    //TODO: MAKE SETKNOCKBACK MENTION A PLAYER ALSO


    private MagmaCore plugin;
    public SetKnockbackCommand(MagmaCore plugin) {
        super("setkb");
        this.setDescription("Use this command to set your knockback!");
        this.setUsage("/setkb <Amount>");
        this.plugin = plugin;
        commandParameters.clear();
        commandParameters.put("default", new CommandParameter[] {
                new CommandParameter("Player Name", CommandParamType.TARGET, false),
                new CommandParameter("Set Knockback", CommandParamType.FLOAT, false)
        });
        this.setCommandParameters(commandParameters);
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
                if(args.length >= 1) {
                    Player toGiveKB = plugin.getServer().getPlayer(args[0]);
                    if(toGiveKB == null) {
                        player.sendMessage(plugin.getCustomMessages().notPlayer);
                        return false;
                    } else {
                        double knockbackSetAmount;
                        try {
                            knockbackSetAmount = Double.parseDouble(args[1]);
                            if(knockbackSetAmount < 0.0001 || knockbackSetAmount > 10) {
                                player.sendMessage(TextFormat.RED + "Please specify a number in between 0.0001 and 10!");
                                return false;
                            } else {
                                plugin.getCustomHashMaps().knockbackAmount.put(toGiveKB.getUniqueId(), knockbackSetAmount);
                                player.sendMessage(TextFormat.GREEN + toGiveKB.getName() + "'s knockback has been set to: " + TextFormat.WHITE + knockbackSetAmount);
                                toGiveKB.sendMessage(TextFormat.GREEN + "Your knockback has been set to: " + TextFormat.WHITE + knockbackSetAmount);
                                return true;
                            }
                        } catch (NumberFormatException e) {
                            sender.sendMessage(TextFormat.RED + "That isn't a number!");
                            return false;
                        }
                    }
                } else {
                    sender.sendMessage(plugin.getCustomMessages().specifyPlayer);
                    return false;
                }
            } else {
                sender.sendMessage(plugin.getCustomMessages().noPermissions);
                return false;
            }
        } else {
            /**---------Console Perms-----------*/
            if(args.length >= 1) {
                Player toGiveKB = plugin.getServer().getPlayer(args[0]);
                if(toGiveKB == null) {
                    sender.sendMessage(plugin.getCustomMessages().notPlayer);
                    return false;
                } else {
                    double knockbackSetAmount;
                    try {
                        knockbackSetAmount = Double.parseDouble(args[1]);
                        if(knockbackSetAmount < 0.0001 || knockbackSetAmount > 10) {
                            sender.sendMessage(TextFormat.RED + "Please specify a number in between 0.0001 and 10!");
                            return false;
                        } else {
                            plugin.getCustomHashMaps().knockbackAmount.put(toGiveKB.getUniqueId(), knockbackSetAmount);
                            sender.sendMessage(TextFormat.GREEN + toGiveKB.getName() + "'s knockback has been set to: " + TextFormat.WHITE + knockbackSetAmount);
                            toGiveKB.sendMessage(TextFormat.GREEN + "Your knockback has been set to: " + TextFormat.WHITE + knockbackSetAmount);
                            return true;
                        }
                    } catch (NumberFormatException e) {
                        sender.sendMessage(TextFormat.RED + "That isn't a number!");
                        return false;
                    }
                }
            } else {
                sender.sendMessage(plugin.getCustomMessages().specifyPlayer);
            }
        }
        return false;
    }
}
