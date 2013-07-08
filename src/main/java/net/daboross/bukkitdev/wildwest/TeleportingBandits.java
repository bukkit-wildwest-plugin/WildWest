package net.daboross.bukkitdev.wildwest;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class TeleportingBandits implements Listener, CommandExecutor {
    
    private final WildWestBukkit wildWestBukkit;
    
    public TeleportingBandits(WildWestBukkit wildWestBukkit) {
        this.wildWestBukkit = wildWestBukkit;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("setbandit")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Players only.");
                return true;
            }
            Player p = (Player) sender;
            wildWestBukkit.getWildWestConfiguration().setBanditLocation(p.getLocation());
            p.sendMessage(MessageStatic.BANDIT_SET);
            return true;
        }
        sender.sendMessage("Command " + cmd + " unknown to TeleportingBandits.");
        return false;
        
    }
    
    @EventHandler
    public void onSignClick(PlayerInteractEvent p) {
        Material t = p.getClickedBlock().getType();
        if ((t == Material.WALL_SIGN || t == Material.SIGN || t == Material.SIGN_POST)
                && p.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Sign sign = (Sign) p.getClickedBlock().getState();
            if (sign.getLine(0).contains("[Bandit]")) {
                Player p2 = p.getPlayer();
                p2.teleport(wildWestBukkit.getWildWestConfiguration().getBanditLocation());
                p2.sendMessage(ChatColor.GREEN + "Teleport bandit");
            }
        }
        
    }
}
