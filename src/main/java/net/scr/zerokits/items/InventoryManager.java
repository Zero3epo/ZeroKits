package net.scr.zerokits.items;


import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryManager {
    private final Map<UUID, Inventory> playerInventories = new HashMap<>();

    public void setPlayerInventory(Player player, Inventory inv) {
        playerInventories.put(player.getUniqueId(), inv);
    }

    public Inventory getPlayerInventory(Player player) {
        return playerInventories.get(player.getUniqueId());
    }

    public void removePlayerInventory(Player player) {
        playerInventories.remove(player.getUniqueId());
    }
}