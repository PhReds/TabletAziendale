package me.phredss.tabletaziendale.commands.tabcompletion;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class AziendaTabCompletion implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String alias, String[] args) {

        if (command.getName().equalsIgnoreCase("azienda")) {
            if (args.length == 1) {
                List<String> aziendaTab = new ArrayList<>();
                aziendaTab.add("help");
                aziendaTab.add("tablet");
                return aziendaTab;
            }

            if (args.length >= 1) return null;
        }

        return null;
    }
}
