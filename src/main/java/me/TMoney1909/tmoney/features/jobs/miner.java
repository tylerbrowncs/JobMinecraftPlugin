package me.TMoney1909.tmoney.features.jobs;


import me.TMoney1909.tmoney.Tmoney;
import net.kyori.adventure.text.ComponentLike;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.*;
import org.bukkit.Color;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.codehaus.plexus.util.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.naming.Name;
import java.awt.*;
import java.util.Locale;

import static java.lang.Integer.parseInt;
import static me.TMoney1909.tmoney.Tmoney.getPlugin;
import static me.TMoney1909.tmoney.Tmoney.plugin;

public class miner implements Listener, CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (command.getName().equalsIgnoreCase("miner")) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                PersistentDataContainer container = player.getPersistentDataContainer();

                //1.25√y + 1
                float minerexp = container.get(new NamespacedKey(Tmoney.getPlugin(), "minerexp"), PersistentDataType.FLOAT);

                int level = (int) Math.floor((0.4*Math.sqrt(minerexp)) + 1);

                int nextlevelexp = (int) Math.pow((level) / 0.4, 2);

                if (level < 100) {
                    player.sendMessage("\u00a7bMiner Statistics!\n\u00a77Level:  \u00a7b" + level + "\n\u00a77Progress: \u00a7b" + minerexp + " / " + nextlevelexp);
                } else {
                    player.sendMessage("\u00a7bMiner Statistics!\n\u00a77Level:  \u00a7b" + level + " (Max)");
                }


                return true;

            }
        } else if (command.getName().equalsIgnoreCase("setmining")) {
            if (commandSender instanceof Player) {
                if (strings.length == 2) {
                    Player target = Bukkit.getPlayer(strings[0]);
                    PersistentDataContainer container = target.getPersistentDataContainer();
                    int levelwant = Integer.parseInt(strings[1]);

                    float expwant = (float) Math.pow((levelwant - 1) / 0.4, 2) + 1;

                    container.set(new NamespacedKey(getPlugin(), "minerexp"), PersistentDataType.FLOAT, expwant);
                    commandSender.sendMessage("Successfully adjusted players level.");
                    return true;

                }
            }
        }
        return false;
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onBreakBlock(BlockBreakEvent event) {

        Player player = (Player) event.getPlayer();
        PersistentDataContainer data = player.getPersistentDataContainer();

        PersistentDataContainer container = player.getPersistentDataContainer();
        Block block = event.getBlock();

        if (container.get(new NamespacedKey(Tmoney.getPlugin(), "isMiner"), PersistentDataType.BOOLEAN)) {

            if (plugin.getConfig().contains("jobs.miner." + event.getBlock().getType().name().toLowerCase())) {

                if (!Tmoney.blockLocationStorage.containsLocation(new Location(block.getWorld(), block.getX(), block.getY(), block.getZ()))) {

                    // HERE GOES GETTING THE AMOUNT OF EXP AND MONEY
                    float moneyBase = 0;
                    float expBase = 0;

                    if (plugin.getConfig().contains("jobs.miner." + event.getBlock().getType().name().toLowerCase() + ".money")) {
                        moneyBase = Float.parseFloat(plugin.getConfig().getString("jobs.miner." + event.getBlock().getType().name().toLowerCase() + ".money"));
                    }

                    if (plugin.getConfig().contains("jobs.miner." + event.getBlock().getType().name().toLowerCase() + ".exp")) {
                        expBase = Float.parseFloat(plugin.getConfig().getString("jobs.miner." + event.getBlock().getType().name().toLowerCase() + ".exp"));
                    }

                    float minerexp = container.get(new NamespacedKey(Tmoney.getPlugin(), "minerexp"), PersistentDataType.FLOAT);
                    float money = container.get(new NamespacedKey(Tmoney.getPlugin(), "balance"), PersistentDataType.FLOAT);
                    int level = (int) Math.floor((0.4 * Math.sqrt(minerexp)) + 1);

                    if (!((moneyBase == 0) && (expBase == 0))) {
                        float baseValue = 1;
                        float minerexpdelta;


                        if (level < 100) {
                            minerexpdelta = (float) (expBase * Tmoney.getBooster() * (1 + ((level - 1) / 100.0)));

                            minerexpdelta = (float) Math.round(minerexpdelta * 100) /100;
                            minerexp += (minerexpdelta);
                            container.set(new NamespacedKey(Tmoney.getPlugin(), "minerexp"), PersistentDataType.FLOAT, minerexp);
                        } else {
                            minerexpdelta = 0;
                        }
                        float moneydelta = (float) (moneyBase * Tmoney.getBooster() * (1 + ((level - 1) / 100.0)));
                        moneydelta = (float) Math.round(moneydelta*100) /100;
                        money += moneydelta;
                        container.set(new NamespacedKey(Tmoney.getPlugin(), "balance"), PersistentDataType.FLOAT, money);

                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("\u00a77" + StringUtils.capitalise(event.getBlock().getType().name().toLowerCase().replace("_", " ")) + "    \u00a7d+" + minerexpdelta + " exp.   \u00a7b+₹" + moneydelta));

                        int levelafter = (int) Math.floor((0.4 * Math.sqrt(minerexp)) + 1);


                        if (!(levelafter == level)) {
                            player.sendMessage("You have just leveled up in mining (Level " + levelafter + ")");

                            Sound sound = Sound.ENTITY_PLAYER_LEVELUP;
                            Location location = player.getLocation();

                            player.playSound(location, sound, 1.0F, 1.0F);

                        }
                        boolean hasBar = data.get(new NamespacedKey(Tmoney.getPlugin(), "hasminingbar"), PersistentDataType.BOOLEAN);


                        if (!hasBar) {
                            BossBar playerBossBar = Bukkit.createBossBar("Miner: Level " + levelafter, BarColor.RED, BarStyle.SOLID);
                            playerBossBar.addPlayer(player);

                            double percentage = ((0.4 * Math.sqrt(minerexp)) + 1) - Math.floor((0.4 * Math.sqrt(minerexp)) + 1);
                            playerBossBar.setProgress(percentage);

                            data.set(new NamespacedKey(Tmoney.getPlugin(), "hasminingbar"), PersistentDataType.BOOLEAN, true);

                            Bukkit.getScheduler().runTaskLater(Tmoney.getPlugin(), () -> playerBossBar.removePlayer(player), 20 * 5);
                            Bukkit.getScheduler().runTaskLater(Tmoney.getPlugin(), () -> data.set(new NamespacedKey(Tmoney.getPlugin(), "hasminingbar"), PersistentDataType.BOOLEAN, false), 99);
                        }

                    }
                }
            }
        }

        //56






    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        PersistentDataContainer container = player.getPersistentDataContainer();

        if (!container.has(new NamespacedKey(Tmoney.getPlugin(), "minerexp"), PersistentDataType.FLOAT)) {
            float set = 0;
            container.set(new NamespacedKey(Tmoney.getPlugin(), "minerexp"), PersistentDataType.FLOAT ,set);

        }
        container.set(new NamespacedKey(Tmoney.getPlugin(), "hasminingbar"), PersistentDataType.BOOLEAN ,false);

    }
}
