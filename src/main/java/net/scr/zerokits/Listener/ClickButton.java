package net.scr.zerokits.Listener;

import net.scr.zerokits.ZeroKits;
import net.scr.zerokits.items.MenuItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ClickButton implements Listener {

    private final MenuItems menuItems;
    private final ZeroKits plugin;
    private int currentMenu = 0; // 0 - Adekt, 1 - PM, 2 - Pizdec

    public ClickButton(MenuItems menuItems, ZeroKits plugin) {
        this.menuItems = menuItems;
        this.plugin = plugin;
    }

    @EventHandler
    public void onClickButton(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        // Базовые проверки
        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        if (e.getClickedInventory() == null) return;
        if (!e.getView().getTitle().equals("ZeroKits")) return;

        e.setCancelled(true);

        // Получаем сообщение из конфига
        String alreadySelected = plugin.getConfig().getString("select_false");
        alreadySelected = ChatColor.translateAlternateColorCodes('&', alreadySelected);

        String select_menu = plugin.getConfig().getString("select_menu");
        select_menu = ChatColor.translateAlternateColorCodes('&', select_menu);

        // Проверяем нажатые кнопки
        ItemStack clicked = e.getCurrentItem();

        if (clicked.isSimilar(menuItems.getAdectKitPanel())) {
            if (currentMenu == 0) {
                p.sendMessage(alreadySelected);
                return;
            }
            currentMenu = 0;
            ChatColor.translateAlternateColorCodes('&', select_menu);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    select_menu.replace("%BTN_Menu%",
                            plugin.getConfig().getString("Adkit", "Adkit"))));

        } else if (clicked.isSimilar(menuItems.getPMPanel())) {
            if (currentMenu == 1) {
                p.sendMessage(alreadySelected);
                return;
            }
            currentMenu = 1;
            ChatColor.translateAlternateColorCodes('&', select_menu);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    select_menu.replace("%BTN_Menu%",
                            plugin.getConfig().getString("PMkit", "PMkit"))));

        } else if (clicked.isSimilar(menuItems.getPizdecPanel())) {
            if (currentMenu == 2) {
                p.sendMessage(alreadySelected);
                return;
            }
            currentMenu = 2;
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    select_menu.replace("%BTN_Menu%",
                            plugin.getConfig().getString("PizdecKit", "PizdecKit"))));
        }

        //////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////

    }
}