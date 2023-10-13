package xyz.kiradev.magic.listeners;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.kiradev.magic.Magic;
import xyz.kiradev.magic.spawn.Spawn;
import xyz.kiradev.magic.utils.C;

import java.util.Map;
import java.util.UUID;

public class GeneralListeners implements Listener {

    private Map<UUID, Boolean> dead = Maps.newHashMap();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(!Spawn.isSet()) C.sendMessage(player, "&cPlease set the spawn.");
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 200));
        for(String str : Magic.getInstance().getConfig().getStringList("Join-Message")) {
            C.sendMessage(player, str);
        }
        dead.put(player.getUniqueId(), false);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(event.getFrom().toString().equals(event.getTo().toString()) || !Spawn.isSet()) return;
        if(dead.get(player.getUniqueId())) return;
        if(event.getTo().getY() < Magic.getInstance().getConfig().getDouble("Minimum-Y")) {
            player.setHealth(0);
            dead.put(player.getUniqueId(), true);
            Bukkit.getScheduler().runTaskLater(Magic.getInstance(), () -> player.spigot().respawn(), 20);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if(Spawn.isSet()) Spawn.teleport(player);
        for(String str : Magic.getInstance().getConfig().getStringList("Join-Message")) {
            C.sendMessage(player, str);
        }
        dead.put(player.getUniqueId(), false);
        Bukkit.getScheduler().runTaskLater(Magic.getInstance(), () -> player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 200)), 20);
    }
}