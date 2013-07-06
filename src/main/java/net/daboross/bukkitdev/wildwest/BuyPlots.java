package net.daboross.bukkitdev.wildwest;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class BuyPlots implements Listener {

    private final WildWestBukkit wildWestBukkit;

    public BuyPlots(WildWestBukkit wildWestBukkit) {
        this.wildWestBukkit = wildWestBukkit;
    }

    @EventHandler
    public void onSignCreate(SignChangeEvent sign) {
        Player player = sign.getPlayer();
        if (sign.getLine(0).equalsIgnoreCase("[BuyPlots]")) {
            player.sendMessage("Your BuyPlots sign has been set up correctly");
            sign.setLine(0, "[BuyPlots]");
            //More to be added, Tired xD
        }
    }
}
