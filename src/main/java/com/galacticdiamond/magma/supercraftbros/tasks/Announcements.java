package com.galacticdiamond.magma.supercraftbros.tasks;

import cn.nukkit.scheduler.NukkitRunnable;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.lists.CustomMessages;

public class Announcements extends NukkitRunnable {

    private MagmaCore plugin;
    private CustomMessages cm = new CustomMessages();

    public Announcements(MagmaCore plugin) {
        this.plugin = plugin;
    }

    public int counter;

    @Override
    public void run() {
            if(counter >= 9) {
                counter = 0;
            }
            if(counter < 9) {
                switch(counter) {
                    case 0:
                        plugin.getServer().broadcastMessage("0");
                        break;
                    case 1:
                        plugin.getServer().broadcastMessage("1");
                        break;
                    case 2:
                        plugin.getServer().broadcastMessage("2");
                        break;
                    case 3:
                        plugin.getServer().broadcastMessage("3");
                        break;
                    case 4:
                        plugin.getServer().broadcastMessage("4");
                        break;
                    case 5:
                        plugin.getServer().broadcastMessage("5");
                        break;
                    case 6:
                        plugin.getServer().broadcastMessage("6");
                        break;
                    case 7:
                        plugin.getServer().broadcastMessage("7");
                        break;
                    case 8:
                        plugin.getServer().broadcastMessage("8");
                        break;
                    case 9:
                        plugin.getServer().broadcastMessage("9");
                        break;
                }
            }
        counter++;
    }
}
