package com.kernelprogrammer.bukkit.ExtraRecipe;
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

public class ExtraItemManager {

	static private int randomBetween(int min, int max) {
		Random random = new Random();
		return random.ints(min, max)
		  .findFirst()
		  .getAsInt();
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
				effects.add(new PotionEffect(PotionEffectType.WEAKNESS, randomBetween(200, 400), 1));
				effects.add(new PotionEffect(PotionEffectType.SLOW, randomBetween(200, 400), randomBetween(1, 6)));
				p.addPotionEffects(effects);
			}
		}.runTaskLater(plugin, 200);
	}

	static void triggerEffect(ExtraRecipe plugin, Player p, ExtraItem item) {
		switch(item) {
			case COCAINE:
				cocaineEffect(plugin, p);
				break;
			default:
				break;
		}
	}

	static ExtraItem isCustomItem(ItemStack item) {
		ItemMeta itemMeta = item.getItemMeta();

		// All custom items must have a display name
		if(!itemMeta.hasDisplayName())
			return null;

		// Iterate over all possible custom items to find the equally named one
		ExtraItem equality = null;
		for(ExtraItem customItem : ExtraItem.values()) {

			// If the equally named one is found update our reference
			if( itemMeta.getDisplayName().equals(customItem.getItemStack().getItemMeta().getDisplayName() )) {
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
