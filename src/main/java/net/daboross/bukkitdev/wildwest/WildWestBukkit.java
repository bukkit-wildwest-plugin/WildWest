/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.wildwest;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author daboross
 */
public class WildWestBukkit extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.err.println("Water: Overridden Method Body Called: onEnable");
    }

    @Override
    public void onDisable() {
        System.err.println("Water: Overridden Method Body Called: onDisable");
    }

    @EventHandler
    public void waterPanning(PlayerBucketFillEvent event) {
        Player p = event.getPlayer();
        if (p.hasPermission("Panning")) {
            Location loc = p.getLocation();
            for (String panning : getConfig().getConfigurationSection("Pan Locations").getKeys(false)) {
                if (getConfig().getConfigurationSection("Pan Locations." + panning + ".Panning Location") != null) {
                    double X1 = getConfig().getDouble("Pan Locations." + panning + ".Panning Location.X");
                    double Y1 = getConfig().getDouble("Pan Locations." + panning + ".Panning Location.Y");
                    double Z1 = getConfig().getDouble("Pan Locations." + panning + ".Panning Location.Z");
                    String world1 = getConfig().getString("Pan Locations." + panning + ".Panning Location.World");
                    if (p.getWorld().getName().equalsIgnoreCase(world1)) {
                        Location ploc = new Location(Bukkit.getWorld(world1), X1, Y1, Z1);
                        //whatever pan distance you want
                        if (loc.distance(ploc) <= 5) {
                            //what ever chance you want for panning for each item
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manudelp " + p.getName() + " Panning");
                            final Player pl = p;
                            new BukkitRunnable() {
                                Integer t = 6;

                                @Override
                                public void run() {
                                    if (t <= 0) {
                                        pl.sendMessage("You've found nothing");
                                        cancel();
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp " + pl.getName() + " Panning");
                                    }
                                    pl.sendMessage("Panning...");
                                    Integer r = (int) (Math.random() * 100);
                                    if (r <= 5) {
                                        Integer ammount = (int) (Math.random() * 5);
                                        pl.getInventory().addItem(new ItemStack(Material.GOLD_NUGGET, ammount));
                                        pl.sendMessage("you've found " + ammount + " gold nuggets while panning water!");
                                    }
                                    t--;
                                }
                            }.runTaskTimer(this, 20, 20);

                        }
                    }
                }
            }
        }
    }
}
