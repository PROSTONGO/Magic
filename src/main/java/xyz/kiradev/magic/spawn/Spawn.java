package xyz.kiradev.magic.spawn;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import xyz.kiradev.magic.Magic;

@RequiredArgsConstructor
public class Spawn {
    private final Location location;

    public void setSpawn() {
        FileConfiguration config = Magic.getInstance().getConfig();
        config.set("spawn.x", location.getX());
        config.set("spawn.y", location.getY());
        config.set("spawn.z", location.getZ());
        config.set("spawn.yaw", location.getYaw());
        config.set("spawn.pitch", location.getPitch());
        config.set("spawn.world", location.getWorld().getName());
        Magic.getInstance().saveConfig();
    }

    public static boolean isSet() {
        return Magic.getInstance().getConfig().get("spawn.x") != null;
    }

    public static void teleport(Player player) {
        FileConfiguration config = Magic.getInstance().getConfig();
        player.teleport(new Location(Bukkit.getWorld(config.getString("spawn.world")), config.getDouble("spawn.x"),
                config.getDouble("spawn.y"),
                config.getDouble("spawn.z"),
                config.getInt("spawn.yaw"),
                config.getInt("spawn.pitch")));
    }
}