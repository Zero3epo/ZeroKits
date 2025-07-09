package net.scr.zerokits.items;

import net.scr.zerokits.ZeroKits;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuItems {
    private final ZeroKits plugin;
    private final String adectKitText;
    private final String pmKitText;
    private final String pizdecKitText;

    public MenuItems(ZeroKits plugin) {
        this.plugin = plugin;

        // Загрузка текстов из конфига
        this.adectKitText = getConfigString("Adkit");
        this.pmKitText = getConfigString("PMkit");
        this.pizdecKitText = getConfigString("PizdecKit");
    }

    private String getConfigString(String path) {
        String value = plugin.getConfig().getString(path);
        if (value == null) {
            plugin.getLogger().warning("Не найдена конфигурация для: " + path);
            return "[" + path + "]"; // Возвращаем значение по умолчанию
        }
        return ChatColor.translateAlternateColorCodes('&', value);
    }

    public ItemStack getPanel() {
        ItemStack panel = new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE);
        ItemMeta meta = panel.getItemMeta();
        meta.setDisplayName(" ");
        panel.setItemMeta(meta);
        return panel;
    }

    public ItemStack getPMPanel() {
        ItemStack panel = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta meta = panel.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + pmKitText);
        panel.setItemMeta(meta);
        return panel;
    }

    public ItemStack getPizdecPanel() {
        ItemStack panel = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta = panel.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + pizdecKitText);
        panel.setItemMeta(meta);
        return panel;
    }

    public ItemStack getAdectKitPanel() {
        ItemStack panel = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta meta = panel.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + adectKitText);
        panel.setItemMeta(meta);
        return panel;
    }

}