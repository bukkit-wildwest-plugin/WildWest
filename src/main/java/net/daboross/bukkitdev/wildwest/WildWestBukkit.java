package net.daboross.bukkitdev.wildwest;

import net.daboross.bukkitdev.wildwest.moneyapi.MoneyAPI;
import net.daboross.bukkitdev.wildwest.moneyapi.CommandPay;
import net.daboross.bukkitdev.wildwest.moneyapi.CommandMoney;
import java.io.File;
import java.util.logging.Level;
import net.daboross.bukkitdev.wildwest.moneyapi.MoneyAPIJoinListener;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 *
 */
public class WildWestBukkit extends JavaPlugin {

    private WildWestConfiguration config;
    private Permission permissionHandler;
    private Economy economyHandler;
    private MoneyAPI moneyAPI;
    private TeleportingBandits teleportingBandits;

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        if (!setupVault(pm)) {
            getLogger().log(Level.SEVERE, "Can't enable!");
            pm.disablePlugin(this);
        }

        config = new WildWestConfiguration(this);
        moneyAPI = new MoneyAPI(this);
        teleportingBandits = new TeleportingBandits(this);

        registerEvents(pm);
        createMoneyFile();
        registerCommands();
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new TaskInterest(this),
                72000 * config.getInterestTime(), 72000 * config.getInterestTime());
    }

    @Override
    public void onDisable() {
    }

    private boolean setupVault(PluginManager pm) {
        if (pm.isPluginEnabled("Vault")) {
            RegisteredServiceProvider<Permission> registeredPermissionServiceProvider =
                    Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
            permissionHandler = registeredPermissionServiceProvider.getProvider();
            RegisteredServiceProvider<Economy> registeredEconomyServiceProvider =
                    Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
            economyHandler = registeredEconomyServiceProvider.getProvider();
            getLogger().log(Level.INFO, "Vault found. Permission Handler {0}. Economy Handler {1}.",
                    new Object[]{permissionHandler == null ? "Not Found" : "Found", economyHandler == null ? "Not Found" : "Found"});
            if (permissionHandler == null || economyHandler == null) {
                return false;
            }
        } else {
            getLogger().log(Level.INFO, "Vault not found.");
            return false;
        }
        return true;
    }

    private void registerEvents(PluginManager pm) {
        pm.registerEvents(new GoldPanningListener(this), this);
        pm.registerEvents(new MayorMovementListener(this), this);
        pm.registerEvents(new BuyPlotsSignListener(this), this);
        pm.registerEvents(new FireWorksJoinListener(this), this);
        pm.registerEvents(teleportingBandits, this);
        pm.registerEvents(new MoneyAPIJoinListener(this), this);
    }

    private void registerCommands() {
        PluginCommand money = getCommand("money");
        PluginCommand pay = getCommand("pay");
        PluginCommand setbandit = getCommand("setbandit");
        if (money != null) {
            money.setExecutor(new CommandMoney(this));
        }
        if (pay != null) {
            pay.setExecutor(new CommandPay(this));
        }
        if (setbandit != null) {
            setbandit.setExecutor(teleportingBandits);
        }
    }

    private void createMoneyFile() {
        saveResource("money.yml", false);
        //Don't need to check if it exists, saveResource() already does.
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

    public MoneyAPI getMoneyAPI() {
        return moneyAPI;
    }
}
