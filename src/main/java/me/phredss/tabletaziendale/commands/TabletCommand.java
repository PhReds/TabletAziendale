package me.phredss.tabletaziendale.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TabletCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            //Non è un Player
        } else {
            //E' un Player
            Player player = (Player) sender;
            if (!player.hasPermission("ta.staff")) {
                //Non ha permesso
                player.sendMessage("§c§lNon hai il permesso!");
            } else {
                //Ha permesso
                if (args.length < 1) {
                    //Lunghezza 0
                    player.sendMessage("§6Usa - /azienda help - per la lista dei comandi.");
                } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
                    //Azienda Help
                    player.sendMessage("§6Ecco la lista dei comandi:");
                    player.sendMessage(" ");
                    player.sendMessage("§6 /Azienda help - Mostra la lista dei comandi");
                    player.sendMessage("§6 /Azienda tablet - Givva un tablet aziendale");
                    player.sendMessage("        ");
                    player.sendMessage("§6 -------------");

                } else if (args.length == 1 && args[0].equalsIgnoreCase("tablet")) {
                    System.out.println("Item Givvato");
                    //Tablet Aziendale
                    ItemStack tablet = new ItemStack(Material.PRISMARINE_SHARD, 1);
                    Inventory inv = player.getInventory();

                    ItemMeta meta = tablet.getItemMeta();
                    List<String> lores = new ArrayList<String>();
                    meta.setDisplayName("§6§lTablet Aziendale");
                    lores.add("§eTablet Contenente le principali funzioni Aziendali");
                    lores.add("§cUtilizzabile solo dai Direttori Aziendali!");
                    meta.setUnbreakable(true);
                    meta.setLore(lores);
                    meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    tablet.setItemMeta(meta);

                    inv.addItem(tablet);
                } else {
                    player.sendMessage("§6Usa - /azienda help - per la lista dei comandi.");
                }
            }
        }
        return false;
    }
}