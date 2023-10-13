package xyz.kiradev.magic.commands;

import org.bukkit.entity.Player;
import xyz.kiradev.magic.spawn.Spawn;
import xyz.kiradev.magic.utils.C;
import xyz.kiradev.magic.utils.command.BukkitCommand;
import xyz.kiradev.magic.utils.command.ICommand;

@ICommand(names = {"setspawn"}, permission = "magic.admin", playersOnly = true)
public class SetSpawnCommand extends BukkitCommand {
    @Override
    public void execute(Player player, String[] args) {
        Spawn spawn = new Spawn(player.getLocation());
        spawn.setSpawn();
        C.sendMessage(player, "&aThe spawn has been set to your location.");
    }
}
