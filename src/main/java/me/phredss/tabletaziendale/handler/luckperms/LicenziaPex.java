package me.phredss.tabletaziendale.handler.luckperms;

import com.mrivanplays.conversations.base.question.Question;
import com.mrivanplays.conversations.spigot.BukkitConversationManager;
import me.phredss.tabletaziendale.TabletAziendale;
import me.phredss.tabletaziendale.util.DelayedTask;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.InheritanceNode;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;


import static me.phredss.tabletaziendale.util.TranslateUtils.translate;

public class LicenziaPex implements Listener {
    TabletAziendale main = TabletAziendale.getPlugin(TabletAziendale.class);

    RegisteredServiceProvider<LuckPerms> providerLP = Bukkit.getServicesManager().getRegistration(LuckPerms.class);

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (providerLP == null) return;
        LuckPerms lp = providerLP.getProvider();
        if (event.getView().getTitle().equals("§6Tablet Aziendale")) {
            event.setCancelled(true);
            int slot = event.getRawSlot();
            ItemStack clicked = event.getCurrentItem();
            if (slot == 15 || clicked.getItemMeta().getDisplayName().equals("§a§lConferma Stampa")) {
                player.closeInventory();


                BukkitConversationManager convoManager =
                        new BukkitConversationManager(main); // this being a Plugin instance
                convoManager
                        .newConversationBuilder(player)
                        .withQuestion(Question.of("utente", "§e§lQuale utente vuoi licenziare?"))
                        .withQuestion(Question.of("azienda", "§e§lDa quale azienda lo vuoi licenziare?"))
                        .whenDone(
                                context -> {


                                    if (!player.hasPermission("azienda.direttore." + context.getInput("azienda"))) {
                                        player.sendMessage(ChatColor.RED + "Non puoi licenziare per questa azienda");
                                        return;
                                    }
                                    String utente = context.getInput("utente");
                                    Player target = Bukkit.getPlayer(utente);
                                    String targetuuid = target.getUniqueId().toString();
                                    if (target == null || !target.isOnline()) {
                                        player.sendMessage("§c§lL'Utente non è Online o non Esiste!");
                                        return;
                                    }

                                    if (player.getUniqueId().toString().equalsIgnoreCase(targetuuid)) {
                                        player.sendMessage(translate("&cNon puoi licenziarti da solo."));
                                        return;
                                    }


                                    player.sendMessage("§c§lStai licenziando " + target.getName() + ".");
                                    new DelayedTask(() -> {
                                        lp.getUserManager().modifyUser(target.getUniqueId(), (User user) -> {
                                            Group group = lp.getGroupManager().getGroup(user.getPrimaryGroup());
                                            String string = group.getName();

                                            Group defaultgroup = lp.getGroupManager().getGroup("default");
                                            if (user.getPrimaryGroup().equals(defaultgroup)) return;
                                            Node node = InheritanceNode.builder(group).build();

                                            user.data().remove(node);

                                            target.sendMessage(translate(ChatColor.RED + "Sei stato licenziato!"));
                                        });
                                    }, 20 * 3);
                                })
                        .build()
                        .start();

            }
        }

    }
}
