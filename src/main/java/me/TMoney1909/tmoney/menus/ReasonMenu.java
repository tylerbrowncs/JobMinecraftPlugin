package me.TMoney1909.tmoney.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class ReasonMenu {

    public ReasonMenu(Player player, Player target, String type) {

        Inventory inventory = Bukkit.createInventory(null, 36, "Reason?");

        ItemStack targetHead = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta targetHeadMeta = (SkullMeta) targetHead.getItemMeta();
        targetHeadMeta.setDisplayName(target.getName());
        List<String> loresList = new ArrayList<String>();
        loresList.add("\n");
        loresList.add("\u00a77Punishment: \u00a7c" + type);
        targetHeadMeta.setLore(loresList);
        targetHeadMeta.setOwningPlayer(Bukkit.getOfflinePlayer(target.getName()));
        targetHead.setItemMeta(targetHeadMeta);

        inventory.setItem(4, targetHead);

        ItemStack hack = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta hackMeta = hack.getItemMeta();
        hackMeta.setDisplayName("\u00a7bHacked Client");
        loresList = new ArrayList<String>();
        loresList.add("\n");
        loresList.add("\u00a77The use of a hacked client!");
        hackMeta.setLore(loresList);
        hack.setItemMeta(hackMeta);

        inventory.setItem(10, hack);

        ItemStack xray = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        ItemMeta xrayMeta = xray.getItemMeta();
        xrayMeta.setDisplayName("\u00a7bX-Ray");
        loresList = new ArrayList<String>();
        loresList.add("\n\u00a77");
        loresList.add("The use of X-Ray.");
        xrayMeta.setLore(loresList);
        xray.setItemMeta(xrayMeta);

        inventory.setItem(11, xray);

        ItemStack exploit = new ItemStack(Material.COMMAND_BLOCK_MINECART, 1);
        ItemMeta exploitMeta = exploit.getItemMeta();
        exploitMeta.setDisplayName("\u00a7bExploits");
        loresList = new ArrayList<String>();
        loresList.add("\n\u00a77");
        loresList.add("Excessive exploitation");
        exploitMeta.setLore(loresList);
        exploit.setItemMeta(exploitMeta);

        inventory.setItem(12, exploit);

        player.openInventory(inventory);

    }
}
