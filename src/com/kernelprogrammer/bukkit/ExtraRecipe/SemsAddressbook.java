package com.kernelprogrammer.bukkit.ExtraRecipe;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.craftbukkit.v1_17_R1.util.CraftMagicNumbers;
import org.jetbrains.annotations.NotNull;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;



public class SemsAddressbook {
	private static NBTTagCompound addressBook = NBTWWStorageContainer.GetStorage("locations");
	public SemsAddressbook(){
	
	}


	public static List<String> GetStreets(String world) {

		if(addressBook == null) {
			return null; // Exception would be better, but we let it run for now
		}
		List<String> streetRepo = new ArrayList<String>();
		NBTTagList streets = addressBook.getList("streets", CraftMagicNumbers.NBT.TAG_COMPOUND); // 10 is the type
		if(streets == null) {
			return streetRepo;
		}
		for (NBTBase street : streets) {
			String streetName = ((NBTTagCompound)street).getString("id");
			String worldName = ((NBTTagCompound)street).getString("world");
			if(world != null) {
				if(worldName.equals(world)) {
					streetRepo.add(streetName);
				}
			}
			else {
				streetRepo.add(streetName);
			}
		}
		return streetRepo;
	}

	public static NBTTagCompound GetAddress(String request, String world) {
		
		if(addressBook == null) {
			return null; // Exception would be better, but we let it run for now
		}

		NBTTagList streets = addressBook.getList("streets", CraftMagicNumbers.NBT.TAG_COMPOUND); // 10 is the type
		NBTTagCompound result = null;
		if(streets == null) {
			return result;
		}
		for (NBTBase street : streets) {
			String streetName = ((NBTTagCompound)street).getString("id");
			String worldName = ((NBTTagCompound)street).getString("world");
			if(streetName.equals(request)) {
				if(world != null) {
					if(worldName.equals(world)) {
						result = ((NBTTagCompound)street);
						break;
					}
				}
				result = ((NBTTagCompound)street);
				break;
			}
		}
		return result;
	}


	public static NBTTagCompound GetAddress(String request) {
		return GetAddress(request, null);
	}

	public static boolean StoreAddress(@NotNull String street, @NotNull org.bukkit.Location location, String world) {
		if(addressBook == null) {
			return false; // Exception would be better, but we let it run for now
		}

		NBTTagList streets = addressBook.getList("streets", CraftMagicNumbers.NBT.TAG_COMPOUND); // 10 is the type
		NBTTagCompound streetNBT = new NBTTagCompound();
		streetNBT.setString("id", street);
		streetNBT.setInt("x", (int)location.getX());
		streetNBT.setInt("y", (int)location.getY());
		streetNBT.setInt("z", (int)location.getZ());
		streetNBT.setString("world", world);
		streets.add(streetNBT);
		return false;
	
	}

	
}
