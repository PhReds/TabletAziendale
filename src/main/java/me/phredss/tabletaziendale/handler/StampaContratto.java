package me.phredss.tabletaziendale.handler;


import com.mrivanplays.conversations.base.question.Question;
import com.mrivanplays.conversations.spigot.BukkitConversationManager;
import com.mrivanplays.conversations.spigot.BukkitConversationPartner;
import me.phredss.tabletaziendale.TabletAziendale;
import me.phredss.tabletaziendale.util.DelayedTask;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

import static me.phredss.tabletaziendale.util.Utils.*;

public class StampaContratto implements Listener {



    TabletAziendale main = TabletAziendale.getPlugin(TabletAziendale.class);

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equals(getInventoryName("conferma-stampa.nome-inv"))) {
            int slot = event.getRawSlot();
            event.setCancelled(true);
            ItemStack clicked = event.getCurrentItem();
            if (slot == 11 || clicked.getItemMeta().getDisplayName().equalsIgnoreCase(getItemName("conferma-stampa.conferma"))) {
                player.closeInventory();

                BukkitConversationManager convoManager =
                        new BukkitConversationManager(main); // this being a Plugin instance
                 convoManager
                        .newConversationBuilder(player)
                        .withQuestion(Question.of("utente", getDomandaUtente("stampa-contratto")))
                        .withQuestion(Question.of("azienda", getDomandaAzienda("stampa-contratto")))
                        .withQuestion(Question.of("ruolo", getDomandaRuolo("stampa-contratto")))
                        .whenDone(
                                context -> {


                                    if(!player.hasPermission("azienda.direttore." + context.getInput("azienda"))) {
                                        player.sendMessage(ChatColor.RED + getErrorAziendaSbagliataContratto("error"));
                                        return;
                                    }
                                        BukkitConversationPartner partner = context.getConversationPartner();

                                        ItemStack contrattostampa = new ItemStack(Material.PAPER, 1);
                                        ItemMeta metacon = contrattostampa.getItemMeta();
                                        List<String> lores = new ArrayList<String>();
                                        metacon.setDisplayName(getItemNameDisplay("contratto-aziendale"));
                                        lores.add("§e――――――――");
                                        lores.add(" §6Informazioni Contratto");
                                        lores.add("  ");
                                        lores.add("§6§lUtente: ");
                                        lores.add(ChatColor.YELLOW + "- " + context.getInput("utente"));
                                        lores.add(" ");
                                        lores.add("§6§lAzienda:");
                                        lores.add(ChatColor.YELLOW + "- " + context.getInput("azienda"));
                                        lores.add(" ");
                                        lores.add("§6§lRuolo:");
                                        lores.add(ChatColor.YELLOW + "- " + context.getInput("ruolo"));
                                        lores.add(" ");
                                        lores.add("§6§lDirettore:");
                                        lores.add(ChatColor.YELLOW + "- " + player.getName());
                                        lores.add("§e――――――――");
                                        metacon.setLore(lores);
                                        contrattostampa.setItemMeta(metacon);

                                        PersistentDataContainer contratto = metacon.getPersistentDataContainer();

                                        String utente = context.getInput("utente");
                                        String azienda = context.getInput("azienda");
                                        String ruolo = context.getInput("ruolo");

                                        Player target = Bukkit.getPlayer(utente);
                                        if (target == null || !target.isOnline()) {
                                            player.sendMessage(getErrorUtenteOff("error"));
                                            return;
                                        }
                                        String direttoreid = player.getUniqueId().toString();
                                        String targetid = target.getUniqueId().toString();

                                        if (direttoreid.equals(targetid)) {
                                            player.sendMessage(getErrorAutoAssumiStampa("error"));
                                            return;
                                        }

                                        if (player.getLocation().distance(target.getLocation()) >= 10) {
                                            player.sendMessage(getErrorUtenteLontano("error"));
                                            return;
                                        }
                                        Inventory inv = target.getInventory();


                                        contratto.set(new NamespacedKey(TabletAziendale.getPlugin(TabletAziendale.class), "utente"), PersistentDataType.STRING, utente);
                                        contratto.set(new NamespacedKey(TabletAziendale.getPlugin(TabletAziendale.class), "azienda"), PersistentDataType.STRING, azienda);
                                        contratto.set(new NamespacedKey(TabletAziendale.getPlugin(TabletAziendale.class), "ruolo"), PersistentDataType.STRING, ruolo);
                                        contrattostampa.setItemMeta(metacon);


                                        player.sendMessage("§6§lStai stampando il contratto...");
                                        new DelayedTask(() -> {
                                            player.sendMessage(ChatColor.BOLD + "§a§lContratto Stampato con successo!");
                                            inv.addItem(contrattostampa);
                                        }, 20 * 3);
                                })
                        .build()
                        .start();


            } else if (slot == 15 || clicked.getItemMeta().getDisplayName().equals(getItemName("conferma-stampa.annulla"))) {
                event.setCancelled(true);
                player.closeInventory();
            }


        }

    }
}