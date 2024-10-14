package me.TMoney1909.tmoney.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class feed implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (command.getName().equalsIgnoreCase("feed")) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                player.setFoodLevel(20);
                player.setSaturation(20);
                player.sendMessage("You are no longer hungry!");
            }
        }

        return true;
    }
}
