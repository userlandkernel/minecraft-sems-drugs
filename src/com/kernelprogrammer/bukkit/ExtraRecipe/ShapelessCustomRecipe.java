package com.kernelprogrammer.bukkit.ExtraRecipe;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.NamespacedKey;

public class ShapelessCustomRecipe extends ShapelessRecipe {
	
	ArrayList<ExtraItem> customIngredients = new ArrayList<ExtraItem>();
	
	public ShapelessCustomRecipe(ItemStack result) {
		super(result);
	}

	public ShapelessCustomRecipe(NamespacedKey key, ItemStack result) {
		super(key, result);
	}

	public void addCustomIngredient(int count, ExtraItem ingredient) {
		for(int i = 0; i < count; i++) {
			customIngredients.add(ingredient);
			super.addIngredient(ingredient.getMaterial());
		}
		
	}
	
	public ShapelessCustomRecipe addIngredient(int count, Material ingredient) {
		for(int i = 0; i < count; i++) {
			customIngredients.add(null);
			super.addIngredient(ingredient);
		}
		return this;
	}
	
}
