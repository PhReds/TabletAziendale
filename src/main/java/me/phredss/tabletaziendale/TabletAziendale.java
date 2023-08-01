package me.phredss.tabletaziendale;

import me.phredss.tabletaziendale.commands.TabletCommand;
import me.phredss.tabletaziendale.commands.tabcompletion.AziendaTabCompletion;
import me.phredss.tabletaziendale.handler.GuiContratto;
import me.phredss.tabletaziendale.handler.StampaContratto;
import me.phredss.tabletaziendale.handler.TabletClick;
import me.phredss.tabletaziendale.handler.luckperms.ContrattoPex;
import me.phredss.tabletaziendale.handler.luckperms.LicenziaPex;
import me.phredss.tabletaziendale.licenza.AdvancedLicense;
import me.phredss.tabletaziendale.util.DelayedTask;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class TabletAziendale extends JavaPlugin {


    public static NamespacedKey tabletAziendaleKey;

    public static TabletAziendale getPlugin() {
        return instance;
    }

    private static TabletAziendale instance;



    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        String licenza = getConfig().getString("LICENSE-KEY");


        if(!new AdvancedLicense(licenza, "https://phredsslicensesystem.000webhostapp.com/verify.php", this).register())  {

            return;
        }

        //Command
        getCommand("azienda").setExecutor(new TabletCommand());
        getCommand("azienda").setTabCompleter(new AziendaTabCompletion());

        //Handler
        getServer().getPluginManager().registerEvents(new TabletClick(), this);

        //Handler Contratto
        getServer().getPluginManager().registerEvents(new GuiContratto(), this);
        getServer().getPluginManager().registerEvents(new StampaContratto(), this);
        getServer().getPluginManager().registerEvents(new ContrattoPex(), this);

        //Handler Licenzia
        getServer().getPluginManager().registerEvents(new LicenziaPex(), this);
        // evento contrattopex con "instance" al posto di plugin come parametro

        new DelayedTask(this);
    }




    public void onDisable() {
    }
}