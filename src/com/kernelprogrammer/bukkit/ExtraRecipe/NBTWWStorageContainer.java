package com.kernelprogrammer.bukkit.ExtraRecipe;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.level.storage.PersistentCommandStorage;

public class NBTWWStorageContainer {
	private static final PersistentCommandStorage container = new PersistentCommandStorage(((WorldServer)((CraftWorld)Bukkit.getWorlds().get(0)).getHandle()).getWorldPersistentData());
	
	public static NBTTagCompound GetStorage(String name) {
		return container.a(new MinecraftKey(name));
	}
	
}
