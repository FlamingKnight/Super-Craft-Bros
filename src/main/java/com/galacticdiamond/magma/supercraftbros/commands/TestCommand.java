package com.galacticdiamond.magma.supercraftbros.commands;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

public class TestCommand extends Command implements Listener {

    private MagmaCore plugin;

    public TestCommand(MagmaCore plugin){
        super("test");
        this.setDescription("This is just a test, nothing to worry about");
        this.setUsage("/<command>");
        this.setAliases(new String[]{"te"});
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        sender.sendMessage(plugin.getCustomMessages().prefix + TextFormat.GREEN + "Successfully tested, thanks " + sender.getName() + "!");
        return false;
    }
}
