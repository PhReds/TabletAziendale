package me.phredss.tabletaziendale.handler.luckperms;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



import static me.phredss.tabletaziendale.util.Utils.*;

public class LicenziaConferma implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equals(getInventoryName("tabletgui.name-inv"))) {
            int slot = event.getRawSlot();
            event.setCancelled(true);
            ItemStack clicked = event.getCurrentItem();
            if (clicked != null && (slot == 15 || clicked.getItemMeta().getDisplayName().equals(getItemName("tabletgui.dismiss")))) {
                player.closeInventory();

                Inventory confermalicenzia = Bukkit.createInventory(null, 27, getInventoryName("confirmation-dismissal.name-inv"));
                //Materiale
                ItemStack conferma = new ItemStack(getItemMaterial("confirmation-dismissal.confirm"), 1);
                ItemStack annulla = new ItemStack(getItemMaterial("confirmation-dismissal.cancel"), 1);
                ItemMeta metacon = conferma.getItemMeta();
                ItemMeta metann = annulla.getItemMeta();
                //Nome
                metacon.setDisplayName(getItemName("confirmation-dismissal.confirm"));
                metann.setDisplayName(getItemName("confirmation-dismissal.cancel"));;
                //Lore
                metacon.setLore(getItemLore("confirmation-dismissal.confirm"));
                metann.setLore(getItemLore("confirmation-dismissal.cancel"));

                conferma.setItemMeta(metacon);
                annulla.setItemMeta(metann);

                confermalicenzia.setItem(11, conferma);
                confermalicenzia.setItem(15, annulla);
                player.openInventory(confermalicenzia);


            }

        }

    }


}