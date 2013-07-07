package main.java.net.daboross.bukkitdev.wildwest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 */
public class WildWestConfiguration {

    private final WildWestBukkit wildWestBukkit;
    private final List<Location> panningLocations = new ArrayList<Location>();

    public WildWestConfiguration(WildWestBukkit wildWestBukkit) {
        this.wildWestBukkit = wildWestBukkit;
        reloadConfig0();

    }

    private void reloadConfig0() {
        FileConfiguration config = wildWestBukkit.getConfig();
        for (String panningLocationSection : config.getConfigurationSection("pan-locations").getKeys(false)) {
            double panningX = config.getDouble("pan-locations." + panningLocationSection + ".x");
            double panningY = config.getDouble("pan-locations." + panningLocationSection + ".y");
            double panningZ = config.getDouble("pan-locations." + panningLocationSection + ".z");
            String panningWorldName = config.getString("pan-locations." + panningLocationSection + ".world");
            World panningWorld = Bukkit.getWorld(panningWorldName);
            if (panningWorld == null) {
                wildWestBukkit.getLogger().log(Level.WARNING, "Invalid world in config: {0}", panningWorldName);
            } else {
                Location location = new Location(panningWorld, panningX, panningY, panningZ);
                panningLocations.add(location);
            }
        }
    }

    public void reloadConfig() {
        reloadConfig0();
    }

    public List<Location> getPanningLocations() {
        return Collections.unmodifiableList(panningLocations);
    }

    public int getDistanceMayorCanMove() {
        return 50; // To Be Implemented
    }
}
