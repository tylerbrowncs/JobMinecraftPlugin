package me.TMoney1909.tmoney;

import me.TMoney1909.tmoney.commands.ManageCommand;
import me.TMoney1909.tmoney.commands.feed;
import me.TMoney1909.tmoney.features.BlockLocationStorage;
import me.TMoney1909.tmoney.features.economy;
import me.TMoney1909.tmoney.features.jobs.JobMenuEvents;
import me.TMoney1909.tmoney.features.jobs.JobsCommand;
import me.TMoney1909.tmoney.features.jobs.lumberjack;
import me.TMoney1909.tmoney.features.jobs.miner;
import me.TMoney1909.tmoney.menus.ManageMenuEvents;
import me.TMoney1909.tmoney.menus.ReasonMenuEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public final class Tmoney extends JavaPlugin {

    public static Tmoney plugin;
    public static float booster = 1.00F;
    public static float eventbooster = 1.00F;
    public static BlockLocationStorage blockLocationStorage;
    private static FileConfiguration config;

    @Override
    public void onEnable() {
        // Global Variables
        plugin = this;

        blockLocationStorage = new BlockLocationStorage("plugins/tmoney/playerPlacedBlocks.yml");
        this.saveDefaultConfig();


        // Plugin startup logic
        eventbooster = Float.parseFloat(this.getConfig().getString("event-booster"));

        Bukkit.getPluginManager().registerEvents(new events(), this);
        Bukkit.getPluginManager().registerEvents(new ManageMenuEvents(), this);
        Bukkit.getPluginManager().registerEvents(new ReasonMenuEvent(), this);
        Bukkit.getPluginManager().registerEvents(new economy(), this);
        Bukkit.getPluginManager().registerEvents(new miner(), this);
        Bukkit.getPluginManager().registerEvents(new JobsCommand(), this);
        Bukkit.getPluginManager().registerEvents(new JobMenuEvents(), this);
        Bukkit.getPluginManager().registerEvents(new lumberjack(), this);

        getCommand("jobs").setExecutor(new JobsCommand());
        getCommand("feed").setExecutor(new feed());
        getCommand("bal").setExecutor(new economy());
        getCommand("addmoney").setExecutor(new economy());
        getCommand("manage").setExecutor(new ManageCommand());
        getCommand("miner").setExecutor(new miner());
        getCommand("setmining").setExecutor(new miner());
        getCommand("lumberjack").setExecutor(new lumberjack());
        getCommand("setlumberjack").setExecutor(new lumberjack());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Tmoney getPlugin() {
        return plugin;
    }

    public static float getBooster() {
        return booster * eventbooster;
    }

}
