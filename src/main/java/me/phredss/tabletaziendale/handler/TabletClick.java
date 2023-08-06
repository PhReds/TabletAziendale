package me.phredss.tabletaziendale.handler;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static me.phredss.tabletaziendale.util.Utils.*;

public class TabletClick implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.getInventory().getItemInHand().getType().equals(getItemMaterialDisplay("agency-tablet")) && player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(getItemNameDisplay("agency-tablet"))) {
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                if (!player.hasPermission("azienda.direttore")) {
                    player.sendMessage(getPrefix() + getMessaggesNoPermission("general"));
                }
                Inventory guiPrima = Bukkit.createInventory(player, 27, getInventoryName("tabletgui.name-inv"));
                player.openInventory(guiPrima);

                //Stampa Contratto

                ItemStack item1 = new ItemStack(getItemMaterial("tabletgui.print-contract"), 1);
                ItemMeta meta1 = item1.getItemMeta();
                List<String> lores = new ArrayList<String>();
                meta1.setDisplayName(getItemName("tabletgui.print-contract"));
                meta1.setLore(getItemLore("tabletgui.print-contract"));
                item1.setItemMeta(meta1);



                //Licenzia

                ItemStack item2 = new ItemStack(getItemMaterial("tabletgui.dismiss"), 1);
                ItemMeta meta2 = item2.getItemMeta();
                List<String> lores2 = new ArrayList<>();
                meta2.setDisplayName(getItemName("tabletgui.dismiss"));
                meta2.setLore(getItemLore("tabletgui.dismiss"));
                item2.setItemMeta(meta2);


                //Item settati in GUI
                guiPrima.setItem(11, item1);
                guiPrima.setItem(15, item2);
                player.openInventory(guiPrima);





            }
        }

    }

}
