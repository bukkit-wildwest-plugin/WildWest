package net.daboross.bukkitdev.wildwest.moneyapi;

import net.daboross.bukkitdev.wildwest.WildWestBukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

class Command_Pay
  implements CommandExecutor
{
  private WildWestBukkit plugin;

  public Command_Pay(WildWestBukkit plugin)
  {
    this.plugin = plugin;
  }

  public boolean onCommand(CommandSender sender, Command command, String string, String[] args)
  {
    if ((sender instanceof Player)) {
      Player player = (Player)sender;
      MoneyAPI money = MoneyAPI.getInstance();
      if (args.length == 2) {
        if (money.hasMoney(args[0]))
          try {
            double amount = Double.parseDouble(args[1]);
            double playermoney = money.getMoney(player);
            if (playermoney >= amount) {
              money.removeMoney(player, amount);
              money.addMoney(args[0], amount);
              player.sendMessage(ChatColor.GREEN + "sent " + ChatColor.GOLD + amount + ChatColor.GREEN + " to " + args[0]);
              for (Player players : this.plugin.getServer().getOnlinePlayers())
                if (players.getName().equalsIgnoreCase(args[0]))
                  player.sendMessage(ChatColor.GREEN + "You got " + ChatColor.GOLD + amount + ChatColor.GREEN + " from " + player.getName());
            }
            else
            {
              player.sendMessage(ChatColor.RED + "You don't have enough money for that.");
            }
          } catch (Exception e) {
            player.sendMessage(ChatColor.LIGHT_PURPLE + args[1] + ChatColor.RED + " is not a valid amount.");
            return false;
          }
        else
          player.sendMessage(ChatColor.LIGHT_PURPLE + args[0] + ChatColor.RED + " does not exist.");
      }
      else
        return false;
    }
    else {
      sender.sendMessage(ChatColor.RED + "This is for player use only.");
    }
    return true;
  }
}