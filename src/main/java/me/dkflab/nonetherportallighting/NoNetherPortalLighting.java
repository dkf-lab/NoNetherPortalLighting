package me.dkflab.nonetherportallighting;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoNetherPortalLighting extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this,this);
    }

    @EventHandler
    public void onInteract(PortalCreateEvent e) {
        if (e.getEntity() != null) {
            if (e.getEntity().getType().equals(EntityType.PLAYER)) {
                Player p = (Player) e.getEntity();
                if (p.hasPermission("dkflab.admin")) {
                    return;
                }
                p.sendMessage(ChatColor.RED + "You can't create portals!");
            }
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onDestroy(BlockBreakEvent e) {
        if (e.getBlock().getType().equals(Material.NETHER_PORTAL)) {
            if (!e.getPlayer().hasPermission("dkflab.admin")) {
                e.getPlayer().sendMessage(ChatColor.RED + "You can't destroy portals!");
                e.setCancelled(true);
            }
        }
    }
}
