package xyz.kiradev.magic.utils.command;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import xyz.kiradev.magic.utils.C;

import java.util.Objects;

@Getter
public abstract class BukkitCommand implements CommandExecutor {

    private final ICommand iCommand;

    public BukkitCommand() {
        iCommand = getClass().getDeclaredAnnotation(ICommand.class);
        Objects.requireNonNull(iCommand, "Command requires the ICommand annotation");
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(iCommand.consoleOnly()) {
            if(sender instanceof ConsoleCommandSender) {
                execute((ConsoleCommandSender) sender, args);
            } else {
                C.sendMessage(sender, "&cThis command is executable by the console.");
            }
        } else if(iCommand.playersOnly()) {
            if(sender instanceof Player) {
                if(iCommand.permission() != null && !sender.hasPermission(iCommand.permission())) {
                    C.sendMessage(sender, "&cYou don't have permission to execute this command.");
                } else execute((Player) sender, args);
            } else {
                C.sendMessage(sender, "&cThis command is executable by the players.");
            }
        } else {
            if(iCommand.permission() != null && !sender.hasPermission(iCommand.permission())) {
                C.sendMessage(sender, "&cYou don't have permission to execute this command.");
            } else {
                execute(sender, args);
            }
        }
        return true;
    }

    public void execute(CommandSender sender, String[] args) {}
    public void execute(Player player, String[] args) {}
    public void execute(ConsoleCommandSender console, String[] args) {}
}
