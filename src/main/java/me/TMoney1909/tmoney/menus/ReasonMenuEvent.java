package me.TMoney1909.tmoney.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ReasonMenuEvent implements Listener {
    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!event.getView().getTitle().equals("Reason?")) {
            return;
        }

        event.setCancelled(true);

        if (event.getCurrentItem() == null) {
            return;
        }

        Player target = Bukkit.getPlayer(event.getView().getItem(4).getItemMeta().getDisplayName());

        if (target == null) {
            player.closeInventory();
            player.sendMessage(ChatColor.RED + "The player left!");
            return;
        }


        player.closeInventory();

        String ban_message = "No reason given!";

        if (event.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)) {
            ban_message = "\u00a77The use of a hacked client.";
        } else if (event.getCurrentItem().getType().equals(Material.DIAMOND_PICKAXE)) {
            ban_message = "\u00a77The use of X-Ray.";
        } else if (event.getCurrentItem().getType().equals(Material.COMMAND_BLOCK_MINECART)) {
            ban_message = "\u00a77Exploiting bugs.";
        }

        if (event.getView().getItem(4).getItemMeta().getLore().get(1).equals("\u00a77Punishment: \u00a7cKick")) {
            target.kickPlayer("\u00a7cYou have been kicked for: \u00a7c" + ban_message);
        } else if (event.getView().getItem(4).getItemMeta().getLore().get(1).equals("\u00a77Punishment: \u00a7cMute0.5")) {
            player.sendMessage("\u00a77User has been muted for 30 minutes! \n\u00a7c\u00a7lReason: \u00a7c" + ban_message + "\n\u00a7cMUTE FUNCTION NOT IMPLEMENTED!");
        } else if (event.getView().getItem(4).getItemMeta().getLore().get(1).equals("\u00a77Punishment: \u00a7cMute3")) {
            player.sendMessage("\u00a77User has been muted for 3 hours! \n\u00a7c\u00a7lReason: \u00a7c" + ban_message + "\n\u00a7cMUTE FUNCTION NOT IMPLEMENTED!");
        } else if (event.getView().getItem(4).getItemMeta().getLore().get(1).equals("\u00a77Punishment: \u00a7cMute24")) {
            player.sendMessage("\u00a77User has been muted for 24 hours! \n\u00a7c\u00a7lReason: \u00a7c" + ban_message + "\n\u00a7cMUTE FUNCTION NOT IMPLEMENTED!");
        } else if (event.getView().getItem(4).getItemMeta().getLore().get(1).equals("\u00a77Punishment: \u00a7cBan1")) {
            player.sendMessage("\u00a77User has been banned for 24 hours! \n\u00a7c\u00a7lReason: \u00a7c" + ban_message + "\n\u00a7cBAN FUNCTION NOT IMPLEMENTED!");
        } else if (event.getView().getItem(4).getItemMeta().getLore().get(1).equals("\u00a77Punishment: \u00a7cBan3")) {
            player.sendMessage("\u00a77User has been banned for 72 hours! \n\u00a7c\u00a7lReason: \u00a7c" + ban_message + "\n\u00a7cBAN FUNCTION NOT IMPLEMENTED!");
        } else if (event.getView().getItem(4).getItemMeta().getLore().get(1).equals("\u00a77Punishment: \u00a7cBan7")) {
            player.sendMessage("\u00a77User has been banned for 7 days! \n\u00a7c\u00a7lReason: \u00a7c" + ban_message + "\n\u00a7cBAN FUNCTION NOT IMPLEMENTED!");
        } else if (event.getView().getItem(4).getItemMeta().getLore().get(1).equals("\u00a77Punishment: \u00a7cMute24Ban30")) {
            player.sendMessage("\u00a77User has been bannedd for 30 days! \n\u00a7c\u00a7lReason: \u00a7c" + ban_message + "\n\u00a7cBAN FUNCTION NOT IMPLEMENTED!");
        } else if (event.getView().getItem(4).getItemMeta().getLore().get(1).equals("\u00a77Punishment: \u00a7cBanPerm")) {
            player.sendMessage("\u00a77User has been banned! \n\u00a7c\u00a7lReason: \u00a7c" + ban_message + "\n\u00a7cMUTE FUNCTION NOT IMPLEMENTED!");
        } else {
            player.sendMessage(ban_message + " ERROR " + event.getView().getItem(4).getItemMeta().getLore().get(1));
        }
        return;
        }
    }
