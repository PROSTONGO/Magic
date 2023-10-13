package xyz.kiradev.magic.commands;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.kiradev.magic.utils.C;
import xyz.kiradev.magic.utils.command.BukkitCommand;
import xyz.kiradev.magic.utils.command.ICommand;

@ICommand(names = {"start"}, playersOnly = true)
public class StartCommand extends BukkitCommand {
    @Override
    public void execute(Player player, String[] args) {
        ItemStack bow = new ItemStack(Material.BOW, 1);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setDisplayName(C.color("&3&lBow"));
        bowMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 2, true);
        bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        bow.setItemMeta(bowMeta);

        player.getInventory().addItem(bow);
        player.getInventory().addItem(new ItemStack(Material.ARROW));
    }
}
