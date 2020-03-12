package com.galacticdiamond.magma.supercraftbros;

import cn.nukkit.command.CommandMap;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.commands.*;
import com.galacticdiamond.magma.supercraftbros.commands.fun.*;
import com.galacticdiamond.magma.supercraftbros.commands.locations.*;
import com.galacticdiamond.magma.supercraftbros.commands.moderation.*;
import com.galacticdiamond.magma.supercraftbros.commands.playerstorage.*;
import com.galacticdiamond.magma.supercraftbros.commands.vanilla.*;
import com.galacticdiamond.magma.supercraftbros.events.*;
import com.galacticdiamond.magma.supercraftbros.events.blocksevents.*;
import com.galacticdiamond.magma.supercraftbros.events.chatting.*;
import com.galacticdiamond.magma.supercraftbros.events.interactions.*;
import com.galacticdiamond.magma.supercraftbros.events.loginandleave.*;
import com.galacticdiamond.magma.supercraftbros.events.motion.*;
import com.galacticdiamond.magma.supercraftbros.lists.*;
import com.galacticdiamond.magma.supercraftbros.tasks.*;

public class MagmaCore extends PluginBase implements Listener {

    public static MagmaCore plugin;
    private Announcements announce = new Announcements(this);
    private ItemSpawner itemSpawner = new ItemSpawner(this);
    public CustomHashMaps customHashMaps;
    public CustomMessages customMessages = new CustomMessages();
    private int sec = 20;
    private int min = sec * 60;
    private int hour = min * 60;
    private int day = hour * 24;



    //TODO: MAKE EVENT HANDLERS IN THEIR OWN PARTS
    //TODO: CUSTOM NAME TAGS
    //TODO: FINISH WARP
    //TODO: MORE COMMANDS SUCH AS VANISH
    //TODO: FIIIIIIIXXXXXXXXXX READING AND WRITING JACKSON
    //TODO: DELETE/REPLACE CURRENT NUKKIT DEFAULT COMMANDS!!!!!!!!!!!!!!!!!!!!!
    //TODO: WORK MORE ON AN API
    //TODO: ECONOMY
    //TODO: BOSSES
    //TODO: MAKE SURE ALL UNECCESARY IMPORTS ARE GONE
    //ALT + X IS THE MENU SHORTCUT






    //asdfnhjsdhlkgfujhbwhy TTTTTTTTTEESSSSSSSSSSSSTTTTTTT



    @Override
    public void onEnable() {
        plugin = this;
        registerCommands();
        registerEvents();
        loadConfig();
        customHashMaps = new CustomHashMaps();

        announce.counter = 0;
        plugin.getCustomHashMaps().isGameOn.put("scb", false);
        this.getLogger().info(TextFormat.GREEN + "\n\nThe plugin has been enabled successfully!\n\n");
        itemSpawner.runTaskTimer(this, sec, 15*sec);
    }

    private void loadConfig() {
        getConfig();
        saveConfig();
    }

    public static MagmaCore get() {
        return plugin;
    }

    public CustomHashMaps getCustomHashMaps() {
        return this.customHashMaps;
    }
    public CustomMessages getCustomMessages() {
        return this.customMessages;
    }

    public void onDisable() {
        this.getLogger().info(TextFormat.RED + "\n\nThe plugin has been Disabled!\n\n");
        saveConfig();
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinActions(this), this);
        pluginManager.registerEvents(new PlayerDeathActions(this), this);
        pluginManager.registerEvents(new PlayerMoveActions(this), this);
        pluginManager.registerEvents(new PlayerInteractionsActions(this), this);
        pluginManager.registerEvents(new PlayerPlaceAndBreakActions(this), this);
        pluginManager.registerEvents(new PlayerBanBlockedMessages(this), this);
        pluginManager.registerEvents(new LaunchPadEvent(this), this);
        pluginManager.registerEvents(new PlayerChatEvents(this), this);
        pluginManager.registerEvents(new PlayerDamageActions(this), this);
    }

    private void registerCommands() {
        CommandMap commandMap = getServer().getCommandMap();
        SimpleCommandMap simpleCommandMap = new SimpleCommandMap(this.getServer());
        simpleCommandMap.clearCommands();
        commandMap.register("test", new TestCommand(this));
        commandMap.register("clear", new ClearCommand(this));
        commandMap.register("push", new PushCommand(this));
        commandMap.register("rankgive", new RankCommand(this));
        commandMap.register("punish", new BanCommand(this));
        commandMap.register("unmute", new UnmuteCommand(this));
        commandMap.register("mute", new MuteCommand(this));
        commandMap.register("spawn", new SpawnCommand(this));
        commandMap.register("top", new TopCommand(this));
        commandMap.register("position", new PositionCommand(this));
        commandMap.register("startgame", new StartGameCommand(this));
        commandMap.register("stopgame", new StopGameCommand(this));
        commandMap.register("rib", new RemoveBedrockCommand(this));
        commandMap.register("setkb", new SetKnockbackCommand(this));
    }
}
