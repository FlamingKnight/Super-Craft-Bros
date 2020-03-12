package com.galacticdiamond.magma.supercraftbros.events;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.item.Item;
import cn.nukkit.math.Vector3;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;
import com.galacticdiamond.magma.supercraftbros.lists.CustomItems;
import com.galacticdiamond.magma.supercraftbros.lists.CustomMessages;

public class PlayerDamageActions implements Listener {

    private MagmaCore plugin;
    private CustomItems customItems = new CustomItems();
    private CustomMessages customMessages = new CustomMessages();
    public PlayerDamageActions(MagmaCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent ev) {
        Entity damagedEntity = ev.getDamager();
        Entity hitEntity = ev.getEntity();

        if (!plugin.getCustomHashMaps().isGameOn.get("scb") || plugin.getCustomHashMaps().isGameOn.get("scb") == null) {
            ev.setCancelled();
        } else {
            if(damagedEntity instanceof Player && hitEntity instanceof Player) {
                Player hitPlayer = (Player) hitEntity;
                Player damager = (Player) damagedEntity;
                Item heldItem = damager.getInventory().getItemInHand();
                double currenntKnockbackAmount = plugin.getCustomHashMaps().knockbackAmount.get(hitPlayer.getUniqueId());

                if(heldItem.getName().equals(customMessages.mjolnirPrefix)) {
                    heldItem.setDamage(0);
                    damager.getInventory().remove(customItems.mjolnir);
                    if(plugin.getCustomHashMaps().knockbackAmount.get(hitPlayer.getUniqueId()) < 10.0) {
                        plugin.getCustomHashMaps().knockbackAmount.put(hitPlayer.getUniqueId(), currenntKnockbackAmount*2);
                    } else {
                        plugin.getCustomHashMaps().knockbackAmount.put(hitPlayer.getUniqueId(), 10.0);
                    }
                } else {
                    if(plugin.getCustomHashMaps().knockbackAmount.get(hitPlayer.getUniqueId()) < 10.0) {
                        plugin.getCustomHashMaps().knockbackAmount.put(hitPlayer.getUniqueId(), currenntKnockbackAmount*1.05);
                    } else {
                        plugin.getCustomHashMaps().knockbackAmount.put(hitPlayer.getUniqueId(), 10.0);
                    }
                }
                ev.setDamage(0);
                if(damager.getDirectionVector().getY() < 0) {
                    double customYVectorDirection = damager.getDirectionVector().getY()*-1;
                    Vector3 knockback = new Vector3(damager.getDirectionVector().getX()*currenntKnockbackAmount, customYVectorDirection*currenntKnockbackAmount, damager.getDirectionVector().getZ()*currenntKnockbackAmount);
                    hitPlayer.setMotion(knockback);
                } else {
                    double customYVectorDirection = damager.getDirectionVector().getY();
                    Vector3 knockback = new Vector3(damager.getDirectionVector().getX()*currenntKnockbackAmount, customYVectorDirection*currenntKnockbackAmount, damager.getDirectionVector().getZ()*currenntKnockbackAmount);
                    hitPlayer.setMotion(knockback);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerFallDamage(EntityDamageEvent ev) {
        if(ev.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
            ev.setCancelled();
        }
    }
}
