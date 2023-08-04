package me.phredss.tabletaziendale.util;

import lombok.Getter;
import me.phredss.tabletaziendale.TabletAziendale;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class Utils {

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String CustomPrefix = translate("&6[TabletAziendale]&r ");

    public static FileConfiguration getConfigFile() {
        return TabletAziendale.getInstance().getConfigFile().getConfig();
    }


    public static FileConfiguration getGuiFile() {
        return TabletAziendale.getInstance().getGuiFile().getConfig();
    }

    public static  String getItemName(String nomeItem) {
        return translate(getGuiFile().getConfigurationSection("gui." + nomeItem).getString("nome"));
    }

    public static String getInventoryName(String path) {
        return translate(getGuiFile().getConfigurationSection("gui.").getString(path));
    }

    public static  List<String> getItemLore(String nomeItem) {
        List<String> lore = new ArrayList<>();
        for(String s : getGuiFile().getConfigurationSection("gui." + nomeItem).getStringList("lore")) {
            lore.add(translate(s));
        }
        if (lore.isEmpty()) {
            System.out.println("lore vuota");
        }
        return lore;
    }



    public static Material getItemMaterial(String nomeItem) {
        String mat = getGuiFile().getString("gui." + nomeItem + ".materiale");
        System.out.println(mat);
        if(Material.valueOf(mat) != null) {
            return Material.valueOf(getGuiFile().getString("gui." + nomeItem + ".materiale"));
        } else {
            String nome = org.bukkit.ChatColor.stripColor(nomeItem);
            System.out.println("Materiale dell'item " + nome + " non valido");
            return null;
        }
    }

    //item File
    public static FileConfiguration getItemFile() {return TabletAziendale.getInstance().getItemFile().getConfig();}

    public static  String getItemNameDisplay(String nomeItem) {
        return translate(getItemFile().getConfigurationSection("item." + nomeItem).getString("nome"));
    }


    public static  List<String> getItemLoreDisplay(String nomeItem) {
        List<String> lore = new ArrayList<>();
        for(String s : getItemFile().getConfigurationSection("item." + nomeItem).getStringList("lore")) {
            lore.add(translate(s));
        }
        if (lore.isEmpty()) {
            System.out.println("lore vuota");
        }
        return lore;
    }



    public static Material getItemMaterialDisplay(String nomeItem) {
        String mat = getItemFile().getString("item." + nomeItem + ".materiale");
        System.out.println(mat);
        if(Material.valueOf(mat) != null) {
            return Material.valueOf(getItemFile().getString("item." + nomeItem + ".materiale"));
        } else {
            String nome = org.bukkit.ChatColor.stripColor(nomeItem);
            System.out.println("Materiale dell'item " + nome + " non valido");
            return null;
        }
    }

    //Conversation File
    public static FileConfiguration getConversationFile() {return TabletAziendale.getInstance().getConversationFile().getConfig();}

    public static  String getDomandaUtente(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("DomandaUtente"));
    }

    public static  String getDomandaAzienda(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("DomandaAzienda"));
    }

    public static  String getDomandaRuolo(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("DomandaRuolo"));
    }

    public static  String getErrorUtenteLontano(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("utente-lontano"));
    }

    public static  String getErrorUtenteOff(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("utenteoff"));
    }

    public static  String getErrorAziendaSbagliataContratto(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("azienda-sbagliata-contratto"));
    }

    public static  String getErrorAziendaSbagliataLicenzia(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("azienda-sbagliata-licenzia"));
    }

    public static  String getErrorAutoAssumi(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("auto-assunzione"));
    }

    public static String getErrorAutoLicenzia(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("auto-licenzia"));
    }

    public static  String getErrorAutoAssumiStampa(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("auto-stampa-contratto"));
    }
}
