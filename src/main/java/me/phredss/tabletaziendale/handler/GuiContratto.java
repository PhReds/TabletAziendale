package me.phredss.tabletaziendale.handler;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GuiContratto implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equals("§6Tablet Aziendale")) {
            int slot = event.getRawSlot();
            event.setCancelled(true);
            ItemStack clicked = event.getCurrentItem();
            if (slot == 11 || clicked.getItemMeta().getDisplayName().equals("§aStampa Contratto")) {
                player.closeInventory();

                Inventory confermacontratto = Bukkit.createInventory(null, 27, "§6Conferma della Stampa");
                ItemStack conferma = new ItemStack(Material.PAPER, 1);
                ItemStack annulla = new ItemStack(Material.BARRIER, 1);
                List<String> loresconferma = new ArrayList<String>();
                List<String> loresannulla = new ArrayList<String>();
                ItemMeta metacon = conferma.getItemMeta();
                ItemMeta metann = annulla.getItemMeta();
                metacon.setDisplayName("§a§lConferma Stampa");
                metann.setDisplayName("§c§lAnnulla Stampa");
                loresconferma.add("§6Clicca per confermare");
                loresconferma.add("§6La stampa del contratto");
                loresannulla.add("§4Clicca per annullare");
                loresannulla.add("§4La stampa del contratto");
                metacon.setLore(loresconferma);
                metann.setLore(loresannulla);
                conferma.setItemMeta(metacon);
                annulla.setItemMeta(metann);

                confermacontratto.setItem(11, conferma);
                confermacontratto.setItem(15, annulla);
                player.openInventory(confermacontratto);


            }

        }

    }
}