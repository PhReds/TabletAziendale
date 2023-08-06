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

import static me.phredss.tabletaziendale.util.Utils.*;

public class TabletCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            //Non è un Player
        }
        //E' un Player
        Player player = (Player) sender;
        if (!player.hasPermission("agency.staff")) {
            //Non ha permesso
            player.sendMessage(getMessaggesNoPermission("general"));
        } else {
            //Ha permesso
            if (args.length < 1) {
                //Lunghezza 0
                player.sendMessage(getPrefix() + getMessagesComandoSbagliato("general"));
            } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
                //Azienda Help
                player.sendMessage(getPrefix() + getLine1("command-help"));
                player.sendMessage(getLine2("command-help"));
                player.sendMessage(getLine3("command-help"));
                player.sendMessage(getLine4("command-help"));
                player.sendMessage(getLine5("command-help"));

            } else if (args.length == 1 && args[0].equalsIgnoreCase("tablet")) {
                //Tablet Aziendale
                ItemStack tablet = new ItemStack(getItemMaterialDisplay("agency-tablet"), 1);
                Inventory inv = player.getInventory();

                ItemMeta meta = tablet.getItemMeta();
                List<String> lores = new ArrayList<String>();
                meta.setUnbreakable(true);
                meta.setDisplayName(getItemNameDisplay("agency-tablet"));
                meta.setLore(getItemLoreDisplay("agency-tablet"));
                meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                tablet.setItemMeta(meta);

                inv.addItem(tablet);
            } else {
                player.sendMessage(getPrefix() + getMessagesComandoSbagliato("general"));
            }
        }
        return false;
    }
}