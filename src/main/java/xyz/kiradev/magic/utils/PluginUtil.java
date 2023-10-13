package xyz.kiradev.magic.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.reflections.Reflections;
import xyz.kiradev.magic.Magic;
import xyz.kiradev.magic.utils.command.BukkitCommand;

@UtilityClass
public class PluginUtil {
    public void registerListeners(Listener... listeners) {
        for(Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, Magic.getInstance());
        }
    }

    public void registerListeners(String packageName) {
        for(Class<?> clazz : new Reflections(packageName).getSubTypesOf(Listener.class)) {
            try {
                Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                registerListeners(listener);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void registerCommands(String packageName) {
        for(Class<? extends BukkitCommand> clazz : new Reflections(packageName).getSubTypesOf(BukkitCommand.class)) {
            try {
                BukkitCommand command = clazz.getDeclaredConstructor().newInstance();
                for(String str : command.getICommand().names()) {
                    Magic.getInstance().getCommand(str).setExecutor(command);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}