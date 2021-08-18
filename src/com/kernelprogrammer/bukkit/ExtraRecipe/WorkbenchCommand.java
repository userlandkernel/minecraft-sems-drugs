package com.kernelprogrammer.bukkit.ExtraRecipe;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorkbenchCommand implements CommandExecutor {

	 public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
		 
		 if (split.length == 0) {
			if(sender instanceof Player) {
				((Player)sender).openWorkbench(null, true);
			}
		 }
		 return true;
	 }

}
