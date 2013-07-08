package net.daboross.bukkitdev.wildwest.moneyapi;

import net.daboross.bukkitdev.wildwest.WildWestBukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMoney implements CommandExecutor {

    private WildWestBukkit plugin;

    public CommandMoney(WildWestBukkit plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        if ((sender instanceof Player)) {
            Player player = (Player) sender;
            MoneyAPI mapi = plugin.getMoneyAPI();
            if (args.length == 0) {
                double money = mapi.getMoney(player);
                player.sendMessage(ChatColor.AQUA + player.getName());
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Money: " + ChatColor.GOLD + mapi.getMoneyString(money));
            } else if (mapi.hasMoney(args[0])) {
                double money = mapi.getMoney(args[0]);
                player.sendMessage(ChatColor.AQUA + args[0]);
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Money: " + ChatColor.GOLD + mapi.getMoneyString(money));
            } else {
                player.sendMessage(ChatColor.LIGHT_PURPLE + args[0] + ChatColor.RED + " Does not exist.");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "This is for player use only.");
        }
        return true;
    }
}