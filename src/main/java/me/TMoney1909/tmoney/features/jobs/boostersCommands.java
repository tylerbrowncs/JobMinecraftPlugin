package me.TMoney1909.tmoney.features.jobs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.TMoney1909.tmoney.Tmoney.*;

public class boostersCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (command.getName().equalsIgnoreCase("setbooster")) {
                setBooster(Integer.parseInt(strings[0]), Long.parseLong(strings[1]));
                return true;

            } else if (command.getName().equalsIgnoreCase("booster")) {
                float booster = getBooster();
                float timeLeft = getTimeLeft();

                if (booster == 1.0) {
                    player.sendMessage("No active boosters.");
                    return true;
                }

                player.sendMessage("Current Booster: x" + booster + " for " + Math.floor(timeLeft) + " seconds.");
                return true;
            }
        }

        return false;
    }
}
