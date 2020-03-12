package com.galacticdiamond.magma.supercraftbros.commands;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

public class RemoveBedrockCommand extends Command implements Listener {
    private MagmaCore plugin;
    public RemoveBedrockCommand(MagmaCore plugin) {
        super("rib");
        this.setDescription("Remove invisible bedrock!");
        this.setUsage("/rib");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if (plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("owner") ||
                    plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("srmod") ||
                    plugin.getCustomHashMaps().rank.get(player.getUniqueId()).equalsIgnoreCase("admin") ||
                    player.isOp()) {
                Block air = Block.get(BlockID.AIR);
                Level playerLevel = player.getLevel();
                Vector3 breakBlockLocation = new Vector3(player.getX(), player.getY()-1, player.getZ());
                playerLevel.setBlock(breakBlockLocation, air);
                return true;
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
