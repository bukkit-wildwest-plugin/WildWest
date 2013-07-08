package net.daboross.bukkitdev.wildwest;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;

public class TeleportingBandits implements Listener, CommandExecutor{
	private WildWestBukkit plugin;

	  public TeleportingBandits(WildWestBukkit plugin)
	  {
	    this.plugin = plugin;
	  }
	
        public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
    	Player p = (Player) sender;
    	if (commandLabel.equalsIgnoreCase("setbandit")){
            plugin.getConfig().set("bandit.world", p.getLocation().getWorld().getName());
            plugin.getConfig().set("bandit.x", p.getLocation().getX());
            plugin.getConfig().set("bandit.y", p.getLocation().getY());
            plugin.getConfig().set("bandit.z", p.getLocation().getZ());
            plugin.saveConfig();
            p.sendMessage(ChatColor.GREEN + "bandit set!");
            return true;                 
   }
		return false;
    
    }
    @EventHandler
    public void onSignClick(PlayerInteractEvent p) {
        Material t = p.getClickedBlock().getType();
            if ((t == Material.WALL_SIGN || t == Material.SIGN
                || t == Material.SIGN_POST)
                && p.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Sign sign = (Sign) p.getClickedBlock().getState();
            if (sign.getLine(0).contains("[Bandit]")) {
            	Player p2 = (Player) p.getPlayer();
                World w = Bukkit.getServer().getWorld(plugin.getConfig().getString("bandit.world"));
                double x = plugin.getConfig().getDouble("bandit.x");
                double y = plugin.getConfig().getDouble("bandit.y");
                double z = plugin.getConfig().getDouble("bandit.z");
                p2.teleport(new Location(w, x, y, z));
                p2.sendMessage(ChatColor.GREEN + "Teleport bandit");
             }
         }
     }  
}

