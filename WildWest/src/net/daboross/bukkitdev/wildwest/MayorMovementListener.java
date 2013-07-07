package net.daboross.bukkitdev.wildwest;

import java.util.Map;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 *
 */
public class MayorMovementListener implements Listener {

    private Map<String, String> townMayors = null;//stub
    private Map<String, Location> townCenters = null;//stub
    private WildWestBukkit wildWestBukkit;

    public MayorMovementListener(WildWestBukkit wildWestBukkit) {
        this.wildWestBukkit = wildWestBukkit;
    }

    @EventHandler
    public void mayorMovement(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        if (townMayors.containsKey(p.getName())) {
            String town = townMayors.get(p.getName());
            Location loc = townCenters.get(town);
            if (event.getTo().distance(loc) >= wildWestBukkit.getWildWestConfiguration().getDistanceMayorCanMove()) {
                p.teleport(p);
                //But the player hasn't even moved yet in the server...
                //So the above line doing something is a glitch...
                event.setCancelled(true);
                p.sendMessage(MessageStatic.MAYOR_CANNOT_LEAVE);

            }
        }
    }
}