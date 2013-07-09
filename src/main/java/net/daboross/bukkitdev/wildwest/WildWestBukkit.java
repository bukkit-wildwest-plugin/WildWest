package net.daboross.bukkitdev.wildwest;

import java.io.File;
import org.bukkit.configuration.Configuration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 */
public class WildWestBukkit extends JavaPlugin implements Listener {

    private WildWestConfiguration config;
    @Override
    public void onEnable(){
        	    new MoneyAPI(this);

        	    createMoneyFile();

        	    getCommand("money").setExecutor(new Command_Money(this));
        	    getCommand("pay").setExecutor(new Command_Pay(this));
        	    getCommand("setbandit").setExecutor(new TeleportingBandits(this));

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
        	  {
        config = new WildWestConfiguration(this);
        getServer().getPluginManager().registerEvents(new GoldPanningListener(this), this);
        getServer().getPluginManager().registerEvents(new MayorMovementListener(this), this);
        getServer().getPluginManager().registerEvents(new BuyPlotsSignListener(this), this);
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new TeleportingBandits(this), this);
    }    
    @Override
    public void onDisable() {
    }

    public WildWestConfiguration getWildWestConfiguration() {
        return config;
    }
}