package net.daboross.bukkitdev.wildwest;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

/**
 *
 */
public class BuyPlotsSignListener implements Listener {

    private final WildWestBukkit wildWestBukkit;

    public BuyPlotsSignListener(WildWestBukkit wildWestBukkit) {
        this.wildWestBukkit = wildWestBukkit;
    }

    @EventHandler
    public void onSignCreate(SignChangeEvent sign) {
        Player player = sign.getPlayer();
        if (sign.getLine(0).equalsIgnoreCase("[BuyPlots]")) {
            player.sendMessage(MessageStatic.BUYPLOTS_CONFIRMATION);
            sign.setLine(0, "[BuyPlots]");
        }
    }
}
