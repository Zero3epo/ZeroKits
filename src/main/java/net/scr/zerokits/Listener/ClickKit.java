package net.scr.zerokits.Listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickKit implements Listener {
    @EventHandler
    public void onClickKit(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        // Базовые проверки
        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        if (e.getClickedInventory() == null) return;
        if (!e.getView().getTitle().equals("ZeroKits")) return;


    }
}
