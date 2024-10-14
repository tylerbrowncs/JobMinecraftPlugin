package me.TMoney1909.tmoney.commands;

import me.TMoney1909.tmoney.menus.ManageMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ManageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "You are not able to preform this command.");
            return true;
        }

        Player player = (Player) commandSender;

        if (strings.length != 1) {
            player.sendMessage(ChatColor.RED + "Usage: /manage <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(strings[0]);

        if (target == null) {
            player.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        new ManageMenu(player, target);


        return true;
    }
}
