package me.TMoney1909.tmoney.features;


import me.TMoney1909.tmoney.Tmoney;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import static java.lang.Integer.parseInt;
import static me.TMoney1909.tmoney.Tmoney.plugin;

public class economy implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (command.getName().equalsIgnoreCase("bal")) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                PersistentDataContainer data = p.getPersistentDataContainer();

                data.get(new NamespacedKey(Tmoney.getPlugin(), "balance"), PersistentDataType.FLOAT);
                commandSender.sendMessage( "\u00a77Balance: \u00a7c₹" + Math.floor(data.get(new NamespacedKey(Tmoney.getPlugin(), "balance"), PersistentDataType.FLOAT)*100)/100);
                return true;
            }
            return false;
        }

        if (command.getName().equalsIgnoreCase("addmoney")) {
            if (strings.length == 2) {
                Player target = Bukkit.getPlayer(strings[0]);

                if (target == null) {
                    target = Bukkit.getOfflinePlayer(strings[0]).getPlayer();

                    if (target == null) {
                        commandSender.sendMessage("\u00a7cError: Player not found.");
                        return true;
                    }
                }


                PersistentDataContainer data = target.getPersistentDataContainer();

                float balance = data.get(new NamespacedKey(Tmoney.getPlugin(), "balance"), PersistentDataType.FLOAT);
                balance += Float.valueOf(strings[1]);

                data.set(new NamespacedKey(Tmoney.getPlugin(), "balance"), PersistentDataType.FLOAT, balance);

                commandSender.sendMessage("Balance adjusted.");
                return true;
            } else {
                commandSender.sendMessage("\u00a7cUsage: /addmoney <player> <amount>");
                return true;
            }
        }

        return false;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        PersistentDataContainer data = p.getPersistentDataContainer();

        if (!p.getPersistentDataContainer().has(new NamespacedKey(Tmoney.getPlugin(), "balance"), PersistentDataType.FLOAT)) {
            float defaultCash = 500;
            p.getPersistentDataContainer().set(new NamespacedKey(Tmoney.getPlugin(), "balance"), PersistentDataType.FLOAT ,defaultCash);
        }


    }


}
