package net.scr.zerokits.Listener;

import net.scr.zerokits.ZeroKits;
import net.scr.zerokits.items.AdKits;
import net.scr.zerokits.items.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ClickKit implements Listener {

    private final ZeroKits plugin;
    private final InventoryManager inventoryManager;
    private final AdKits adKits;
    private final HashMap<UUID, ItemStack> playerKits = new HashMap<>();

    private final HashMap<ItemStack, List<UUID>> kitPlayers = new HashMap<>();

    public ClickKit(ZeroKits plugin) {
        this.plugin = plugin;
        this.inventoryManager = plugin.getInventoryManager();
        this.adKits = plugin.getAdItems();
    }

    @EventHandler
    public void onClickKit(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = inventoryManager.getPlayerInventory(p);
        ItemStack clicked = e.getCurrentItem();

        String start = plugin.getConfig().getString("start_fight");

        // Базовые проверки
        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        if (e.getClickedInventory() == null) return;
        if (!e.getView().getTitle().equals("ZeroKits")) return;

        e.setCancelled(true);

        if (clicked.isSimilar(adKits.getCrystalKit())) {
            handleKitSelection(p, adKits.getCrystalKit(), "Crystal кит", start);
        }
        else if (clicked.isSimilar(adKits.getShieldKit())) {
            handleKitSelection(p, adKits.getShieldKit(), "Щитовой кит", start);
        }
        else if (clicked.isSimilar(adKits.getDiamondKit())) {
            handleKitSelection(p, adKits.getDiamondKit(), "Алмазный кит", start);
        }
        else if (clicked.isSimilar(adKits.getNetherKit())) {
            handleKitSelection(p, adKits.getNetherKit(), "Незеритовый кит", start);
        }
        else if (clicked.isSimilar(adKits.getEnderKit())) {
            handleKitSelection(p, adKits.getEnderKit(), "Эндер кит", start);
        }
        else if (clicked.isSimilar(adKits.getElytraKit())) {
            handleKitSelection(p, adKits.getElytraKit(), "Элитровый кит", start);
        }
        else if (clicked.isSimilar(adKits.getUHCKit())) {
            handleKitSelection(p, adKits.getUHCKit(), "UHC кит", start);
        }
        else if (clicked.isSimilar(adKits.getAppleKit())) {
            handleKitSelection(p, adKits.getAppleKit(), "Яблочный кит", start);
        }
        else if (clicked.isSimilar(adKits.getAnchorKit())) {
            handleKitSelection(p, adKits.getAnchorKit(), "Анкерный кит", start);
        }
        else if (clicked.isSimilar(adKits.getTotemKit())) {
            handleKitSelection(p, adKits.getTotemKit(), "Тотемный кит", start);
        }
        else if (clicked.isSimilar(adKits.getPigKit())) {
            handleKitSelection(p, adKits.getPigKit(), "Хрю кит", start);
        }
        else if (clicked.isSimilar(adKits.getWaterKit())) {
            handleKitSelection(p, adKits.getWaterKit(), "Водный кит", start);
        }
        else if (clicked.isSimilar(adKits.getSumoKit())) {
            handleKitSelection(p, adKits.getSumoKit(), "Сумо кит", start);
        }
        else if (clicked.isSimilar(adKits.getCrossbowKit())) {
            handleKitSelection(p, adKits.getCrossbowKit(), "Арбалетный кит", start);
        }
        else if (clicked.isSimilar(adKits.getSphereKit())) {
            handleKitSelection(p, adKits.getSphereKit(), "Сферовый кит", start);
        }
        else if (clicked.isSimilar(adKits.getTrolleyTntKit())) {
            handleKitSelection(p, adKits.getTrolleyTntKit(), "TNT-вагон", start);
        }



    }

    //да, снизу неиронка. Но бля, как же мне было в падлу выписывать эт всё ._.

    private void handleKitSelection(Player player, ItemStack kit, String kitName, String start) {
        // 1. Удаляем игрока из всех предыдущих записей
        playerKits.remove(player.getUniqueId()); // Удаляем из "кто какой кит выбрал"

        // Удаляем из всех списков "кто выбрал этот кит"
        for (List<UUID> players : kitPlayers.values()) {
            players.remove(player.getUniqueId());
        }

        // 2. Добавляем новую запись
        playerKits.put(player.getUniqueId(), kit); // Запоминаем выбор игрока

        // Если для этого кита ещё нет списка - создаём
        if (!kitPlayers.containsKey(kit)) {
            kitPlayers.put(kit, new ArrayList<>());
        }

        // Добавляем игрока в список выбравших этот кит
        kitPlayers.get(kit).add(player.getUniqueId());

        player.sendMessage("Вы выбрали: " + kitName);

        // 3. Проверяем, есть ли дуэлянты
        checkForDuels(kit, start);
    }

    private void checkForDuels(ItemStack kit, String start) {
        // Получаем список игроков с этим китом
        List<UUID> players = kitPlayers.getOrDefault(kit, new ArrayList<>());

        // Если есть хотя бы 2 игрока
        if (players.size() >= 2) {
            Player p1 = Bukkit.getPlayer(players.get(0));
            Player p2 = Bukkit.getPlayer(players.get(1));

            if (p1 != null && p2 != null) { // Проверяем, что они онлайн
                String kitName = getKitName(kit); // Получаем название кита

                String start1 = ChatColor.translateAlternateColorCodes('&',
                        start.replace("%player%", p2.getName()).replace("%kit%", kitName));

                String start2 = ChatColor.translateAlternateColorCodes('&',
                        start.replace("%player%", p1.getName()).replace("%kit%", kitName));

                p1.sendMessage(start1);
                p2.sendMessage(start2);

                // Очищаем список, чтобы не было повторных срабатываний
                kitPlayers.get(kit).clear();

                p1.closeInventory();
                p2.closeInventory();
                p1.getInventory().clear();
                p2.getInventory().clear();




            }
        }
    }


    private String getKitName(ItemStack kit) {
        if (kit == null) return "Неизвестный кит";

        if (kit.isSimilar(adKits.getCrystalKit())) return "Crystal кит";
        if (kit.isSimilar(adKits.getShieldKit())) return "Щитовой кит";
        if (kit.isSimilar(adKits.getDiamondKit())) return "Алмазный кит";
        if (kit.isSimilar(adKits.getNetherKit())) return "Незеритовый кит";
        if (kit.isSimilar(adKits.getEnderKit())) return "Эндер кит";
        if (kit.isSimilar(adKits.getElytraKit())) return "Элитровый кит";
        if (kit.isSimilar(adKits.getUHCKit())) return "UHC кит";
        if (kit.isSimilar(adKits.getAppleKit())) return "Яблочный кит";
        if (kit.isSimilar(adKits.getAnchorKit())) return "Анкерный кит";
        if (kit.isSimilar(adKits.getTotemKit())) return "Тотемный кит";
        if (kit.isSimilar(adKits.getPigKit())) return "Хрю кит";
        if (kit.isSimilar(adKits.getWaterKit())) return "Водный кит";
        if (kit.isSimilar(adKits.getSumoKit())) return "Сумо кит";
        if (kit.isSimilar(adKits.getCrossbowKit())) return "Арбалетный кит";
        if (kit.isSimilar(adKits.getSphereKit())) return "Сферовый кит";
        if (kit.isSimilar(adKits.getTrolleyTntKit())) return "TNT-вагон";

        return "Неизвестный кит";
    }

}
