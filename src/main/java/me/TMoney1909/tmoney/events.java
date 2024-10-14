package me.TMoney1909.tmoney;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;
import java.util.List;


public class events  implements Listener {


    @EventHandler
    public void onPlaceBlock (BlockPlaceEvent e) {
        Block block = e.getBlock();

        List<Location> locations = new ArrayList<>();
        locations.add(new Location(block.getWorld(), block.getX(), block.getY(), block.getZ()));
        Tmoney.blockLocationStorage.saveLocations(locations);


    }

    @EventHandler
    public void onBreakBlock (BlockBreakEvent e) {
        Block block = e.getBlock();

        Tmoney.blockLocationStorage.removeLocation(new Location(block.getWorld(), block.getX(), block.getY(), block.getZ()));

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer().hasPlayedBefore()) {
            event.setJoinMessage("\u00a77[\u00a7a+\u00a77] " + event.getPlayer().getName());
        } else {
            event.setJoinMessage("\u00a77[\u00a7a+\u00a77] " + event.getPlayer().getName() + " has joined for the first time!");
        }
    }


    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        event.setQuitMessage("\u00a77[\u00a7c-\u00a77] " + event.getPlayer().getName());
    }
}
