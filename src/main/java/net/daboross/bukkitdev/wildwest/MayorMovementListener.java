/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.wildwest;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 *
 * @author daboross
 */
public class MayorMovementListener {
    
    @EventHandler
    public void mayorMovement(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        if (townMayors.containsKey(p.getName())) {
            String town = townMayors.get(p.getName());
            Location loc = townCenters.get(town);
            //distance listed here should be config set
            if (event.getTo().distance(loc) >= 50) {
                p.teleport(p);
                p.sendMessage("You cannont leave your town");
            }
        }
    }
}
