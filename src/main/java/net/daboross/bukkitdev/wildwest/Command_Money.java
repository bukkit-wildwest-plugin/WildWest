package net.daboross.bukkitdev.wildwest;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

class Command_Money
  implements CommandExecutor
{
  private WildWestBukkit plugin;

  public Command_Money(WildWestBukkit plugin)
  {
    this.plugin = plugin;
  }

  public boolean onCommand(CommandSender sender, Command command, String string, String[] args)
  {
    if ((sender instanceof Player)) {
      Player player = (Player)sender;
      MoneyAPI mapi = MoneyAPI.getInstance();
      if (args.length == 0) {
        double money = mapi.getMoney(player);
        player.sendMessage(ChatColor.AQUA + player.getName());
        player.sendMessage(ChatColor.LIGHT_PURPLE + "Money: " + ChatColor.GOLD + mapi.getMoneyString(money));
      }
      else if (mapi.hasMoney(args[0])) {
        double money = mapi.getMoney(args[0]);
        player.sendMessage(ChatColor.AQUA + args[0]);
        player.sendMessage(ChatColor.LIGHT_PURPLE + "Money: " + ChatColor.GOLD + mapi.getMoneyString(money));
      } else {
        player.sendMessage(ChatColor.LIGHT_PURPLE + args[0] + ChatColor.RED + " Does not exist.");
      }
    }
    else {
      sender.sendMessage(ChatColor.RED + "This is for player use only.");
    }
    return true;
  }
}