package net.daboross.bukkitdev.wildwest.moneyapi;

import java.io.File;
import net.daboross.bukkitdev.wildwest.WildWestBukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class MoneyAPI {

    private WildWestBukkit plugin;

    public MoneyAPI(WildWestBukkit plugin) {
        this.plugin = plugin;
    }

    public File getMoneyFile() {
        return new File(this.plugin.getDataFolder(), "money.yml");
    }

    public FileConfiguration getMoneyConfig() {
        File file = getMoneyFile();
        FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
        return conf;
    }

    public boolean hasMoney(Player player) {
        FileConfiguration conf = getMoneyConfig();
        if (conf.contains(player.getName())) {
            return true;
        }
        return false;
    }

    public boolean hasMoney(String name) {
        FileConfiguration conf = getMoneyConfig();
        if (conf.contains(name)) {
            return true;
        }
        return false;
    }

    public void createPlayerMoney(Player player) {
        File file = getMoneyFile();
        FileConfiguration conf = getMoneyConfig();
        double startmoney = this.plugin.getConfig().getDouble("Starting Balance");
        conf.addDefault(player.getName() + ".Money", Double.valueOf(startmoney));
        conf.options().copyDefaults(true);
        try {
            conf.save(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createPlayerMoney(String name) {
        File file = getMoneyFile();
        FileConfiguration conf = getMoneyConfig();
        double startmoney = this.plugin.getConfig().getDouble("Starting Balance");
        conf.addDefault(name + ".Money", Double.valueOf(startmoney));
        conf.options().copyDefaults(true);
        try {
            conf.save(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public double getMoney(Player player) {
        FileConfiguration conf = getMoneyConfig();
        double money = conf.getDouble(player.getName() + ".Money");
        return money;
    }

    public double getMoney(String name) {
        FileConfiguration conf = getMoneyConfig();
        double money = conf.getDouble(name + ".Money");
        return money;
    }

    public String getMoneyString(double amount) {
        String money = "0.0 " + this.plugin.getConfig().getString("Money Name.Plural");
        if (amount == 1.0D) {
            money = "1.0 " + this.plugin.getConfig().getString("Money Name.Single");
        } else {
            money = amount + " " + this.plugin.getConfig().getString("Money Name.Plural");
        }
        return money;
    }

    public void addMoney(Player player, double amount) {
        File file = getMoneyFile();
        FileConfiguration conf = getMoneyConfig();
        double money = getMoney(player);
        conf.set(player.getName() + ".Money", Double.valueOf(money + amount));
        conf.options().copyDefaults(true);
        try {
            conf.save(file);
        } catch (Exception e) {
        }
    }

    public void addMoney(String name, double amount) {
        File file = getMoneyFile();
        FileConfiguration conf = getMoneyConfig();
        double money = getMoney(name);
        conf.set(name + ".Money", Double.valueOf(money + amount));
        conf.options().copyDefaults(true);
        try {
            conf.save(file);
        } catch (Exception e) {
        }
    }

    public void removeMoney(Player player, double amount) {
        File file = getMoneyFile();
        FileConfiguration conf = getMoneyConfig();
        double money = getMoney(player);
        conf.set(player.getName() + ".Money", Double.valueOf(money - amount));
        conf.options().copyDefaults(true);
        try {
            conf.save(file);
        } catch (Exception e) {
        }
    }

    public void removeMoney(String name, double amount) {
        File file = getMoneyFile();
        FileConfiguration conf = getMoneyConfig();
        double money = getMoney(name);
        conf.set(name + ".Money", Double.valueOf(money - amount));
        conf.options().copyDefaults(true);
        try {
            conf.save(file);
        } catch (Exception e) {
        }
    }

    public void setMoney(Player player, double amount) {
        File file = getMoneyFile();
        FileConfiguration conf = getMoneyConfig();
        conf.set(player.getName() + ".Money", Double.valueOf(amount));
        conf.options().copyDefaults(true);
        try {
            conf.save(file);
        } catch (Exception e) {
        }
    }

    public void setMoney(String name, double amount) {
        File file = getMoneyFile();
        FileConfiguration conf = getMoneyConfig();
        conf.set(name + ".Money", Double.valueOf(amount));
        conf.options().copyDefaults(true);
        try {
            conf.save(file);
        } catch (Exception e) {
        }
    }
}
