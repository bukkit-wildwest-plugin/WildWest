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
        final Player p = event.getPlayer();
        if (p.hasPermission("wildwest.goldpanning")) {
            p.sendMessage("Panning...");
            new BukkitRunnable() {
                Integer panningTimes = 6;

                @Override
                public void run() {
                    if (panningTimes <= 0) {
                        p.sendMessage("You've found nothing");
                        cancel();
                    }
                    p.sendMessage("Panning...");
                    Integer r = (int) (Math.random() * 100);
                    if (r <= 5) {
                        Integer ammount = (int) (Math.random() * 5);
                        p.getInventory().addItem(new ItemStack(Material.GOLD_NUGGET, ammount));
                        p.sendMessage("You've found " + ammount + " gold nuggets while panning water!");
                    }
                    p.sendMessage("Panning...");
                    panningTimes--;
                }
            }.runTaskTimer(wildWestBukkit, 20, 20);
        }
    }
}
