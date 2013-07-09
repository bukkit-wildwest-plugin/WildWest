package net.daboross.bukkitdev.wildwest;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 */
public class FireWorksJoinListener implements Listener {

    private final WildWestBukkit wildWestBukkit;
    private final FireworkEffectPlayer fireworkEffectPlayer = new FireworkEffectPlayer();
    private final FireworkEffect fireworkEffect = FireworkEffect.builder().withColor(Color.GREEN).withFade(Color.GREEN).with(FireworkEffect.Type.BURST).flicker(true).trail(false).build();

    public FireWorksJoinListener(WildWestBukkit wildWestBukkit) {
        this.wildWestBukkit = wildWestBukkit;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) throws Exception {
        if (!event.getPlayer().hasPlayedBefore()) {
            Player player = event.getPlayer();
            Location location = player.getLocation();
            event.getPlayer().sendMessage(MessageStatic.PLAYER_JOIN_WITH_FIREWORK);
            fireworkEffectPlayer.playFirework(player.getWorld(), location, fireworkEffect);
        }
    }
}