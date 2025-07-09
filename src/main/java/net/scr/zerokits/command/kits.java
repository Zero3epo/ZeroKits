package net.scr.zerokits.command;

import net.scr.zerokits.ZeroKits;
import net.scr.zerokits.items.MenuItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class kits implements CommandExecutor {

    private final ZeroKits plugin;
    private final MenuItems menuItems;

    public kits(ZeroKits plugin) {
        this.plugin = plugin;
        this.menuItems = plugin.getMenuItems(); // Получаем доступ к MenuItems
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        //получение текста из кфг//получение текста из кфг//получение текста из кфг//
        String err_open = plugin.getConfig().getString("err_open");
        if(err_open == null) return false;
        err_open = ChatColor.translateAlternateColorCodes('&', err_open);
        //получение текста из кфг//получение текста из кфг//получение текста из кфг//

        plugin.reloadConfig();
        int[] slots = {9, 17, 18, 26, 27, 35, 36, 44};
        if(!(sender instanceof Player)) {
            Bukkit.getLogger().info(err_open);
            return true;
        }
        Player p = (Player) sender;

        Inventory inv = Bukkit.createInventory(p, 54, "ZeroKits");

        for(int i = 0; i <= 8; i++ ) {
            inv.setItem(i, menuItems.getPanel());
        }
        for (int slot : slots) {
            inv.setItem(slot, menuItems.getPanel());
        }
        for(int i = 45; i <= 53; i++) {
            switch (i) {
                case 48:
                    inv.setItem(i, menuItems.getAdectKitPanel());
                    break;
                case 49:
                    inv.setItem(i, menuItems.getPMPanel());
                    break;
                case 50:
                    inv.setItem(i, menuItems.getPizdecPanel());
                    break;
                default:
                    inv.setItem(i, menuItems.getPanel());
                    break;
            }
        }

        p.openInventory(inv);



        return true;
    }
}
