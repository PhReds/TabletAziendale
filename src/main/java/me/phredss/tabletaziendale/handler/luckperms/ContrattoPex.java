package me.phredss.tabletaziendale.handler.luckperms;

import me.phredss.tabletaziendale.TabletAziendale;
import me.phredss.tabletaziendale.handler.StampaContratto;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

public class ContrattoPex implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR) || !(event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;

        if(!player.getInventory().getItemInHand().hasItemMeta()) return;
        if(!(player.getInventory().getItemInHand().getItemMeta().hasDisplayName() || !(player.getInventory().getItemInHand().getItemMeta().hasLore()))) return;
        ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
        PersistentDataContainer item1 = meta.getPersistentDataContainer();
        String azienda = item1.get(new NamespacedKey(TabletAziendale.getPlugin(), "azienda"), PersistentDataType.STRING);







    }

}
