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

        if (event.getView().getTitle().equals(getInventoryName("tabletgui.name-inv"))) {
            int slot = event.getRawSlot();
            event.setCancelled(true);
            ItemStack clicked = event.getCurrentItem();
            if (clicked != null && (slot == 11 || clicked.getItemMeta().getDisplayName().equals(getItemName("tabletgui.print-contract")))) {
                player.closeInventory();

                Inventory confermacontratto = Bukkit.createInventory(null, 27, getInventoryName("print-confirmation.name-inv"));
                //Materiale
                ItemStack conferma = new ItemStack(getItemMaterial("print-confirmation.confirm"), 1);
                ItemStack annulla = new ItemStack(getItemMaterial("print-confirmation.cancel"), 1);
                ItemMeta metacon = conferma.getItemMeta();
                ItemMeta metann = annulla.getItemMeta();
                //Nome
                metacon.setDisplayName(getItemName("print-confirmation.confirm"));
                metann.setDisplayName(getItemName("print-confirmation.cancel"));;
                //Lore
                metacon.setLore(getItemLore("print-confirmation.confirm"));
                metann.setLore(getItemLore("print-confirmation.cancel"));



                conferma.setItemMeta(metacon);
                annulla.setItemMeta(metann);

                confermacontratto.setItem(11, conferma);
                confermacontratto.setItem(15, annulla);
                player.openInventory(confermacontratto);


            }

        }

    }


}