package net.daboross.bukkitdev.wildwest;

import java.io.File;
import java.util.logging.Level;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 *
 */
public class WildWestBukkit extends JavaPlugin implements Listener {

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
        pm.registerEvents(new JoinListener(this), this);
        pm.registerEvents(new TeleportingBandits(this), this);
    }
    {
        new MoneyAPI(this);

        createMoneyFile();

        getCommand("money").setExecutor(new Command_Money(this));
        getCommand("pay").setExecutor(new Command_Pay(this));

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Task_Interest(this), 72000 * getConfig().getInt("Interest.Time"), 72000 * getConfig().getInt("Interest.Time"));

        getServer().getPluginManager().registerEvents(this, this);

        Configuration config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
      }

      public void createMoneyFile() {
        File money = new File(getDataFolder(), "money.yml");
        if (!money.exists())
          saveResource("money.yml", false);
      }

      @EventHandler
      public void onPlayerJoin(PlayerJoinEvent e)
      {
        MoneyAPI money = MoneyAPI.getInstance();
        if (!money.hasMoney(e.getPlayer().getName()))
          money.createPlayerMoney(e.getPlayer());
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
