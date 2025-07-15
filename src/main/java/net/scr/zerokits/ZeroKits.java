package net.scr.zerokits;

import net.scr.zerokits.Listener.CheckDuels;
import net.scr.zerokits.Listener.ClickButton;
import net.scr.zerokits.Listener.ClickKit;
import net.scr.zerokits.command.kits;
import net.scr.zerokits.items.AdKits;
import net.scr.zerokits.items.InventoryManager;
import net.scr.zerokits.items.MenuItems;
import org.bukkit.plugin.java.JavaPlugin;

public final class ZeroKits extends JavaPlugin {

    private MenuItems menuItems;
    private AdKits adKits;
    private InventoryManager inventoryManager;

    @Override
    public void onEnable() {
        System.out.println("ZeroKits has enable");
        this.menuItems = new MenuItems(this);
        this.adKits = new AdKits(this);
        this.inventoryManager = new InventoryManager();

        getCommand("Zkit").setExecutor(new kits(this));
        getServer().getPluginManager().registerEvents(new ClickButton(this.getMenuItems(), this), this);
        getServer().getPluginManager().registerEvents(new ClickKit(this), this);
        getServer().getPluginManager().registerEvents(new CheckDuels(this), this);

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        System.out.println("ZeroKits has disable");
    }

    public MenuItems getMenuItems() {
        return menuItems;
    }
    public AdKits getAdItems() {
        return adKits;
    }
    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }
}
