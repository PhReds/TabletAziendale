package me.phredss.tabletaziendale.config;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

@Getter
public class FileManager {
    private final JavaPlugin plugin;
    private final File file;
    private YamlConfiguration config;

    public FileManager(String name, JavaPlugin plugin) {
        this.plugin = plugin;
        file = new File(plugin.getDataFolder(),name + ".yml");
        setup();
    }

    public boolean reload() {
        boolean result;
        try {
            config = YamlConfiguration.loadConfiguration(file);
            result = true;
        }
        catch (Exception e) {
            result = false;
        }
        return result;
    }

    @SneakyThrows
    public void saveConfig() throws IOException {
        config.save(file);
    }

    public void delete() {
        file.delete();
        setup();
    }
    public void setup() {
        if (!file.exists()) plugin.saveResource(file.getName(),false);
        reload();
    }
}
