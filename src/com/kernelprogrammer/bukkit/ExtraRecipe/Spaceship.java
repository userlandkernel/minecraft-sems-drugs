package com.kernelprogrammer.bukkit.ExtraRecipe;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.html.parser.Entity;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.Furnace;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Directional;

public class Spaceship {

	private ArrayList<Player> passengers = new ArrayList<Player>();

	public Spaceship() {

	}

	public Spaceship(ArrayList<Player> passengers) {
		this.passengers = passengers;
	}

	public Boolean fly(PlayerInteractEvent event) {

		Player captain = event.getPlayer(); // Who is driving the spaceship
		Location captainLoc = captain.getLocation(); // Where is the captain

		// TODO: Check adjecent block layout and types

		// A captain is also a passenger
		passengers.add(captain);

		Block clicked = event.getClickedBlock(); // Captain clicked what block?
		org.bukkit.entity.Entity captainVehicle = captain.getVehicle();

		// We cannot fly without a full spaceship
		if(captainVehicle == null)
			return false;

		// We need a valid spaceship design, paper planes don't fly dumbass
		if(captainVehicle.getType() != EntityType.BOAT)
			return false;

		// If it's the ship's computer sign
		if(clicked != null && clicked.getState() instanceof Sign) {

			Sign computer = (Sign)clicked.getState();
			
			if(computer.getLines().length == 0)
				return false;

			// Get the target world from the sign
			String world = computer.getLine(0);

			// If the world exists and it isn't the current world we can fly to it
			World targetWorld = Bukkit.getWorld(world);
			if(targetWorld != null && captain.getWorld().getName() != world) {
				
				// Parse the location data from the sign
				Location targetLoc = null;
				if(computer.getLines().length >= 2) {

					// Calculate target coordinations
					String targetCoords = computer.getLine(1);
					String[] cords = targetCoords.trim().split("\\s+");
					if(cords.length >= 3) {
						int targetX = 0, targetY = 0, targetZ = 0;
						try {
							targetX = Integer.parseInt(cords[0]);
							targetY = Integer.parseInt(cords[1]);
							targetZ = Integer.parseInt(cords[2]);
						} 
						catch(NumberFormatException exc)
						{
							captain.sendMessage("Invalid coordinates.");
							return false;
						}
						
						WallSign signData = (WallSign)computer.getBlock().getState().getBlockData();
						Block fuelTank = clicked.getRelative(signData.getFacing().getOppositeFace());
						if(fuelTank == null) {
							captain.sendMessage("This spaceship is incomplete.");
							return false;
						}

						if (fuelTank.getType() != Material.FURNACE) {
							captain.sendMessage("This spaceship needs a fuel tank.");
							return false;
						}

						Furnace fuelSupply = (Furnace)fuelTank.getState();
						FurnaceInventory fuelInventory = fuelSupply.getInventory();
						ItemStack fuel = fuelInventory.getFuel();
						if(fuel == null) {
							captain.sendMessage("Please add fuel to the fuel tank.");
						}
						else if(fuel.getAmount() < 64)
						{
							captain.sendMessage("Not enough fuel to travel, needs 64 blocks of coal.");
						}
						else {

							targetLoc = new Location(targetWorld, targetX, targetY, targetZ); // Set course
							fuelInventory.clear(); // Clear the inventory
							computer.setLine(0, "");
							computer.setLine(1, " In transit ");
							computer.setLine(2, "");
							computer.setLine(3, "");
							computer.update();
							for(Player passenger : passengers) {
								passenger.sendMessage("To boldly go where no man has gone before! Have a safe yourney to "+world+" ;)");
								passenger.teleport(targetLoc);
								passenger.addScoreboardTag("IN_TRANSIT");
							}
							return true;
						}
						
					}
				}
			}
		}
		return false;
	}
}
