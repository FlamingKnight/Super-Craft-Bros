package com.galacticdiamond.magma.supercraftbros.lists;

import cn.nukkit.event.Listener;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;

public class CustomForms implements Listener {
    public FormWindowSimple buyWindow = new FormWindowSimple("Buy menu", "Here is where you can buy in-game items with your balance!");
    public FormWindowCustom elementInput = new FormWindowCustom("test");
    public FormWindowCustom elementDropdown = new FormWindowCustom("Dropdowns!");
}
