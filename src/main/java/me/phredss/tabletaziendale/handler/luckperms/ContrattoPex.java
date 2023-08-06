package me.phredss.tabletaziendale.handler.luckperms;

import me.phredss.tabletaziendale.TabletAziendale;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.RegisteredServiceProvider;

import static me.phredss.tabletaziendale.util.Utils.*;

public class ContrattoPex implements Listener {

    RegisteredServiceProvider<LuckPerms> providerLP = Bukkit.getServicesManager().getRegistration(LuckPerms.class);

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(providerLP == null) return;
        LuckPerms lp = providerLP.getProvider();
        Player player = event.getPlayer();
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_AIR) return;
        if(!player.getInventory().getItemInHand().hasItemMeta()) return;
        if(!(player.getInventory().getItemInHand().getItemMeta().hasDisplayName() || !(player.getInventory().getItemInHand().getItemMeta().hasLore()))) return;
        ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
        PersistentDataContainer item1 = meta.getPersistentDataContainer();


        //Controllo Nome
        if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(getItemNameDisplay("company-contract"))) {

            String utente = item1.get(new NamespacedKey(TabletAziendale.getPlugin(TabletAziendale.class), "utente"), PersistentDataType.STRING);
            Player utentelic = Bukkit.getPlayer(utente);
            String utenteuuid = utentelic.getUniqueId().toString();
            //Line 37

            if (!(player.getUniqueId().toString().equalsIgnoreCase(utenteuuid))) {
                player.sendMessage(getPrefix() + getErrorAutoAssumi("error"));
                return;
            }

            String azienda = item1.get(new NamespacedKey(TabletAziendale.getPlugin(TabletAziendale.class), "azienda"), PersistentDataType.STRING);
            String ruolo = item1.get(new NamespacedKey(TabletAziendale.getPlugin(TabletAziendale.class), "ruolo"), PersistentDataType.STRING);

            Group group = lp.getGroupManager().getGroup(getConfigFile().getString("pex-format")
                    .replace("{agency}", azienda)
                    .replace("{role}", ruolo));
            lp.getUserManager().modifyUser(player.getUniqueId(), (User user) -> {

                Node node = InheritanceNode.builder(group).build();

                user.data().add(node);

                player.sendMessage(getPrefix() + translate(getMessagesAssumi("general")));
                String string = group.getName();
                System.out.println(string);
                player.getInventory().remove(player.getInventory().getItemInMainHand());
            });
        }

    }

}
