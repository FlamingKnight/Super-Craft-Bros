package com.galacticdiamond.magma.supercraftbros.tasks;

import cn.nukkit.scheduler.NukkitRunnable;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

public class SetDay extends NukkitRunnable {

    private MagmaCore plugin;
    public SetDay(MagmaCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        plugin.getServer().getLevelByName("world").setTime(6000);
    }
}
