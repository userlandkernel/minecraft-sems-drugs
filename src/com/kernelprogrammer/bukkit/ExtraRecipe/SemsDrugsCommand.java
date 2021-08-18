package com.kernelprogrammer.bukkit.ExtraRecipe;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Handler for the /extrarecipe sample command.
 * @author userlandkernel
 */
public class SemsDrugsCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        if (split.length == 0) {
			if(sender instanceof Player) {
				Player invoker = (Player)sender;
				invoker.sendMessage("List of craftable drugs: ");
				for(ExtraItem item : ExtraItem.values()) {
					invoker.sendMessage("\t"+item.getItemStack().getItemMeta().getDisplayName());
				}
			}
        	return true;
        } else {
            return false;
        }
    }
}
