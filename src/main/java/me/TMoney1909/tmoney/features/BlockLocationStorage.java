package me.TMoney1909.tmoney.features;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;


public class BlockLocationStorage {
    private final File file;
    private YamlConfiguration config;

    public BlockLocationStorage(String fileName) {
        file = new File(fileName);
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void saveLocations(List<Location> locations) {
        List<String> locationStrings = config.getStringList("locations");
        for (Location location : locations) {
            locationStrings.add(location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ());
        }
        config.set("locations", locationStrings);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void removeLocation(Location location) {
        List<String> locationStrings = config.getStringList("locations");
        String locationString = location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ();
        if (locationStrings.contains(locationString)) {
            locationStrings.remove(locationString);
            config.set("locations", locationStrings);
            try {
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean containsLocation(Location location) {
        List<String> locationStrings = config.getStringList("locations");
        String locationString = location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ();
        return locationStrings.contains(locationString);
    }

    public List<Location> loadLocations() {
        List<Location> locations = new ArrayList<>();
        List<String> locationStrings = config.getStringList("locations");
        for (String locationString : locationStrings) {
            String[] parts = locationString.split(":");
            if (parts.length == 4) {
                locations.add(new Location(Bukkit.getWorld(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
            }
        }
        return locations;

    }
}