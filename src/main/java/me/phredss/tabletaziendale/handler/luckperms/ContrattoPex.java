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

import static me.phredss.tabletaziendale.util.TranslateUtils.translate;

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
        if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§a§lContratto Aziendale")) {

            String utente = item1.get(new NamespacedKey(TabletAziendale.getPlugin(), "utente"), PersistentDataType.STRING);
            Player utentelic = Bukkit.getPlayer(utente);
            String utenteuuid = utentelic.getUniqueId().toString();
            //Line 37

            if (!(player.getUniqueId().toString().equalsIgnoreCase(utenteuuid))) {
                player.sendMessage(translate("&cNon puoi usare questo contratto in quanto non tuo."));
                return;
            }
            String azienda = item1.get(new NamespacedKey(TabletAziendale.getPlugin(), "azienda"), PersistentDataType.STRING);
            String ruolo = item1.get(new NamespacedKey(TabletAziendale.getPlugin(), "ruolo"), PersistentDataType.STRING);

            Group group = lp.getGroupManager().getGroup(azienda + "-" + ruolo);
            lp.getUserManager().modifyUser(player.getUniqueId(), (User user) -> {

                Node node = InheritanceNode.builder(group).build();

                user.data().add(node);

                player.sendMessage(translate("&aSei stato assunto come " + ruolo + " nell'azienda " + azienda + "."));
                String string = group.getName();
                System.out.println(string);
                player.getInventory().remove(player.getInventory().getItemInMainHand());
            });
        }

    }

}
