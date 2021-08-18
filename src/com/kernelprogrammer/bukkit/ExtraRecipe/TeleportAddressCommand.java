package com.kernelprogrammer.bukkit.ExtraRecipe;
import java.io.Console;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.craftbukkit.v1_17_R1.*;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.server.commands.data.CommandDataStorage;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.level.storage.PersistentCommandStorage;
import net.minecraft.world.level.storage.WorldPersistentData;
import net.minecraft.world.level.storage.loot.providers.nbt.StorageNbtProvider;


public class TeleportAddressCommand implements CommandExecutor {

	 public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
		 
		 if (split.length == 0) {
			 sender.sendMessage("Please specify entity and street name.");
		 }
		 else  {
			if(sender instanceof Player) {
				WorldServer fromWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
				WorldPersistentData persistentData = fromWorld.getWorldPersistentData();
				NBTTagCompound locations = new PersistentCommandStorage(persistentData).a(new MinecraftKey("locations"));
				NBTTagCompound streets = locations.getCompound("streets");
				sender.sendMessage(streets.getKeys().toString());
			}
		 }
		 return true;
	 }

}
