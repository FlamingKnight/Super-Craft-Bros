package com.galacticdiamond.magma.supercraftbros.events;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockFromToEvent;

import cn.nukkit.math.Vector3;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

public class OreGenerator implements Listener {

    private MagmaCore plugin;
    public OreGenerator(MagmaCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockForm(BlockFromToEvent ev) {
        int randomNumber = (int) (Math.random()*7);
        Block block = ev.getBlock();
        Block blockTo = ev.getTo();
        Vector3 blockPlaceLocation = new Vector3(block.getX(), block.getY(), block.getZ());
        if(blockTo.getId() == BlockID.COBBLE || blockTo.getId() == BlockID.STONE) {
            ev.setCancelled();
            switch (randomNumber) {
                case 0:
                    plugin.getServer().getLevel(block.getLevel().getId()).setBlock(blockPlaceLocation, Block.get(BlockID.DIAMOND_ORE));
                    break;
                case 1:
                    plugin.getServer().getLevel(block.getLevel().getId()).
                            setBlock(blockPlaceLocation, Block.get(BlockID.EMERALD_ORE));
                    break;
                case 2:
                    plugin.getServer().getLevel(block.getLevel().getId()).
                            setBlock(blockPlaceLocation, Block.get(BlockID.IRON_ORE));
                    break;
                case 3:
                    plugin.getServer().getLevel(block.getLevel().getId()).
                            setBlock(blockPlaceLocation, Block.get(BlockID.COAL_ORE));
                    break;
                case 4:
                    plugin.getServer().getLevel(block.getLevel().getId()).
                            setBlock(blockPlaceLocation, Block.get(BlockID.COBBLE));
                    break;
                case 5:
                    plugin.getServer().getLevel(block.getLevel().getId()).
                            setBlock(blockPlaceLocation, Block.get(BlockID.REDSTONE_ORE));
                    break;
                case 6:
                    plugin.getServer().getLevel(block.getLevel().getId()).
                            setBlock(blockPlaceLocation, Block.get(BlockID.LAPIS_ORE));
                    break;
                default:
                    plugin.getServer().broadcastMessage(TextFormat.RED + "Generator made another number!");
                    break;
            }
        }
    }
}
