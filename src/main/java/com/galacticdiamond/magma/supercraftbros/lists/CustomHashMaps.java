package com.galacticdiamond.magma.supercraftbros.lists;

import cn.nukkit.event.Listener;
import cn.nukkit.math.Vector3f;

import java.util.HashMap;
import java.util.UUID;

public class CustomHashMaps implements Listener {
    public HashMap<UUID, Integer> xpAmount = new HashMap<UUID, Integer>();
    public HashMap<UUID, Boolean> muted = new HashMap<UUID, Boolean>();
    public HashMap<UUID, String> rank = new HashMap<UUID, String>();

    public HashMap<UUID, Boolean> hasDoubleJumped = new HashMap<UUID, Boolean>();

    public HashMap<UUID, Double> knockbackAmount = new HashMap<UUID, Double>();
    public HashMap<UUID, Integer> playerLives = new HashMap<UUID, Integer>();

    public HashMap<String, Boolean> isGameOn = new HashMap<String, Boolean>();

    public HashMap<UUID, String> banReason = new HashMap<UUID, String>();
    public HashMap<UUID, String> muteReason = new HashMap<UUID, String>();
}
