package dev.guilhermeds.listeners;

import dev.guilhermeds.Main;
import dev.guilhermeds.builder.ItemBuilder;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    /*
    Okay, for the test plugin what I want priced/made is a 1.8 PVPSword plugin, what it needs to be is a configurable
    plugin, that gives use a sword on join that they can right click and it teleports them to a PVPWorld and gives them
    PVPGear, max Diamond, instant health 2 potions, and then 1 item that they can right click that pulls them out and
    puts them back to spawn
     */

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
//        event.getPlayer().teleport(Main.SPAWN_LOCATION);

        event.getPlayer().getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).addTag("onClick", "pvp_sword"));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                && event.getPlayer().getItemInHand() != null
                && event.getPlayer().getItemInHand().hasItemMeta()
                && CraftItemStack.asNMSCopy(event.getPlayer().getItemInHand()).hasTag()) {
            NBTTagCompound nbtTagCompound = CraftItemStack.asNMSCopy(event.getPlayer().getItemInHand()).getTag();

            if(nbtTagCompound.hasKey("onClick")) {
                if(nbtTagCompound.getString("onClick").equalsIgnoreCase("pvp_sword")) {
                    event.getPlayer().getInventory().clear();
                    event.getPlayer().getInventory().setArmorContents(null);

//                    event.getPlayer().teleport(Main.PVP_LOCATION);

                    event.getPlayer().getInventory().setItem(1, new ItemBuilder(Material.INK_SACK, (short) 1).addTag("onClick", "leave_battle"));

                    event.getPlayer().updateInventory();
                    return;
                }
                if(nbtTagCompound.getString("onClick").equalsIgnoreCase("leave_battle")) {
                    event.getPlayer().getInventory().clear();
                    event.getPlayer().getInventory().setArmorContents(null);

//                    event.getPlayer().teleport(Main.SPAWN_LOCATION);

                    event.getPlayer().getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).addTag("onClick", "pvp_sword"));

                    event.getPlayer().updateInventory();
                    return;
                }
            }
        }
    }

}
