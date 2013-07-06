package net.daboross.bukkitdev.wildwest;

import java.util.logging.Level;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 */
public class WildWestBukkit extends JavaPlugin {

    private WildWestConfiguration config;
    private Permission permissionHandler;
    private Economy economyHandler;

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        if (pm.isPluginEnabled("Vault")) {
            RegisteredServiceProvider<Permission> registeredPermissionServiceProvider = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
            permissionHandler = registeredPermissionServiceProvider.getProvider();
            RegisteredServiceProvider<Economy> registeredEconomyServiceProvider = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
            economyHandler = registeredEconomyServiceProvider.getProvider();
            getLogger().log(Level.INFO, "Vault found. Permission Handler {0}. Economy Handler {1}.", new Object[]{permissionHandler == null ? "Not Found" : "Found", economyHandler == null ? "Not Found" : "Found"});
            if (permissionHandler == null || economyHandler == null) {
                getLogger().log(Level.SEVERE, "Can't enable!");
                pm.disablePlugin(this);
                return;
            }
        } else {
            getLogger().log(Level.INFO, "Vault not found.");
        }
        config = new WildWestConfiguration(this);
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

    public Permission getPermissionHandler() {
        return permissionHandler;
    }

    public Economy getEconomyHandler() {
        return economyHandler;
    }
}
