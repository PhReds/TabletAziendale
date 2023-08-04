package me.phredss.tabletaziendale.handler;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static me.phredss.tabletaziendale.util.Utils.*;

public class GuiContratto implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equals(getInventoryName("tabletgui.nome-inv"))) {
            int slot = event.getRawSlot();
            event.setCancelled(true);
            ItemStack clicked = event.getCurrentItem();
            if (clicked != null && (slot == 11 || clicked.getItemMeta().getDisplayName().equals(getItemName("tabletgui.stampa-contratto")))) {
                player.closeInventory();

                Inventory confermacontratto = Bukkit.createInventory(null, 27, getInventoryName("conferma-stampa.nome-inv"));
                //Materiale
                ItemStack conferma = new ItemStack(getItemMaterial("conferma-stampa.conferma"), 1);
                ItemStack annulla = new ItemStack(getItemMaterial("conferma-stampa.annulla"), 1);
                List<String> loresconferma = new ArrayList<String>();
                List<String> loresannulla = new ArrayList<String>();
                ItemMeta metacon = conferma.getItemMeta();
                ItemMeta metann = annulla.getItemMeta();
                //Nome
                metacon.setDisplayName(getItemName("conferma-stampa.conferma"));
                metann.setDisplayName(getItemName("conferma-stampa.annulla"));;
                //Lore
                metacon.setLore(getItemLore("conferma-stampa.conferma"));
                metann.setLore(getItemLore("conferma-stampa.annulla"));




                conferma.setItemMeta(metacon);
                annulla.setItemMeta(metann);

                confermacontratto.setItem(11, conferma);
                confermacontratto.setItem(15, annulla);
                player.openInventory(confermacontratto);


            }

        }

    }


}