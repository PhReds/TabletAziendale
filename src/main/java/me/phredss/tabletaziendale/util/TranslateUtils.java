package me.phredss.tabletaziendale.util;

import net.md_5.bungee.api.ChatColor;

public class TranslateUtils {

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
