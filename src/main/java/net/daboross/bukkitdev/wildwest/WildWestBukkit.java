/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.wildwest;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author daboross
 */
public class WildWestBukkit extends JavaPlugin implements Listener {

    private WildWestConfiguration config;

    @Override
    public void onEnable() {
        config = new WildWestConfiguration(this);
    }

    @Override
    public void onDisable() {
    }

    public WildWestConfiguration getWildWestConfiguration() {
        return config;
    }
}
