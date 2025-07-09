package net.scr.zerokits;

import net.scr.zerokits.Listener.ClickButton;
import net.scr.zerokits.command.kits;
import net.scr.zerokits.items.MenuItems;
import org.bukkit.plugin.java.JavaPlugin;

public final class ZeroKits extends JavaPlugin {

    private MenuItems menuItems;

    @Override
    public void onEnable() {
        System.out.println("ZeroKits has enable");
        this.menuItems = new MenuItems(this);
        getCommand("Zkit").setExecutor(new kits(this));
        getServer().getPluginManager().registerEvents(new ClickButton(this.getMenuItems(), this), this);

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        System.out.println("ZeroKits has disable");
    }

    public MenuItems getMenuItems() {
        return menuItems;
    }

}
