import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;


public class Thirst implements Listener{
  public static Bukkit plugin;
	
	public void BukkitListener(Bukkit instance){
		
		plugin = instance;
	}
     public void onSignCreate(SignChangeEvent sign){
    	 Player player = sign.getPlayer();
    	 if(sign.getLine(0).equalsIgnoreCase("[BuyPlots]")){
    		 player.sendMessage("Your BuyPlots sign has been set up correctly");
    		 sign.setLine(0, "[BuyPlots]");
    		 //More to be added, Tired xD
    	 }
     }
}
