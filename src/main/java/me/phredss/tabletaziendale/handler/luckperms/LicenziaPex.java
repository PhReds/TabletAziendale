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

import static me.phredss.tabletaziendale.util.Utils.*;

public class LicenziaPex implements Listener {
    TabletAziendale main = TabletAziendale.getPlugin(TabletAziendale.class);

    RegisteredServiceProvider<LuckPerms> providerLP = Bukkit.getServicesManager().getRegistration(LuckPerms.class);

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        if (providerLP == null) return;
        LuckPerms lp = providerLP.getProvider();
        if (event.getView().getTitle().equals(getInventoryName("confirmation-dismissal.name-inv"))) {
            event.setCancelled(true);
            int slot = event.getRawSlot();
            ItemStack clicked = event.getCurrentItem();
            if (clicked != null && (slot == 11 || clicked.getItemMeta().getDisplayName().equals(getItemName("confirmation-dismissal.confirm")))) {
                player.closeInventory();


                BukkitConversationManager convoManager =
                        new BukkitConversationManager(main);
                convoManager
                        .newConversationBuilder(player)
                        .withQuestion(Question.of("utente", getDomandaUtente("Fires-Employee")))
                        .withQuestion(Question.of("azienda", getDomandaAzienda("Fires-Employee")))
                        .whenDone(
                                context -> {


                                    if (!player.hasPermission("azienda.direttore." + context.getInput("azienda"))) {
                                        player.sendMessage(getPrefix() + ChatColor.RED + getErrorAziendaSbagliataLicenzia("error"));
                                        return;
                                    }

                                    String utente = context.getInput("utente");
                                    Player target = Bukkit.getPlayer(utente);
                                    if (target == null || !target.isOnline()) {
                                        player.sendMessage(getPrefix() + getErrorUtenteOff("error"));
                                        return;
                                    }

                                    String targetuuid = target.getUniqueId().toString();


                                    if (player.getUniqueId().toString().equalsIgnoreCase(targetuuid)) {
                                        player.sendMessage(getPrefix() + getErrorAutoLicenzia("error"));
                                        return;
                                    }



                                    new DelayedTask(() -> {
                                        lp.getUserManager().modifyUser(target.getUniqueId(), (User user) -> {
                                            Group group = lp.getGroupManager().getGroup(user.getPrimaryGroup());
                                            String string = group.getName();

                                            Group defaultgroup = lp.getGroupManager().getGroup("default");
                                            if (user.getPrimaryGroup().equals(defaultgroup)) return;
                                            Node node = InheritanceNode.builder(group).build();

                                            user.data().remove(node);
                                            String aName = context.getInput("azienda");
                                            String format = getConfigFile().getString("pex-format");

                                            player.sendMessage(getPrefix() + getMessagesLicenziaTu("general")
                                                    .replace("{agency}", aName));

                                            target.sendMessage(getPrefix() + getMessagesLicenzia("general"));
                                        });
                                    }, 20 * 3);
                                })
                        .build()
                        .start();

            } else if (clicked != null && (slot == 15 || clicked.getItemMeta().getDisplayName().equals(getItemName("print-confirm.cancel")))){
                player.closeInventory();
            }
        }

    }
}
