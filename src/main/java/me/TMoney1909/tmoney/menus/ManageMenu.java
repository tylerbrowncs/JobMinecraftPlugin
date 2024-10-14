package me.TMoney1909.tmoney.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;


public class ManageMenu {
    public ManageMenu(Player player, Player target) {


        Inventory inventory = Bukkit.createInventory(null, 54, "Manage Player");

        ItemStack targetHead = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta targetHeadMeta = (SkullMeta) targetHead.getItemMeta();
        targetHeadMeta.setDisplayName(target.getName());
        List<String> loresList = new ArrayList<String>();
        loresList.add("\n");
        loresList.add("\u00a77Health: \u00a7c" + target.getHealth());
        loresList.add("\u00a77Ping: \u00a7f" + target.getPing());
        targetHeadMeta.setLore(loresList);
        targetHeadMeta.setOwningPlayer(Bukkit.getOfflinePlayer(target.getName()));
        targetHead.setItemMeta(targetHeadMeta);

        inventory.setItem(13, targetHead);

        ItemStack teleport = new ItemStack(Material.ENDER_PEARL, 1);
        ItemMeta teleportMeta = teleport.getItemMeta();
        teleportMeta.setDisplayName("\u00a7bTeleport to player");
        loresList = new ArrayList<String>();
        loresList.add("\n");
        loresList.add("\u00a77Click to teleport to \u00a7c" + target.getName());
        teleportMeta.setLore(loresList);
        teleport.setItemMeta(teleportMeta);

        inventory.setItem(20, teleport);

        // Punishments: Non-bans

        ItemStack kick = new ItemStack(Material.BARRIER, 1);
        ItemMeta kickMeta = kick.getItemMeta();
        kickMeta.setDisplayName("\u00a7cKick Player");
        loresList = new ArrayList<String>();
        loresList.add("\n");
        loresList.add("\u00a77Click to \u00a7lkick to \u00a7c" + target.getName()+ ".");
        kickMeta.setLore(loresList);
        kick.setItemMeta(kickMeta);

        inventory.setItem(21, kick);

        ItemStack mute1 = new ItemStack(Material.BOOK, 1);
        ItemMeta muteMeta = mute1.getItemMeta();
        muteMeta.setDisplayName("\u00a7cMute Player (30m)");
        loresList = new ArrayList<String>();
        loresList.add("\n");
        loresList.add("\u00a77Click to \u00a7lmute \u00a7c" + target.getName() + " \u00a77for 30 minutes.");
        muteMeta.setLore(loresList);
        mute1.setItemMeta(muteMeta);

        inventory.setItem(22, mute1);

        ItemStack mute2 = new ItemStack(Material.WRITABLE_BOOK, 1);
        ItemMeta mute2Meta = mute2.getItemMeta();
        mute2Meta.setDisplayName("\u00a7cMute Player (3hr)");
        loresList = new ArrayList<String>();
        loresList.add("\n");
        loresList.add("\u00a77Click to \u00a7lmute \u00a7c" + target.getName() + " \u00a77for 3 hours.");
        mute2Meta.setLore(loresList);
        mute2.setItemMeta(mute2Meta);

        inventory.setItem(23, mute2);

        ItemStack mute3 = new ItemStack(Material.WRITTEN_BOOK, 1);
        ItemMeta mute3Meta = mute3.getItemMeta();
        mute3Meta.setDisplayName("\u00a7cMute Player (1d)");
        loresList = new ArrayList<String>();
        loresList.add("\n");
        loresList.add("\u00a77Click to \u00a7lmute \u00a7c" + target.getName() + " \u00a77for 1 day.");
        mute3Meta.setLore(loresList);
        mute3.setItemMeta(mute3Meta);

        inventory.setItem(24, mute3);

        // Player bans!
        ItemStack ban1 = new ItemStack(Material.WOODEN_AXE, 1);
        ItemMeta ban1Meta = ban1.getItemMeta();
        ban1Meta.setDisplayName("\u00a7cBan (1d)");
        loresList = new ArrayList<String>();
        loresList.add("\n");
        loresList.add("\u00a77Click to \u00a7lban \u00a7c" + target.getName() + " \u00a77for 1 day.");
        ban1Meta.setLore(loresList);
        ban1.setItemMeta(ban1Meta);

        inventory.setItem(29, ban1);


        ItemStack ban2 = new ItemStack(Material.IRON_AXE, 1);
        ItemMeta ban2Meta = ban2.getItemMeta();
        ban2Meta.setDisplayName("\u00a7cBan (3d)");
        loresList = new ArrayList<String>();
        loresList.add("\n");
        loresList.add("\u00a77Click to \u00a7lban \u00a7c" + target.getName() + " \u00a77for 3 days.");
        ban2Meta.setLore(loresList);
        ban2.setItemMeta(ban2Meta);

        inventory.setItem(30, ban2);


        ItemStack ban3 = new ItemStack(Material.GOLDEN_AXE, 1);
        ItemMeta ban3Meta = ban3.getItemMeta();
        ban3Meta.setDisplayName("\u00a7cBan (7d)");
        loresList = new ArrayList<String>();
        loresList.add("\n");
        loresList.add("\u00a77Click to \u00a7lban \u00a7c" + target.getName() + " \u00a77for 7 days.");
        ban3Meta.setLore(loresList);
        ban3.setItemMeta(ban3Meta);

        inventory.setItem(31, ban3);


        ItemStack ban4 = new ItemStack(Material.DIAMOND_AXE, 1);
        ItemMeta ban4Meta = ban4.getItemMeta();
        ban4Meta.setDisplayName("\u00a7cBan (30d)");
        loresList = new ArrayList<String>();
        loresList.add("\n");
        loresList.add("\u00a77Click to \u00a7lban \u00a7c" + target.getName() + " \u00a77for 30 days.");
        ban4Meta.setLore(loresList);
        ban4.setItemMeta(ban4Meta);

        inventory.setItem(32, ban4);


        ItemStack ban5 = new ItemStack(Material.NETHERITE_AXE, 1);
        ItemMeta ban5Meta = ban5.getItemMeta();
        ban5Meta.setDisplayName("\u00a7cBan (Perm)");
        ban5Meta.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
        ban5Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        loresList = new ArrayList<String>();
        loresList.add("\n");
        loresList.add("\u00a77Click to \u00a7lban \u00a7c" + target.getName() + " \u00a77permanently.");
        ban5Meta.setLore(loresList);
        ban5.setItemMeta(ban5Meta);

        inventory.setItem(33, ban5);

        ItemStack blank = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta blankMeta = blank.getItemMeta();
        blankMeta.setDisplayName(" ");
        blank.setItemMeta(blankMeta);


        inventory.setItem(10, blank);
        inventory.setItem(11, blank);
        inventory.setItem(12, blank);
        inventory.setItem(14, blank);
        inventory.setItem(15, blank);
        inventory.setItem(16, blank);
        inventory.setItem(19, blank);
        inventory.setItem(25, blank);
        inventory.setItem(28, blank);
        inventory.setItem(34, blank);
        inventory.setItem(37, blank);
        inventory.setItem(38, blank);
        inventory.setItem(39, blank);
        inventory.setItem(40, blank);
        inventory.setItem(41, blank);
        inventory.setItem(42, blank);
        inventory.setItem(43, blank);





        player.openInventory(inventory);


    }
}