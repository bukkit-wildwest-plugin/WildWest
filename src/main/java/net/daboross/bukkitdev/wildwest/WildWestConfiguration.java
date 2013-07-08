package net.daboross.bukkitdev.wildwest;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 */
public class WildWestConfiguration {

    private final WildWestBukkit wildWestBukkit;
    private boolean interestEnabled;
    private double interestRate;
    private int interestTime;
    private int distanceMayorCanMove;
    private int banditX, banditY, banditZ;
    private String banditWorldName;

    public WildWestConfiguration(WildWestBukkit wildWestBukkit) {
        this.wildWestBukkit = wildWestBukkit;
        reloadConfig0();
    }

    private void reloadConfig0() {
        FileConfiguration config = wildWestBukkit.getConfig();
        wildWestBukkit.saveDefaultConfig();
        interestEnabled = config.getBoolean("interest.enabled");
        interestRate = config.getDouble("interest.rate");
        interestTime = config.getInt("interest.time");
        distanceMayorCanMove = 50; // Where?
        banditX = config.getInt("bandit.location.x");
        banditY = config.getInt("bandit.location.y");
        banditZ = config.getInt("bandit.location.z");
        banditWorldName = config.getString("bandit.location.world");
    }

    public void reloadConfig() {
        reloadConfig0();
    }

    public int getDistanceMayorCanMove() {
        return distanceMayorCanMove;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getInterestTime() {
        return interestTime;
    }

    public boolean isInterestEnabled() {
        return interestEnabled;
    }

    public Location getBanditLocation() {
        World banditWorld = Bukkit.getWorld(banditWorldName);
        if (banditWorld == null) {
            throw new IllegalStateException("Bandit world unknown!");
            // Anyone know of a better exception to throw here other than
            // IllegalStateException?
        }
        return new Location(banditWorld, banditX, banditY, banditZ);
    }

    public void setBanditLocation(Location l) {
        banditX = (int) l.getX();
        banditY = (int) l.getY();
        banditZ = (int) l.getZ();
        banditWorldName = l.getWorld().getName();
    }
}
