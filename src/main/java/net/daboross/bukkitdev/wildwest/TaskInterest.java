package net.daboross.bukkitdev.wildwest;

import net.daboross.bukkitdev.wildwest.moneyapi.MoneyAPI;
import org.bukkit.configuration.file.FileConfiguration;

public class TaskInterest implements Runnable {

    private final WildWestBukkit plugin;

    public TaskInterest(WildWestBukkit plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        WildWestConfiguration config = this.plugin.getWildWestConfiguration();
        if (config.isInterestEnabled()) {
            MoneyAPI money = plugin.getMoneyAPI();
            FileConfiguration conf = money.getMoneyConfig();
            if (!conf.getKeys(false).isEmpty()) {
                for (String keys : conf.getKeys(false)) {
                    double rate = config.getInterestRate() / 100.0D + 1.0D;
                    int time = config.getInterestTime();
                    double cmoney = money.getMoney(keys);
                    double p = cmoney * rate;
                    double a = Math.pow(p, time);
                    money.setMoney(keys, a);
                }
            }
        }
    }
}