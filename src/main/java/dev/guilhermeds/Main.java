package dev.guilhermeds;

import dev.guilhermeds.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Location PVP_LOCATION = new Location(Bukkit.getWorld("world"), 0, 67, 0),
            SPAWN_LOCATION = new Location(Bukkit.getWorld("world"), 0, 143, 0);

    @Override
    public void onEnable() {
        super.onEnable();

        this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }
}