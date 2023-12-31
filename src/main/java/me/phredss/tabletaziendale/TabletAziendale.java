package me.phredss.tabletaziendale;

import lombok.Getter;
import me.phredss.tabletaziendale.commands.TabletCommand;
import me.phredss.tabletaziendale.commands.tabcompletion.AziendaTabCompletion;
import me.phredss.tabletaziendale.config.FileManager;
import me.phredss.tabletaziendale.handler.GuiContratto;
import me.phredss.tabletaziendale.handler.StampaContratto;
import me.phredss.tabletaziendale.handler.TabletClick;
import me.phredss.tabletaziendale.handler.luckperms.ContrattoPex;
import me.phredss.tabletaziendale.handler.luckperms.LicenziaConferma;
import me.phredss.tabletaziendale.handler.luckperms.LicenziaPex;
import me.phredss.tabletaziendale.licenza.AdvancedLicense;
import me.phredss.tabletaziendale.util.DelayedTask;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TabletAziendale extends JavaPlugin {

    @Getter
    private static TabletAziendale instance;
    @Getter
    private FileManager configFile, guiFile, itemFile, conversationFile, messagesFile;

    @Override
    public void onEnable() {
        instance = this;

        configFile = new FileManager("config", instance);
        guiFile = new FileManager("gui", instance);
        itemFile = new FileManager("item", instance);
        conversationFile = new FileManager("conversation", instance);
        messagesFile = new FileManager("messages", instance);
        String licenza = getConfig().getString("LICENSE-KEY");

        if(!new AdvancedLicense(licenza, "https://phredsslicensesystem.000webhostapp.com/verify.php", this).register())  {
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        if(getConfig().getBoolean("bstats")) {
            int pluginId = 19392; // <-- Replace with the id of your plugin!
            Metrics metrics = new Metrics(this, pluginId);
        }

        //Command
        getCommand("agency").setExecutor(new TabletCommand());
        getCommand("agency").setTabCompleter(new AziendaTabCompletion());

        //Handler
        getServer().getPluginManager().registerEvents(new TabletClick(), this);

        //Handler Contratto
        getServer().getPluginManager().registerEvents(new GuiContratto(), this);
        getServer().getPluginManager().registerEvents(new StampaContratto(), this);
        getServer().getPluginManager().registerEvents(new ContrattoPex(), this);

        //Handler Licenzia
        getServer().getPluginManager().registerEvents(new LicenziaPex(), this);
        getServer().getPluginManager().registerEvents(new LicenziaConferma(), this);
        // evento contrattopex con "instance" al posto di plugin come parametro

        new DelayedTask(this);
    }




    public void onDisable() {
    }
}