package me.TMoney1909.tmoney.features.jobs;

import me.TMoney1909.tmoney.Tmoney;
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
import org.jetbrains.annotations.NotNull;

public class JobsCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player) {
            if (command.getName().equalsIgnoreCase("jobs")) {
                Player player = (Player) commandSender;

                new JobMenu(player);

                return true;

            }
        }
        return false;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        PersistentDataContainer container = player.getPersistentDataContainer();



        if (!container.has(new NamespacedKey(Tmoney.getPlugin(), "isMiner"), PersistentDataType.BOOLEAN)) {
            container.set(new NamespacedKey(Tmoney.getPlugin(), "isMiner"), PersistentDataType.BOOLEAN, false);
        }
        if (!container.has(new NamespacedKey(Tmoney.getPlugin(), "isFisher"), PersistentDataType.BOOLEAN)) {
            container.set(new NamespacedKey(Tmoney.getPlugin(), "isFisher"), PersistentDataType.BOOLEAN, false);
        }
        if (!container.has(new NamespacedKey(Tmoney.getPlugin(), "isExplorer"), PersistentDataType.BOOLEAN)) {
            container.set(new NamespacedKey(Tmoney.getPlugin(), "isExplorer"), PersistentDataType.BOOLEAN, false);
        }
        if (!container.has(new NamespacedKey(Tmoney.getPlugin(), "isHunter"), PersistentDataType.BOOLEAN)) {
            container.set(new NamespacedKey(Tmoney.getPlugin(), "isHunter"), PersistentDataType.BOOLEAN, false);
        }
        if (!container.has(new NamespacedKey(Tmoney.getPlugin(), "isFarmer"), PersistentDataType.BOOLEAN)) {
            container.set(new NamespacedKey(Tmoney.getPlugin(), "isFarmer"), PersistentDataType.BOOLEAN, false);
        }
        if (!container.has(new NamespacedKey(Tmoney.getPlugin(), "isBuilder"), PersistentDataType.BOOLEAN)) {
            container.set(new NamespacedKey(Tmoney.getPlugin(), "isBuilder"), PersistentDataType.BOOLEAN, false);
        }
        if (!container.has(new NamespacedKey(Tmoney.getPlugin(), "isLumber"), PersistentDataType.BOOLEAN)) {
            container.set(new NamespacedKey(Tmoney.getPlugin(), "isLumber"), PersistentDataType.BOOLEAN, false);
        }


    }


}
