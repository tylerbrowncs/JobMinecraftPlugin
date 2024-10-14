package me.TMoney1909.tmoney.features.jobs;

import me.TMoney1909.tmoney.Tmoney;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class JobMenu {

    public JobMenu(Player player) {

        PersistentDataContainer container = player.getPersistentDataContainer();
        List<String> playertags = List.of(container.get(new NamespacedKey(Tmoney.getPlugin(), "tags"), PersistentDataType.STRING).split(","));


        Inventory inventory = Bukkit.createInventory(null, 27, "Jobs");

        // MINING JOB
        ItemStack miner = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta minerMeta = miner.getItemMeta();
        minerMeta.setDisplayName("\u00a7bMiner");

        List<String> loresList = new ArrayList<String>();
        loresList.add("\u00a77Get experience and cash by mining.");
        loresList.add("\n");

        if (container.get(new NamespacedKey(Tmoney.getPlugin(), "isMiner"), PersistentDataType.BOOLEAN)) {
            // Display Level
            float minerexp = container.get(new NamespacedKey(Tmoney.getPlugin(), "minerexp"), PersistentDataType.FLOAT);
            int level = (int) Math.floor((0.4 * Math.sqrt(minerexp)) + 1);
            int nextlevelexp = (int) Math.pow((level) / 0.4, 2);
            loresList.add("\u00a77Level: \u00a7b" + level);
            loresList.add("\u00a77Exp: \u00a7b" + minerexp + " / " + nextlevelexp);
            loresList.add("\n");
            loresList.add("\u00a7a\u00a7lALREADY JOINED");
            loresList.add("\u00a7cClick to Leave");
            minerMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
            minerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        } else {
            loresList.add("\u00a7aClick to Join");
        }

        minerMeta.setLore(loresList);

        miner.setItemMeta(minerMeta);
        inventory.setItem(10, miner);


        // Lumberjack
        ItemStack lumber = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta lumberMeta = lumber.getItemMeta();
        lumberMeta.setDisplayName("\u00a7bLumberjack");

        loresList = new ArrayList<String>();
        loresList.add("\u00a77Get experience and cash by chopping and felling trees.");
        loresList.add("\n");

        if (container.get(new NamespacedKey(Tmoney.getPlugin(), "isLumber"), PersistentDataType.BOOLEAN)) {
            // Display Level

            float lumberexp = container.get(new NamespacedKey(Tmoney.getPlugin(), "lumberexp"), PersistentDataType.FLOAT);
            int level = (int) Math.floor((0.4 * Math.sqrt(lumberexp)) + 1);
            int nextlevelexp = (int) Math.pow((level) / 0.4, 2);
            loresList.add("\u00a77Level: \u00a7b" + level);
            loresList.add("\u00a77Exp: \u00a7b" + lumberexp + " / " + nextlevelexp);
            loresList.add("\n");
            loresList.add("\u00a7a\u00a7lALREADY JOINED");
            loresList.add("\u00a7cClick to Leave");
            lumberMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
            lumberMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        } else {
            loresList.add("\u00a7aClick to Join");
        }
        loresList.add("\u00a7c\u00a7lUNDER CONSTRUCTION");
        lumberMeta.setLore(loresList);

        lumber.setItemMeta(lumberMeta);
        inventory.setItem(11, lumber);

        // Builder
        ItemStack builder = new ItemStack(Material.STONE_BRICKS);
        ItemMeta builderMeta = builder.getItemMeta();
        builderMeta.setDisplayName("\u00a7bBuilder");

        loresList = new ArrayList<String>();
        loresList.add("\u00a77Get experience and cash by placing blocks.");
        loresList.add("\n");

        if (container.get(new NamespacedKey(Tmoney.getPlugin(), "isBuilder"), PersistentDataType.BOOLEAN)) {
            // Display Level

            loresList.add("\u00a7a\u00a7lALREADY JOINED");
            loresList.add("\u00a7cClick to Leave");
            builderMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
            builderMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        } else {
            loresList.add("\u00a7aClick to Join");
        }
        loresList.add("\u00a7c\u00a7lUNDER CONSTRUCTION");
        builderMeta.setLore(loresList);

        builder.setItemMeta(builderMeta);
        inventory.setItem(12, builder);

        // Farmer
        ItemStack farmer = new ItemStack(Material.WHEAT);
        ItemMeta farmerMeta = farmer.getItemMeta();
        farmerMeta.setDisplayName("\u00a7bFarmer");

        loresList = new ArrayList<String>();
        loresList.add("\u00a77Get experience and cash by planting and harvesting crops and raising animals");
        loresList.add("\n");

        if (container.get(new NamespacedKey(Tmoney.getPlugin(), "isFarmer"), PersistentDataType.BOOLEAN)) {
            // Display Level

            loresList.add("\u00a7a\u00a7lALREADY JOINED");
            loresList.add("\u00a7cClick to Leave");
            farmerMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
            farmerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        } else {
            loresList.add("\u00a7aClick to Join");
        }
        loresList.add("\u00a7c\u00a7lUNDER CONSTRUCTION");
        farmerMeta.setLore(loresList);

        farmer.setItemMeta(farmerMeta);
        inventory.setItem(13, farmer);

        // Hunter
        ItemStack hunter = new ItemStack(Material.CROSSBOW);
        ItemMeta hunterMeta = hunter.getItemMeta();
        hunterMeta.setDisplayName("\u00a7bHunter");

        loresList = new ArrayList<String>();
        loresList.add("\u00a77Get experience and cash by killing monsters.");
        loresList.add("\n");

        if (container.get(new NamespacedKey(Tmoney.getPlugin(), "isHunter"), PersistentDataType.BOOLEAN)) {
            // Display Level

            loresList.add("\u00a7a\u00a7lALREADY JOINED");
            loresList.add("\u00a7cClick to Leave");
            hunterMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
            hunterMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        } else {
            loresList.add("\u00a7aClick to Join");
        }
        loresList.add("\u00a7c\u00a7lUNDER CONSTRUCTION");
        hunterMeta.setLore(loresList);

        hunter.setItemMeta(hunterMeta);
        inventory.setItem(14, hunter);

        // Explorer
        ItemStack explorer = new ItemStack(Material.COMPASS);
        ItemMeta explorerMeta = explorer.getItemMeta();
        explorerMeta.setDisplayName("\u00a7bExplorer");

        loresList = new ArrayList<String>();
        loresList.add("\u00a77Get experience and cash by exploring new lands and structures.");
        loresList.add("\n");

        if (container.get(new NamespacedKey(Tmoney.getPlugin(), "isExplorer"), PersistentDataType.BOOLEAN)) {
            // Display Level

            loresList.add("\u00a7a\u00a7lALREADY JOINED");
            loresList.add("\u00a7cClick to Leave");
            explorerMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
            explorerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        } else {
            loresList.add("\u00a7aClick to Join");
        }
        loresList.add("\u00a7c\u00a7lUNDER CONSTRUCTION");
        explorerMeta.setLore(loresList);

        explorer.setItemMeta(explorerMeta);
        inventory.setItem(15, explorer);

        // Fisher
        ItemStack fisher = new ItemStack(Material.FISHING_ROD);
        ItemMeta fisherMeta = fisher.getItemMeta();
        fisherMeta.setDisplayName("\u00a7bFisher");

        loresList = new ArrayList<String>();
        loresList.add("\u00a77Get experience and cash by fishing.");
        loresList.add("\n");

        if (container.get(new NamespacedKey(Tmoney.getPlugin(), "isFisher"), PersistentDataType.BOOLEAN)) {
            // Display Level

            loresList.add("\u00a7a\u00a7lALREADY JOINED");
            loresList.add("\u00a7cClick to Leave");

            fisherMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
            fisherMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        } else {
            loresList.add("\u00a7aClick to Join");
        }
        loresList.add("\u00a7c\u00a7lUNDER CONSTRUCTION");
        fisherMeta.setLore(loresList);

        fisher.setItemMeta(fisherMeta);
        inventory.setItem(16, fisher);


        player.openInventory(inventory);

    }

}
