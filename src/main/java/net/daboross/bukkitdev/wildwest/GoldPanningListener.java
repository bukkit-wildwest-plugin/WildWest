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
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author daboross
 */
public class GoldPanningListener implements Listener {

    private final WildWestBukkit wildWestBukkit;

    public GoldPanningListener(WildWestBukkit wildWestBukkit) {
        this.wildWestBukkit = wildWestBukkit;
    }

    @EventHandler
    public void waterPanning(PlayerBucketFillEvent event) {
        Player p = event.getPlayer();
        if (p.hasPermission("Panning")) {
            for (Location panningLocation : wildWestBukkit.getWildWestConfiguration().getPanningLocations()) {
                if (p.getLocation().getWorld() == panningLocation.getWorld()) {
                    //whatever pan distance you want
                    if (p.getLocation().distance(panningLocation) <= 5) {
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
                        }.runTaskTimer(wildWestBukkit, 20, 20);
                    }
                }
            }
        }
    }
}
