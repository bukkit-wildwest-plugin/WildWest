/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.wildwest;

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
 * @author daboross
 */
public class WildWestConfiguration {

    private final WildWestBukkit wildWestBukkit;
    private final List<Location> panningLocations = new ArrayList<Location>();

    public WildWestConfiguration(WildWestBukkit wildWestBukkit) {
        this.wildWestBukkit = wildWestBukkit;
        FileConfiguration config = wildWestBukkit.getConfig();
        for (String panningLocationSection : config.getConfigurationSection("PanLocations").getKeys(false)) {
            double panningX = config.getDouble("Pan-locations." + panningLocationSection + ".x");
            double panningY = config.getDouble("Pan-locations." + panningLocationSection + ".y");
            double panningZ = config.getDouble("Pan-locations." + panningLocationSection + ".z");
            String panningWorldName = config.getString("Pan Locations." + panningLocationSection + ".world");
            World panningWorld = Bukkit.getWorld(panningWorldName);
            if (panningWorld == null) {
                wildWestBukkit.getLogger().log(Level.WARNING, "Invalid world in config: {0}", panningWorldName);
            } else {
                Location location = new Location(panningWorld, panningX, panningY, panningZ);
                if (location != null) {
                    panningLocations.add(location);
                }
            }
        }
    }

    public List<Location> getPanningLocations() {
        return Collections.unmodifiableList(panningLocations);
    }
}
