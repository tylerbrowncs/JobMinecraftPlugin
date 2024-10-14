package me.TMoney1909.tmoney.features.jobs;

import me.TMoney1909.tmoney.Tmoney;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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

import static me.TMoney1909.tmoney.Tmoney.plugin;

public class lumberjack implements Listener, CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (command.getName().equalsIgnoreCase("lumberjack")) {
            if (commandSender instanceof Player player) {


                PersistentDataContainer container = player.getPersistentDataContainer();

                float lumberexp = container.get(new NamespacedKey(Tmoney.getPlugin(), "lumberexp"), PersistentDataType.FLOAT);

                int level = (int) Math.floor((0.4 * Math.sqrt(lumberexp)) + 1);

                int nextlevelexp = (int) Math.pow((level) / 4, 2);

                if (level < 100) {
                    player.sendMessage("\u00a7bLumberjack Statistics!\n\u00a77Level:  \u00a7b" + level + "\n\u00a77Progress: \u00a7b" + lumberexp + " / " + nextlevelexp);
                } else {
                    player.sendMessage("\u00a7bLumberjack Statistics!\n\u00a77Level:  \u00a7b" + level + " (Max)");
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

        if (container.get(new NamespacedKey(Tmoney.getPlugin(), "isLumber"), PersistentDataType.BOOLEAN)) {

            if (plugin.getConfig().contains("jobs.lumberjack." + event.getBlock().getType().name().toLowerCase())) {

                if (!Tmoney.blockLocationStorage.containsLocation(new Location(block.getWorld(), block.getX(), block.getY(), block.getZ()))) {

                    // HERE GOES GETTING THE AMOUNT OF EXP AND MONEY
                    float moneyBase = 0;
                    float expBase = 0;

                    if (plugin.getConfig().contains("jobs.lumberjack." + event.getBlock().getType().name().toLowerCase() + ".money")) {
                        moneyBase = Float.parseFloat(plugin.getConfig().getString("jobs.lumberjack." + event.getBlock().getType().name().toLowerCase() + ".money"));
                    }

                    if (plugin.getConfig().contains("jobs.lumberjack." + event.getBlock().getType().name().toLowerCase() + ".exp")) {
                        expBase = Float.parseFloat(plugin.getConfig().getString("jobs.lumberjack." + event.getBlock().getType().name().toLowerCase() + ".exp"));
                    }

                    float lumberexp = container.get(new NamespacedKey(Tmoney.getPlugin(), "lumberexp"), PersistentDataType.FLOAT);
                    float money = container.get(new NamespacedKey(Tmoney.getPlugin(), "balance"), PersistentDataType.FLOAT);
                    int level = (int) Math.floor((0.4 * Math.sqrt(lumberexp)) + 1);

                    if (!((moneyBase == 0) && (expBase == 0))) {
                        float baseValue = 1;
                        float lumberexpdelta;


                        if (level < 100) {
                            lumberexpdelta = (float) (expBase * Tmoney.getBooster() * (1 + ((level - 1) / 100.0)));

                            lumberexpdelta = (float) Math.round(lumberexpdelta * 100) /100;
                            lumberexp += (lumberexpdelta);
                            container.set(new NamespacedKey(Tmoney.getPlugin(), "lumberexp"), PersistentDataType.FLOAT, lumberexp);
                        } else {
                            lumberexpdelta = 0;
                        }
                        float moneydelta = (float) (moneyBase * Tmoney.getBooster() * (1 + ((level - 1) / 100.0)));
                        moneydelta = (float) Math.round(moneydelta*100) /100;
                        money += moneydelta;
                        container.set(new NamespacedKey(Tmoney.getPlugin(), "balance"), PersistentDataType.FLOAT, money);

                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("\u00a77" + StringUtils.capitalise(event.getBlock().getType().name().toLowerCase().replace("_", " ")) + "    \u00a7d+" + lumberexpdelta + " exp.   \u00a7b+₹" + moneydelta));

                        int levelafter = (int) Math.floor((0.4 * Math.sqrt(lumberexp)) + 1);


                        if (!(levelafter == level)) {
                            player.sendMessage("You have just leveled up in woodcutting (Level " + levelafter + ")");

                            Sound sound = Sound.ENTITY_PLAYER_LEVELUP;
                            Location location = player.getLocation();

                            player.playSound(location, sound, 1.0F, 1.0F);

                        }
                        boolean hasBar = data.get(new NamespacedKey(Tmoney.getPlugin(), "haslumberbar"), PersistentDataType.BOOLEAN);


                        if (!hasBar) {
                            BossBar playerBossBar = Bukkit.createBossBar("Lumberjack: Level " + levelafter, BarColor.GREEN, BarStyle.SOLID);
                            playerBossBar.addPlayer(player);

                            double percentage = ((0.4 * Math.sqrt(lumberexp)) + 1) - Math.floor((0.4 * Math.sqrt(lumberexp)) + 1);
                            playerBossBar.setProgress(percentage);

                            data.set(new NamespacedKey(Tmoney.getPlugin(), "haslumberbar"), PersistentDataType.BOOLEAN, true);

                            Bukkit.getScheduler().runTaskLater(Tmoney.getPlugin(), () -> playerBossBar.removePlayer(player), 20 * 5);
                            Bukkit.getScheduler().runTaskLater(Tmoney.getPlugin(), () -> data.set(new NamespacedKey(Tmoney.getPlugin(), "haslumberbar"), PersistentDataType.BOOLEAN, false), 99);
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

        if (!container.has(new NamespacedKey(Tmoney.getPlugin(), "lumberexp"), PersistentDataType.FLOAT)) {
            float set = 0;
            container.set(new NamespacedKey(Tmoney.getPlugin(), "lumberexp"), PersistentDataType.FLOAT, set);

        }
        container.set(new NamespacedKey(Tmoney.getPlugin(), "haslumberbar"), PersistentDataType.BOOLEAN, false);

    }
}
