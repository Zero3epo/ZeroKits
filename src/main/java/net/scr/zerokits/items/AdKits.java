package net.scr.zerokits.items;

import net.scr.zerokits.ZeroKits;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AdKits {
    private final ZeroKits plugin;

    public AdKits(ZeroKits plugin) {
        this.plugin = plugin;
    }

    // Основной метод для получения описания из конфига
    private List<String> getKitDescription(String path) {
        List<String> lines = plugin.getConfig().getStringList(path);
        List<String> coloredLines = new ArrayList<>();

        if (lines.isEmpty()) {
            plugin.getLogger().warning("Не найдена конфигурация для: " + path);
            coloredLines.add(ChatColor.RED + "[" + path + "]");
            return coloredLines;
        }

        for (String line : lines) {
            coloredLines.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        return coloredLines;
    }

    // Методы для создания предметов
    public ItemStack getCrystalKit() {
        ItemStack item = new ItemStack(Material.END_CRYSTAL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fCrystal кит"));
        meta.setLore(getKitDescription("crystal_kit"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getShieldKit() {
        ItemStack item = new ItemStack(Material.SHIELD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fЩитовой кит"));
        meta.setLore(getKitDescription("shield_kit"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getDiamondKit() {
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bАлмазный кит"));
        meta.setLore(getKitDescription("diamond_kit"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getNetherKit() {
        ItemStack item = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4Незеритовый кит"));
        meta.setLore(getKitDescription("nether_kit"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getEnderKit() {
        ItemStack item = new ItemStack(Material.ENDER_PEARL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5Эндер кит"));
        meta.setLore(getKitDescription("ender_kit"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getElytraKit() {
        ItemStack item = new ItemStack(Material.ELYTRA);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eЭлитровый кит"));
        meta.setLore(getKitDescription("elytra_kit"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getUHCKit() {
        ItemStack item = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6UHC кит"));
        meta.setLore(getKitDescription("uhc_kit"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getAppleKit() {
        ItemStack item = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cЯблочный кит"));
        meta.setLore(getKitDescription("apple_kit"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getAnchorKit() {
        ItemStack item = new ItemStack(Material.RESPAWN_ANCHOR);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4Анчор кит"));
        meta.setLore(getKitDescription("anchor_kit"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getTotemKit() {
        ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4Тотемный кит"));
        meta.setLore(getKitDescription("totem_kit"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getPigKit() {
        ItemStack item = new ItemStack(Material.PIG_SPAWN_EGG);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&dхрю кит"));
        meta.setLore(getKitDescription("fight_pig"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getWaterKit() {
        ItemStack item = new ItemStack(Material.WATER_BUCKET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4Водный кит"));
        meta.setLore(getKitDescription("water_kit"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getSumoKit() {
        ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4Сумо кит"));
        meta.setLore(getKitDescription("sumo_kit"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getCrossbowKit() {
        ItemStack item = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Арбалетный кит"));
        meta.setLore(getKitDescription("crossbow_kit"));
        meta.addEnchant(Enchantment.MULTISHOT, 1, true);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getSphereKit() {
        ItemStack item = new ItemStack(Material.ENDER_EYE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&dСферовый кит"));
        meta.setLore(getKitDescription("sphere_kit"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getTrolleyTntKit() {
        ItemStack item = new ItemStack(Material.TNT_MINECART);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4TNT-вагон"));
        meta.setLore(getKitDescription("trolley_tnt"));
        item.setItemMeta(meta);
        return item;
    }
}