package com.kernelprogrammer.bukkit.ExtraRecipe;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.text.html.parser.Entity;

public class ExtraItemManager {

	static private int randomBetween(int min, int max) {
		Random random = new Random();
		return random.ints(min, max)
		  .findFirst()
		  .getAsInt();
	}

	static private String randomName() {
		List<String> colors = new ArrayList<>(List.of("Red", "Pink", "Blue", "Orange", "Green", "Purple", "Black", "White", "Grey", "Yellow"));
		List<String> professions = new ArrayList<>(List.of("King", "Wanderer", "Trader", "Wizard", "Farmer", "Knight", "Queen", "Priest", "Traveler", "Blacksmith", "Astronaut", "Doctor", "Fisher", "Archer", "Angel", "Demon", "Ghost", "Warlord", "Herbalist", "Pirate", "Mine worker", "Cook"));
		return colors.get(randomBetween(0, colors.size())) + " " + professions.get(randomBetween(0, professions.size()));
	}

	private static void cocaineEffect(ExtraRecipe plugin, Player p) {
		
		// Add 'high' effects
		p.sendMessage("You feel amazingly high on coke and are the king of the village.");
		ArrayList<PotionEffect> effects = new ArrayList<PotionEffect>();
		effects.add(new PotionEffect(PotionEffectType.SPEED, 200, 3));
		effects.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 3));
		effects.add(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 200, randomBetween(1, 7)));
		p.addPotionEffects(effects);
		
		// Add 'comedown' effects
		new BukkitRunnable(){
			@Override
			public void run(){
				p.sendMessage("You feel tired and a great desire to redose cocaine.");
				ArrayList<PotionEffect> effects = new ArrayList<PotionEffect>();
				effects.add(new PotionEffect(PotionEffectType.WEAKNESS, 200, 3));
				effects.add(new PotionEffect(PotionEffectType.SLOW, randomBetween(200, 400), randomBetween(1, 6)));
				if(randomBetween(0, 10) > 5) {
					effects.add(new PotionEffect(PotionEffectType.WITHER, 200, 3));
				}
				p.addPotionEffects(effects);
			}
		}.runTaskLater(plugin, 200);
	}


	static void weedEffect(ExtraRecipe plugin, Player p) {
		
		// Add 'high' effects
		p.sendMessage("You feel baked as hell.");
		
		ArrayList<PotionEffect> effects = new ArrayList<PotionEffect>();
		effects.add(new PotionEffect(PotionEffectType.HUNGER, 3000, 3));
		effects.add(new PotionEffect(PotionEffectType.HEALTH_BOOST, 3000, 3));
		effects.add(new PotionEffect(PotionEffectType.SLOW_DIGGING, randomBetween(400, 3000), 1));
		effects.add(new PotionEffect(PotionEffectType.WEAKNESS, randomBetween(400, 3000), 1));
		p.addPotionEffects(effects);
		
		// Add 'comedown' effects
		new BukkitRunnable(){
			@Override
			public void run(){
				p.sendMessage("You feel pretty tired as your weed trip ends.");
		
			}
		}.runTaskLater(plugin, 3000);

	}

	static void mdmaEffect(ExtraRecipe plugin, Player p) {
		
		// Add 'high' effects
		p.sendMessage("You feel incredibly euphoric and sociable.");
		
		ArrayList<PotionEffect> effects = new ArrayList<PotionEffect>();
		effects.add(new PotionEffect(PotionEffectType.CONFUSION, 3000, 3));
		effects.add(new PotionEffect(PotionEffectType.GLOWING, 3000, 3));
		effects.add(new PotionEffect(PotionEffectType.SPEED, randomBetween(1000, 3000), 2));
		effects.add(new PotionEffect(PotionEffectType.FAST_DIGGING, randomBetween(1000, 3000), 1));
		effects.add(new PotionEffect(PotionEffectType.LUCK, randomBetween(1000, 3000), 1));
		effects.add(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, randomBetween(1000, 3000), 1));
		if(randomBetween(0, 10) >= 5) {
			effects.add(new PotionEffect(PotionEffectType.POISON, randomBetween(1000, 3000), 2));
		}
		p.addPotionEffects(effects);
		
		// Add 'comedown' effects
		new BukkitRunnable(){
			@Override
			public void run(){
				ArrayList<PotionEffect> effects = new ArrayList<PotionEffect>();
				p.sendMessage("You feel depressed and tired as your trip ends.");
				effects.add(new PotionEffect(PotionEffectType.UNLUCK, 3000, 1));
				effects.add(new PotionEffect(PotionEffectType.SLOW, randomBetween(1000, 3000), 1));
				effects.add(new PotionEffect(PotionEffectType.SLOW_DIGGING, randomBetween(1000, 3000), 1));
				effects.add(new PotionEffect(PotionEffectType.CONFUSION,  randomBetween(0, 3000), 3));
				if(randomBetween(0, 10) > 5) {
					effects.add(new PotionEffect(PotionEffectType.WITHER, 200, 3));
				}
				p.addPotionEffects(effects);
			}
		}.runTaskLater(plugin, 3000);

	}

	static void lsdEffect(ExtraRecipe plugin, Player p) {
		
		// Add 'high' effects
		p.sendMessage("You are tripping balls on LSD");
		
		ArrayList<PotionEffect> effects = new ArrayList<PotionEffect>();
		effects.add(new PotionEffect(PotionEffectType.CONFUSION, 8000, 3));
		effects.add(new PotionEffect(PotionEffectType.CONDUIT_POWER, 8000, 3));
		effects.add(new PotionEffect(PotionEffectType.ABSORPTION, 8000, 1));
		effects.add(new PotionEffect(PotionEffectType.LEVITATION, randomBetween(400, 8000), 1));
		effects.add(new PotionEffect(PotionEffectType.WATER_BREATHING, randomBetween(400, 8000), 1));
		effects.add(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, randomBetween(400, 8000), 1));
		p.addPotionEffects(effects);
		
		switch(randomBetween(0, 10)) {
			case 0:
			case 1:
			case 2:
			case 3:
				break;
			case 4:
			case 5:
			case 6:
			case 7:
				break;
			case 8:
			case 9:
			case 10:
				p.setCustomName(randomName());
				p.setCustomNameVisible(true);
				break;
		}

		

		// Add 'comedown' effects
		new BukkitRunnable(){
			@Override
			public void run(){
				p.sendMessage("The LSD has fully worked out and you barely remember what the fuck happened");
		
			}
		}.runTaskLater(plugin, 8000);

	}

	static void triggerEffect(ExtraRecipe plugin, Player p, ExtraItem item) {
		switch(item) {

			case COCAINE:
				cocaineEffect(plugin, p);
				break;

			case WEED:
				weedEffect(plugin, p);
				break;

			case ECSTASY:
				mdmaEffect(plugin, p);
				break;

			case COFFEE_CUP:
				p.sendMessage("Coffee is always good");
				p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1000, 1));
				break;
			case LSD:
				lsdEffect(plugin, p);
				break;

			default:
				break;
		}
	}

	static ExtraItem isCustomItem(ItemStack item) {

		ItemMeta itemMeta = item.getItemMeta();
		ExtraItem equality = null; // The corresponding custom item

		if(itemMeta == null)
			return null;

		// All custom items must have a display name
		if(!itemMeta.hasDisplayName())
			return null;

		// Iterate over all possible custom items to find the equally named one
		for(ExtraItem customItem : ExtraItem.values()) {

			// If the equally named one is found update our reference
			if( itemMeta.getDisplayName().equals(customItem.getItemStack().getItemMeta().getDisplayName())) {
				equality = customItem;
				break;
			}
		}

		// We need a candidate to work with
		if (equality == null)
			return null;

		// if the candidate item has a lore, check it
		if (equality.getItemStack().getItemMeta().hasLore()) {

			if (!itemMeta.hasLore())
				return null;

			// Must have equal lore line length
			List<String> customLore = equality.getItemStack().getItemMeta().getLore();
			List<String> itemLore = itemMeta.getLore();
			if(!customLore.equals(itemLore)) {
				return null;
			}

		}

		// All checks succeeded, the item is custom
		return equality;
	}
}
