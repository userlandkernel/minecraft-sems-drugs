package com.kernelprogrammer.bukkit.ExtraRecipe;
import java.util.Arrays;
import java.util.List;
 
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum ExtraItem
{

	SPACE_CAKE(Material.CAKE, ChatColor.RED + "Space cake", Arrays
		.asList(ChatColor.GREEN+"Makes you stoned and laughy :)"), Arrays
		.asList(), true),

	COFFEE_BEANS(Material.COCOA_BEANS, ChatColor.RED + "Coffee Beans", Arrays
		.asList(ChatColor.GREEN + "Used to make coffee!"), Arrays
		.asList(Material.COOKIE), false),
	
	COFFEE_CUP(Material.POTION, ChatColor.RED + "Coffee Cup", Arrays
		.asList(ChatColor.GREEN + "Keeps u focussed and productive!"), Arrays
		.asList(), true),
	
	WEED(Material.SEAGRASS, ChatColor.RED + "Weed", Arrays
		.asList(ChatColor.GREEN + "Snoop dogg loves this!"), Arrays
		.asList(), true),
	
	COCAINE(Material.SUGAR, ChatColor.RED + "Cocaine", Arrays
		.asList(ChatColor.GREEN + "WOAAAAAAAAAAAAHHHHHHHHHRGH!"), Arrays
		.asList(Material.CAKE), true),
	
	LSD(Material.LINGERING_POTION, ChatColor.RED + "LSD", Arrays
		.asList(ChatColor.GREEN + "Make love, not war!"), Arrays
		.asList(), true),

	ECSTASY(Material.AMETHYST_CLUSTER, ChatColor.RED + "Ecstasy", Arrays
		.asList(ChatColor.GREEN + "Artificial hapiness, but with a price."), Arrays
		.asList(Material.SPYGLASS), true),

	CHEESE(Material.YELLOW_WOOL, ChatColor.RED + "Cheese", Arrays
		.asList(ChatColor.GREEN + "Cheese for making pizza"), Arrays
		.asList(Material.YELLOW_BANNER, Material.YELLOW_BED), false),
	
	TOMATO(Material.BEETROOT, ChatColor.RED + "Tomato", Arrays
		.asList(ChatColor.GREEN + "Tomato for making pizza"), Arrays
		.asList(), false),
	
	PIZZA(Material.PUMPKIN_PIE, ChatColor.RED + "Pizza", Arrays
		.asList(ChatColor.GREEN + "Pizza for the hungry <3"), Arrays
		.asList(), false);

	private Material material; // Which original-material is the custom one
	private String displayName; // What is the name of the custom material
	private List<String> lore; // What is the lore of the custom material
	private List<Material> usedToMake; // Blacklist of materials this item is used as ingredient for
	private Boolean needsGlow; // Is it an item with effects on the player

	private ExtraItem(Material material, String displayName,
			List<String> lore, List<Material> usedToMake, Boolean needsGlow)
	{
		this.material = material;
		this.displayName = displayName;
		this.lore = lore;
		this.usedToMake = usedToMake;
		this.needsGlow = needsGlow;
	}
	
	public Material getMaterial() {
		return material;
	}

	public ItemStack getItemStack()
	{
		ItemStack itemstack = new ItemStack(material, 1);
		ItemMeta itemMeta = itemstack.getItemMeta();
		itemMeta.setDisplayName(displayName);
		itemMeta.setLore(lore);
		if(needsGlow) {
			itemMeta.addEnchant(Enchantment.LUCK, 1, false);
			itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		itemstack.setItemMeta(itemMeta);
		return itemstack;
	}

	public boolean isUsedToMakeNormally(Material material)
	{
		return usedToMake.contains(material);
	}
	
	
}


