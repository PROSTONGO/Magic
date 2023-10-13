package xyz.kiradev.magic.commands;

import org.bukkit.entity.Player;
import xyz.kiradev.magic.Magic;
import xyz.kiradev.magic.utils.C;
import xyz.kiradev.magic.utils.command.BukkitCommand;
import xyz.kiradev.magic.utils.command.ICommand;

@ICommand(names = {"setminY"}, permission = "magic.admin", playersOnly = true)
public class SetMinimumYCommand extends BukkitCommand {
    @Override
    public void execute(Player player, String[] args) {
        if(args.length > 1) {
            C.sendMessage(player, "&cPlease provide 1 number");
        } else {
            try {
                double y = Double.valueOf(args[0]);
                Magic.getInstance().getConfig().set("Minimum-Y", y);
                Magic.getInstance().saveConfig();
                C.sendMessage(player, "&cSuccessfully set the minimum Y to " + y);
            } catch (Exception ignored) {
                C.sendMessage(player, "&cPlease provide a number.");
            }
        }
    }
}
