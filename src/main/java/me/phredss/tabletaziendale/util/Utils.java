package me.phredss.tabletaziendale.util;


import me.phredss.tabletaziendale.TabletAziendale;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import java.util.ArrayList;
import java.util.List;



public class Utils {

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String getPrefix() {
        String prefisso;
        if(getConfigFile().getString("prefix") != null) {
            prefisso = ChatColor.translateAlternateColorCodes('&', getConfigFile().getString("prefix"));
            return prefisso;
        } else {
            prefisso = ChatColor.translateAlternateColorCodes('&', "&e[&6&lAgency&e]&r ");
            return prefisso;
        }
    }

    public static FileConfiguration getConfigFile() {
        return TabletAziendale.getInstance().getConfigFile().getConfig();
    }


    public static FileConfiguration getGuiFile() {
        return TabletAziendale.getInstance().getGuiFile().getConfig();
    }

    public static  String getItemName(String nomeItem) {
        return translate(getGuiFile().getConfigurationSection("gui." + nomeItem).getString("name"));
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
        }
        return lore;
    }



    public static Material getItemMaterial(String nomeItem) {
        String mat = getGuiFile().getString("gui." + nomeItem + ".material");
        System.out.println(mat);
        if(Material.valueOf(mat) != null) {
            return Material.valueOf(getGuiFile().getString("gui." + nomeItem + ".material"));
        } else {
            String nome = org.bukkit.ChatColor.stripColor(nomeItem);
            return null;
        }
    }

    //item File
    public static FileConfiguration getItemFile() {return TabletAziendale.getInstance().getItemFile().getConfig();}

    public static  String getItemNameDisplay(String nomeItem) {
        return translate(getItemFile().getConfigurationSection("item." + nomeItem).getString("name"));
    }

    public static String getItemUser(String nomeItem) {
        return translate(getItemFile().getConfigurationSection("item." + nomeItem).getString("user"));
    }

    public static String getItemAgency(String nomeItem) {
        return translate(getItemFile().getConfigurationSection("item." + nomeItem).getString("agency"));
    }

    public static String getItemRole(String nomeItem) {
        return translate(getItemFile().getConfigurationSection("item." + nomeItem).getString("role"));
    }

    public static String getItemDirector(String nomeItem) {
        return translate(getItemFile().getConfigurationSection("item." + nomeItem).getString("director"));
    }

    public static String getItemInformation(String nomeItem) {
        return translate(getItemFile().getConfigurationSection("item." + nomeItem).getString("contract-information"));
    }
    public static  List<String> getItemLoreDisplay(String nomeItem) {
        List<String> lore = new ArrayList<>();
        for(String s : getItemFile().getConfigurationSection("item." + nomeItem).getStringList("lore")) {
            lore.add(translate(s));
        }
        if (lore.isEmpty()) {
        }
        return lore;
    }



    public static Material getItemMaterialDisplay(String nomeItem) {
        String mat = getItemFile().getString("item." + nomeItem + ".material");
        if(Material.valueOf(mat) != null) {
            return Material.valueOf(getItemFile().getString("item." + nomeItem + ".material"));
        } else {
            String nome = org.bukkit.ChatColor.stripColor(nomeItem);
            return null;
        }
    }

    //Conversation File
    public static FileConfiguration getConversationFile() {return TabletAziendale.getInstance().getConversationFile().getConfig();}

    public static  String getDomandaUtente(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("UserQuestion"));
    }

    public static  String getDomandaAzienda(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("CompanyQuestion"));
    }

    public static  String getDomandaRuolo(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("RoleQuestion"));
    }

    public static  String getErrorUtenteLontano(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("user-far"));
    }

    public static  String getErrorUtenteOff(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("useroff"));
    }

    public static  String getErrorAziendaSbagliataContratto(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("wrong-company-contract"));
    }

    public static  String getErrorAziendaSbagliataLicenzia(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("wrong-company-dismissal"));
    }

    public static  String getErrorAutoAssumi(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("self-hiring"));
    }

    public static String getErrorAutoLicenzia(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("self-dismissal"));
    }

    public static  String getErrorAutoAssumiStampa(String nomeItem) {
        return translate(getConversationFile().getConfigurationSection("conversation." + nomeItem).getString("self-print-contract"));
    }

    public static FileConfiguration getMessagesFile() {return TabletAziendale.getInstance().getMessagesFile().getConfig();}

    public static String getMessaggesNoPermission(String nomeItem) {
        return translate(getMessagesFile().getConfigurationSection("messages." + nomeItem).getString("no-permissions"));
    }

    public static String getMessagesLicenzia(String nomeItem) {
        return translate(getMessagesFile().getConfigurationSection("messages." + nomeItem).getString("you-were-fired"));
    }

    public static String getMessagesLicenziaTu(String nomeItem) {
        return translate(getMessagesFile().getConfigurationSection("messages." + nomeItem).getString("you-fired-a-person"));
    }

    public static String getMessagesAssumi(String nomeItem) {
        return translate(getMessagesFile().getConfigurationSection("messages." + nomeItem).getString("you-have-been-hired"));
    }

    public static String getMessagesStampa(String nomeItem) {
        return translate(getMessagesFile().getConfigurationSection("messages." + nomeItem).getString("contract-printing"));
    }

    public static String getMessagesStampaSucc(String nomeItem) {
        return translate(getMessagesFile().getConfigurationSection("messages." + nomeItem).getString("printed-contract"));
    }

    public static String getMessagesComandoSbagliato(String nomeItem) {
        return translate(getMessagesFile().getConfigurationSection("messages." + nomeItem).getString("wrong-command"));
    }

    public static String getLine1(String nomeItem) {
        return translate(getMessagesFile().getConfigurationSection("messages.general." + nomeItem).getString("line-one"));
    }

    public static String getLine2(String nomeItem) {
        return translate(getMessagesFile().getConfigurationSection("messages.general." + nomeItem).getString("line-two"));
    }

    public static String getLine3(String nomeItem) {
        return translate(getMessagesFile().getConfigurationSection("messages.general." + nomeItem).getString("line-three"));
    }

    public static String getLine4(String nomeItem) {
        return translate(getMessagesFile().getConfigurationSection("messages.general." + nomeItem).getString("line-four"));
    }

    public static String getLine5(String nomeItem) {
        return translate(getMessagesFile().getConfigurationSection("messages.general." + nomeItem).getString("line-five"));
    }

}
