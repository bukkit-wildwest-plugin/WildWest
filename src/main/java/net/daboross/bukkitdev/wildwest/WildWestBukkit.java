package net.daboross.bukkitdev.wildwest;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 */
public class WildWestBukkit extends JavaPlugin {

    private WildWestConfiguration config;

    @Override
    public void onEnable() {
        config = new WildWestConfiguration(this);
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new GoldPanningListener(this), this);
        pm.registerEvents(new MayorMovementListener(this), this);
        pm.registerEvents(new BuyPlotsSignListener(this), this);
    }

    @Override
    public void onDisable() {
    }

    public WildWestConfiguration getWildWestConfiguration() {
        return config;
    }
}
