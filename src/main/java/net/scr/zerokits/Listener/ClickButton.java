package net.scr.zerokits.Listener;

import net.scr.zerokits.ZeroKits;
import net.scr.zerokits.items.AdKits;
import net.scr.zerokits.items.InventoryManager;
import net.scr.zerokits.items.MenuItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static net.scr.zerokits.command.kits.currentMenu;

public class ClickButton implements Listener {

    private final MenuItems menuItems;
    private final ZeroKits plugin;
    private final AdKits adKits;
    private final InventoryManager inventoryManager;


    public ClickButton(MenuItems menuItems, ZeroKits plugin) {
        this.menuItems = menuItems;
        this.plugin = plugin;
        this.inventoryManager = plugin.getInventoryManager();
        this.adKits = plugin.getAdItems();
    }

    @EventHandler
    public void onClickButton(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = inventoryManager.getPlayerInventory(p);

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

            clearMenu(inv);

            inv.setItem(20, adKits.getAnchorKit());
            inv.setItem(21, adKits.getAppleKit());
            inv.setItem(22, adKits.getNetherKit());
            inv.setItem(23, adKits.getTotemKit());
            inv.setItem(24, adKits.getEnderKit());
            inv.setItem(29, adKits.getElytraKit());
            inv.setItem(30, adKits.getShieldKit());
            inv.setItem(31, adKits.getCrystalKit());
            inv.setItem(32 , adKits.getUHCKit());
            inv.setItem(33 , adKits.getDiamondKit());



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

            clearMenu(inv);

            inv.setItem(21, adKits.getPigKit());
            inv.setItem(22, adKits.getWaterKit());
            inv.setItem(23, adKits.getSumoKit());
            inv.setItem(30, adKits.getCrossbowKit());
            inv.setItem(31, adKits.getSphereKit());
            inv.setItem(32 , adKits.getTrolleyTntKit());


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
    private void clearMenu(Inventory inv) {
        // Очищаем центральные слоты (все, кроме рамки)
        int[] slotsToClear = {
                10,11,12,13,14,15,16,
                19,20,21,22,23,24,25,
                28,29,30,31,32,33,34,
                37,38,39,40,41,42,43
        };

        for (int slot : slotsToClear) {
            inv.setItem(slot, null); // null работает так же как new ItemStack(Material.AIR)
        }
    }


}