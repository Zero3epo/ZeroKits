package net.scr.zerokits.Listener;

import net.scr.zerokits.Listener.ClickKit;
import net.scr.zerokits.ZeroKits;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

import static net.scr.zerokits.Listener.ClickKit.duelPlayers;
import static net.scr.zerokits.Listener.ClickKit.arenasStatus;
import static net.scr.zerokits.Listener.ClickKit.playerArena;

public class CheckDuels implements Listener {
    private final ZeroKits plugin;

    public CheckDuels(ZeroKits plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCheckDuels(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();

        if(duelPlayers.containsKey(p.getUniqueId())) {
            UUID opId = duelPlayers.get(p.getUniqueId());
            Player op = Bukkit.getPlayer(opId);
            if(op != null) {
                op.sendMessage(ChatColor.GREEN + "Вы одержали победу над игроком " + ChatColor.YELLOW + p.getName());
                p.sendMessage(ChatColor.RED + "Вы проиграли игроку " + ChatColor.YELLOW + op.getName());
                Integer arenaId = playerArena.get(p.getUniqueId());

                if(arenaId != null) {
                    arenasStatus.remove(arenaId, false);
                    playerArena.remove(p.getUniqueId());
                    playerArena.remove(op.getUniqueId());
                    duelPlayers.remove(p.getUniqueId());
                    duelPlayers.remove(op.getUniqueId());
                    plugin.getConfig().set("coordinate.arena_" + arenaId + ".players", false);
                    plugin.saveConfig();
                }
            }
        }
    }
}
