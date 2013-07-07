package main.java.net.daboross.bukkitdev.wildwest;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 */
public class GoldPanningListener implements Listener {

    private final WildWestBukkit wildWestBukkit;

    public GoldPanningListener(WildWestBukkit wildWestBukkit) {
        this.wildWestBukkit = wildWestBukkit;
    }

    @EventHandler
    public void waterPanning(PlayerBucketFillEvent event) {
        final Player p = event.getPlayer();
        if (p.hasPermission(PermissionStatic.GOLD_PANNING)) {
            p.sendMessage(MessageStatic.PANNING_WAITING);
            new BukkitRunnable() {
                Integer panningTimes = 6;

                @Override
                public void run() {
                    if (panningTimes <= 0) {
                        p.sendMessage(MessageStatic.PANNING_NOTHING_FOUND);
                        cancel();
                    }
                    Integer r = (int) (Math.random() * 100);
                    if (r <= 5) {
                        Integer amount = (int) (Math.random() * 5);
                        p.getInventory().addItem(new ItemStack(Material.GOLD_NUGGET, amount));
                        p.sendMessage(String.format(MessageStatic.PANNING_FOUND, amount));
                    }
                    p.sendMessage(MessageStatic.PANNING_WAITING);
                    panningTimes--;
                }
            }.runTaskTimer(wildWestBukkit, 20, 20);
        }
    }
}
