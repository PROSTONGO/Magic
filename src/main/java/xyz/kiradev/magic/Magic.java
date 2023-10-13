package xyz.kiradev.magic;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.kiradev.magic.utils.PluginUtil;

public final class Magic extends JavaPlugin {
    @Getter private static Magic instance;
    @Override
    public void onEnable() {
        instance = this;
        PluginUtil.registerListeners(getClass().getPackage().getName() + ".listeners");
        PluginUtil.registerCommands(getClass().getPackage().getName() + ".commands");
    }

    @Override
    public void onDisable() {}
}
