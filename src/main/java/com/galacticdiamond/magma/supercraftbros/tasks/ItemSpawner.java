package com.galacticdiamond.magma.supercraftbros.tasks;

import cn.nukkit.level.Location;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.lists.CustomItems;

public class ItemSpawner extends NukkitRunnable {

    private MagmaCore plugin;
    private CustomItems customItems = new CustomItems();
    public ItemSpawner(MagmaCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        int randomX = (int) (Math.random() * 24);
        int randomZ = (int) (Math.random() * 22);
        int positionX = randomX+44;
        int positionZ = randomZ+128;
        int yPosition = 100;
        Location spawnPosition = new Location(positionX, yPosition, positionZ);
        if(!plugin.getCustomHashMaps().isGameOn.get("scb")) {
            return;
        }
        int random = (int) (Math.random() * 1);
            switch (random) {
                case 0:
                    plugin.getServer().getLevelByName("world").dropItem(spawnPosition, customItems.mjolnir);
                    break;
                case 1:
                    plugin.getServer().getLevelByName("world").dropItem(spawnPosition, customItems.fragGrenade);
                    break;
                default:
                    plugin.getServer().broadcastMessage(TextFormat.RED + "Something went wrong!");
                    break;
            }
    }
}
