package me.phredss.tabletaziendale.handler;


import me.phredss.tabletaziendale.util.TranslateUtils;
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

                    //Stampa Contratto

                    ItemStack item1 = new ItemStack(Material.PAPER, 1);
                    ItemMeta meta1 = item1.getItemMeta();
                    List<String> lores = new ArrayList<String>();
                    meta1.setDisplayName("§aStampa Contratto");
                    lores.add("    ");
                    lores.add("§6Clicca per stampare");
                    lores.add("§6Un contratto Aziendale");
                    lores.add("    ");
                    meta1.setLore(lores);
                    item1.setItemMeta(meta1);



                    //Licenzia

                    ItemStack item2 = new ItemStack(Material.STRUCTURE_VOID, 1);
                    ItemMeta meta2 = item2.getItemMeta();
                    List<String> lores2 = new ArrayList<>();
                    meta2.setDisplayName("§cLicenzia Dipendente");
                    lores2.add("    ");
                    lores2.add("§6Clicca per licenziare");
                    lores2.add("§6Un dipendente dell'Azienda");
                    lores2.add("    ");
                    meta2.setLore(lores2);
                    item2.setItemMeta(meta2);


                    //Item settati in GUI
                    guiPrima.setItem(11, item1);
                    guiPrima.setItem(15, item2);
                    player.openInventory(guiPrima);


                }


            }
        }

    }

}
