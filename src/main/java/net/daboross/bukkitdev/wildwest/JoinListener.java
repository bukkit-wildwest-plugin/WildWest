/**
 *
 */
package net.daboross.bukkitdev.wildwest;
 
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
 
public class JoinListener implements Listener {
        FireworkEffectPlayer fplayer = new FireworkEffectPlayer();
        public JoinListener(WildWestBukkit wildWestBukkit) {
 	}
        @EventHandler
        public void onPlayerJoinEvent(PlayerJoinEvent event) throws Exception{
          if(!event.getPlayer().hasPlayedBefore()) {
                Player player = event.getPlayer();
                Location location = player.getLocation();
            event.getPlayer().sendMessage("Message Here");
            this.fplayer.playFirework(player.getWorld(), location,FireworkEffect.builder().withColor(Color.GREEN).withFade(Color.GREEN).with(FireworkEffect.Type.BURST).flicker(true).trail(false).build());
          }
        }        
}
