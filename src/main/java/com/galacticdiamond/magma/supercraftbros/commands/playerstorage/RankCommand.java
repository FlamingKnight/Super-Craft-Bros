package com.galacticdiamond.magma.supercraftbros.commands.playerstorage;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.event.Listener;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.ReadAndWriteFunctions;

import java.io.File;

public class RankCommand extends Command implements Listener {

    private ReadAndWriteFunctions readAndWriteFunctions = new ReadAndWriteFunctions();
    private MagmaCore plugin;
    public RankCommand(MagmaCore plugin) {
        super("rankgive", "Use this to give a player a rank!", "/rankgive <Rank Name>");
        this.setDescription("Give a player a special rank!");
        this.setUsage("/<command> <Player Name> <Rank Name>");
        this.plugin = plugin;
        commandParameters.clear();
        commandParameters.put("default", new CommandParameter[] {
                new CommandParameter("Player Name", CommandParamType.TARGET, false),
                new CommandParameter("Rank Name", CommandParamType.STRING, false)
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
                if(args.length != 1 && args.length != 2) {
                    sender.sendMessage(TextFormat.RED + "Please enter a player's name!");
                    return false;
                } else {
                    Player toGiveRank = this.plugin.getServer().getPlayer(args[0]);
                    if(toGiveRank == null) {
                        sender.sendMessage("That isn't a player!");
                        return false;
                    } else if(args.length == 1) {
                        sender.sendMessage("You didn't enter a valid rank! Please use /rankslist to see the seperate ranks!");
                        return false;
                    } else {
                        String playerUUIDStringified = String.valueOf(player.getUniqueId());
                        String rankName;
                        if(args[1].equalsIgnoreCase("mod")) {
                            rankName = "mod";
                            plugin.getCustomHashMaps().rank.put(toGiveRank.getUniqueId(), "mod");
                            sender.sendMessage("Gave " + toGiveRank.getName() + " the Moderator role!");
                            toGiveRank.sendMessage(TextFormat.GREEN + "Congratulations " + toGiveRank.getName() + "! You recieved the Moderator role!");
                            File file = new File("scbdata/" + playerUUIDStringified + "/ranks.txt");
                            readAndWriteFunctions.writeFile(file, rankName);
                        } else if(args[1].equalsIgnoreCase("srmod")) {
                            rankName = "srmod";
                            plugin.getCustomHashMaps().rank.put(toGiveRank.getUniqueId(), "srmod");
                            sender.sendMessage("Gave " + toGiveRank.getName() + " the Senior Moderator role!");
                            toGiveRank.sendMessage(TextFormat.GREEN + "Congratulations " + toGiveRank.getName() + "! You recieved the Senior Moderator role!");
                            File file = new File("scbdata/" + playerUUIDStringified + "/ranks.txt");
                            readAndWriteFunctions.writeFile(file, rankName);
                        } else if(args[1].equalsIgnoreCase("owner")) {
                            rankName = "owner";
                            plugin.getCustomHashMaps().rank.put(toGiveRank.getUniqueId(), "owner");
                            sender.sendMessage("Gave " + toGiveRank.getName() + " the Owner role!");
                            toGiveRank.sendMessage(TextFormat.GREEN + "Congratulations " + toGiveRank.getName() + "! You recieved the Owner role!");
                            File file = new File("scbdata/" + playerUUIDStringified + "/ranks.txt");
                            readAndWriteFunctions.writeFile(file, rankName);
                        } else if(args[1].equalsIgnoreCase("diamondstar")) {
                            rankName = "diamond star";
                            plugin.getCustomHashMaps().rank.put(toGiveRank.getUniqueId(), "diamondstar");
                            sender.sendMessage("Gave " + toGiveRank.getName() + " the Diamond Star role!");
                            toGiveRank.sendMessage("Congratulations " + toGiveRank.getName() + "! You recieved the Diamond Star role!");
                            File file = new File("scbdata/" + playerUUIDStringified + "/ranks.txt");
                            readAndWriteFunctions.writeFile(file, rankName);
                        } else if(args[1].equalsIgnoreCase("diamond")) {
                            rankName = "diamond";
                            plugin.getCustomHashMaps().rank.put(toGiveRank.getUniqueId(), "diamond");
                            sender.sendMessage("Gave " + toGiveRank.getName() + " the Diamond role!");
                            toGiveRank.sendMessage("Congratulations " + toGiveRank.getName() + "! You recieved the Diamond role!");
                            File file = new File("scbdata/" + playerUUIDStringified + "/ranks.txt");
                            readAndWriteFunctions.writeFile(file, rankName);
                        } else if(args[1].equalsIgnoreCase("asteroid")) {
                            rankName = "asteroid";
                            plugin.getCustomHashMaps().rank.put(toGiveRank.getUniqueId(), "asteroid");
                            sender.sendMessage("Gave " + toGiveRank.getName() + " the Asteroid role!");
                            toGiveRank.sendMessage("Congratulations " + toGiveRank.getName() + "! You recieved the Asteroid role!");
                            File file = new File("scbdata/" + playerUUIDStringified + "/ranks.txt");
                            readAndWriteFunctions.writeFile(file, rankName);
                        } else {
                            sender.sendMessage(TextFormat.RED + "You didn't enter a correct rank name!");
                            return false;
                        }
                        return true;
                    }
                }
            } else  {
                sender.sendMessage(plugin.getCustomMessages().noPermissions);
            }
        } else {
            sender.sendMessage(TextFormat.RED + "You aren't a player!");
        }
        return false;
    }
}
