package me.TMoney1909.tmoney.features.jobs;

import me.TMoney1909.tmoney.Tmoney;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class JobMenuEvents implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equals("Jobs")) {


            float resetValue = 0;


            if (event.getCurrentItem().getType().equals(Material.DIAMOND_PICKAXE)) {
                PersistentDataContainer container = player.getPersistentDataContainer();




                boolean isMiner = container.get(new NamespacedKey(Tmoney.getPlugin(), "isMiner"), PersistentDataType.BOOLEAN);
                container.set(new NamespacedKey(Tmoney.getPlugin(), "isMiner"), PersistentDataType.BOOLEAN, !(isMiner));


                container.set(new NamespacedKey(Tmoney.getPlugin(), "minerexp"), PersistentDataType.FLOAT, resetValue);



            } else  if (event.getCurrentItem().getType().equals(Material.DIAMOND_AXE)) {
                PersistentDataContainer container = player.getPersistentDataContainer();




                boolean isLumber = container.get(new NamespacedKey(Tmoney.getPlugin(), "isLumber"), PersistentDataType.BOOLEAN);
                container.set(new NamespacedKey(Tmoney.getPlugin(), "isLumber"), PersistentDataType.BOOLEAN, !(isLumber));


                container.set(new NamespacedKey(Tmoney.getPlugin(), "Lumberexp"), PersistentDataType.FLOAT, resetValue);



            }
            event.setCancelled(true);
            new JobMenu(player);
        }

    }
}
