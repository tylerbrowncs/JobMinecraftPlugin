package me.TMoney1909.tmoney.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.time.Duration;

public class ManageMenuEvents implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!event.getView().getTitle().equals("Manage Player")) {
            return;
        }

        event.setCancelled(true);

        if (event.getCurrentItem() == null) {
            return;
        }

        Player target = Bukkit.getPlayer(event.getView().getItem(13).getItemMeta().getDisplayName());

        if (target == null) {
            player.closeInventory();
            player.sendMessage(ChatColor.RED + "The player left!");
            return;
        }

        if (event.getCurrentItem().getType().equals(Material.ENDER_PEARL)) {
            player.sendMessage("\u00a77Teleporting to \u00a7c" + target.getName());
            player.teleport(target);
            player.closeInventory();
            return;
        } else if (event.getCurrentItem().getType().equals(Material.BARRIER)) {
            player.closeInventory();
            new ReasonMenu(player, target,"Kick");

        } else if (event.getCurrentItem().getType().equals(Material.BOOK)) {
            player.closeInventory();
            new ReasonMenu(player, target,"Mute0.5");
        } else if (event.getCurrentItem().getType().equals(Material.WRITABLE_BOOK)) {
            player.closeInventory();
            new ReasonMenu(player, target,"Mute3");
        } else if (event.getCurrentItem().getType().equals(Material.WRITTEN_BOOK)) {
            player.closeInventory();
            new ReasonMenu(player, target,"Mute24");
        } else if (event.getCurrentItem().getType().equals(Material.WOODEN_AXE)) {
            player.closeInventory();
            new ReasonMenu(player, target, "Ban1");
        } else if (event.getCurrentItem().getType().equals(Material.IRON_AXE)) {
            player.closeInventory();
            new ReasonMenu(player, target, "Ban3");
        } else if (event.getCurrentItem().getType().equals(Material.GOLDEN_AXE)) {
            player.closeInventory();
            new ReasonMenu(player, target, "Ban7");
        } else if (event.getCurrentItem().getType().equals(Material.DIAMOND_AXE)) {
            player.closeInventory();
            new ReasonMenu(player, target, "Ban30");

        } else if (event.getCurrentItem().getType().equals(Material.NETHERITE_AXE)) {
            player.closeInventory();
            new ReasonMenu(player, target, "BanPerm");
        }
    }
}
