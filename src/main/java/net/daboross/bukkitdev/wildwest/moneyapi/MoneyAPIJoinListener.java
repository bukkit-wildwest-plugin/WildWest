/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.wildwest.moneyapi;

import net.daboross.bukkitdev.wildwest.WildWestBukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author daboross
 */
public class MoneyAPIJoinListener implements Listener {

    private final WildWestBukkit wildWestBukkit;

    public MoneyAPIJoinListener(WildWestBukkit wildWestBukkit) {
        this.wildWestBukkit = wildWestBukkit;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        MoneyAPI moneyAPI = wildWestBukkit.getMoneyAPI();
        if (!moneyAPI.hasMoney(e.getPlayer().getName())) {
            moneyAPI.createPlayerMoney(e.getPlayer());
        }
    }
}