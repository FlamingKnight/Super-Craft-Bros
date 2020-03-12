/**package com.galacticdiamond.magma.supercraftbros.commands.locations;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.lists.CustomLocations;
import com.galacticdiamond.magma.supercraftbros.lists.CustomMessages;

public class WarpCommand extends Command implements Listener {

    private MagmaCore plugin;
    private CustomMessages cm = new CustomMessages();
    private CustomLocations loc = new CustomLocations();

    public WarpCommand(MagmaCore plugin) {
        super("warp", "Warp to a location!", "/warp <Maybe location soon>", new String[]{"w"});
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                player.sendMessage(TextFormat.RED + "Please specify a location! You can see the mby using: "
                + TextFormat.WHITE + "/warp lists");
                return false;
            }
            switch (args[0]) {
                case "home":
                    player.teleport(plugin.getCustomHashMaps().playerHome.get(player.getUniqueId()));
                    break;
                case "spawn":
                    player.teleport(loc.spawn);
                    break;
                case "shop":
                    player.teleport(loc.shop);
                    break;
                case "list":
                case "lists":
                    player.sendMessage(cm.prefix + TextFormat.GREEN + "You can teleport to: " + TextFormat.GRAY + "spawn, home, or pvp!");
                    break;
                case "pvp":
                    player.teleport(loc.pvp);
                    break;
                default:
                    player.sendMessage(cm.prefix + TextFormat.RED + "You didn't enter a valid location! Use /locations or /warps list to see our warps! You entered: " + TextFormat.WHITE + args[0]);
                    break;
            }
        } else {
            sender.sendMessage(TextFormat.RED + "You aren't a player!");
        }
        return false;
    }
}*/
