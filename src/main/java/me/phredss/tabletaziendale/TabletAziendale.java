//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.phredss.tabletaziendale;

import me.phredss.tabletaziendale.commands.TabletCommand;
import me.phredss.tabletaziendale.handler.GuiContratto;
import me.phredss.tabletaziendale.handler.StampaContratto;
import me.phredss.tabletaziendale.handler.TabletClick;
import me.phredss.tabletaziendale.handler.luckperms.ContrattoPex;
import me.phredss.tabletaziendale.licenza.AdvancedLicense;
import me.phredss.tabletaziendale.util.DelayedTask;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class TabletAziendale extends JavaPlugin {

    public static TabletAziendale plugin;

    public static NamespacedKey tabletAziendaleKey;

    public static TabletAziendale getPlugin() {
        return plugin;
    }

    static TabletAziendale instance;



    private void plugininstance(TabletAziendale instance) {
        TabletAziendale plugin = instance;
    }



    public TabletAziendale() {
    }

    public void onEnable() {
        plugin = this;

        saveDefaultConfig();
        String licenza = getConfig().getString("LICENSE-KEY");

        if(!new AdvancedLicense(licenza, "https://phredsslicensesystem.000webhostapp.com/verify.php", this).register()) return;
        instance = this;
        tabletAziendaleKey = new NamespacedKey(this, "tablet-aziendale");
        this.getCommand("azienda").setExecutor(new TabletCommand());
        this.getServer().getPluginManager().registerEvents(new TabletClick(), this);
        this.getServer().getPluginManager().registerEvents(new GuiContratto(), this);
        this.getServer().getPluginManager().registerEvents(new StampaContratto(), this);
        this.getServer().getPluginManager().registerEvents(new ContrattoPex(), this);


        new DelayedTask(this);
    }

    public static TabletAziendale getInstance() {
        return instance;
    }

    public void onDisable() {
    }
}