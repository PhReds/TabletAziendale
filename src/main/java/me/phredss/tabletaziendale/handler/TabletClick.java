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

public class TabletClick implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.getInventory().getItemInHand().getType().equals(Material.PRISMARINE_SHARD) && player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals("§6§lTablet Aziendale")) {
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                if (!player.hasPermission("azienda.direttore")) {
                    player.sendMessage("§cNon hai il permesso di utilizzare questo tablet!");
                }
                else {
                    Inventory guiPrima = Bukkit.createInventory(player, 27, "§6Tablet Aziendale");
                    player.openInventory(guiPrima);
                    System.out.println("Item Aperto e Prima Gui");

                    ItemStack item1 = new ItemStack(Material.PAPER, 1);
                    ItemMeta meta1 = item1.getItemMeta();
                    List<String> lores = new ArrayList<String>();
                    meta1.setDisplayName("§aStampa Contratto");
                    lores.add("    ");
                    lores.add("§6Clicca per stampare");
                    lores.add("§6Un contratto Aziendale");
                    meta1.setLore(lores);
                    item1.setItemMeta(meta1);

                    guiPrima.setItem(11, item1);
                    player.openInventory(guiPrima);

                }


            }
        }

    }

}
