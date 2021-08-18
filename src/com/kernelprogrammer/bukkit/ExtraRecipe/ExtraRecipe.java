package com.kernelprogrammer.bukkit.ExtraRecipe;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.NamespacedKey;
import org.bukkit.WorldCreator;

/**
 * Drug cooking plugin for Bukkit
 *
 * @author userlandkernel
 */
public class ExtraRecipe extends JavaPlugin implements Listener {

	public ShapelessCustomRecipe pizzaRecipe = null;
	private NamespacedKey cokeRecipeKey;
	private NamespacedKey weedRecipeKey;
	private NamespacedKey lsdRecipeKey;
	private NamespacedKey mdmaRecipeKey;
	private NamespacedKey coffeeRecipeKey;
	private NamespacedKey coffeeBeansRecipeKey;
	public static Location marsSpawn = null;

	@Override
	public void onDisable() {
		getLogger().info("ExtraRecipe has now been disabled.");
	}

	private void createWorlds() {
        if (!(Bukkit.getWorld("mars") != null)) {
            WorldCreator creator = new WorldCreator("mars");
            creator.generator(new Mars());
            creator.createWorld();
        }
    }

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		
		// Log version
		PluginDescriptionFile pdfFile = this.getDescription();
		getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );

		// Load mars
		getLogger().info("Loading / Creating Mars...");
		createWorlds();
		marsSpawn = new Location(Bukkit.getWorld("mars"), 0, 128, 0);

		// Listen for events
		getLogger().info("Registering extra recipes...");
		getServer().getPluginManager().registerEvents(this, this);

		getServer().getPluginManager().registerEvents(new NoBoomListener(), (Plugin)this);
		getCommand("drugs").setExecutor(new SemsDrugsCommand());
		getCommand("workbench").setExecutor(new WorkbenchCommand());
		TeleportAddressCommand tpaCMD = new TeleportAddressCommand();
		getCommand("tpaddress").setExecutor(tpaCMD);

		// MDMA
		getLogger().info("Crafting MDMA recipe...");
		mdmaRecipeKey = new NamespacedKey(this, "MDMARecipe");
		ItemStack ecstasy = ExtraItem.ECSTASY.getItemStack();
		ShapedRecipe ecstasyRecipe = new ShapedRecipe(mdmaRecipeKey, ecstasy);
		ecstasyRecipe.shape("BBB","WEW","BBB");
		ecstasyRecipe.setIngredient('B', Material.COCOA_BEANS);
		ecstasyRecipe.setIngredient('E', Material.FERMENTED_SPIDER_EYE);
		ecstasyRecipe.setIngredient('W', Material.WATER_BUCKET);
		getServer().addRecipe(ecstasyRecipe);


		// LSD
		getLogger().info("Crafting LSD recipe...");
		lsdRecipeKey = new NamespacedKey(this, "LSDRecipe");
		ItemStack lsdPotion = ExtraItem.LSD.getItemStack();
		ShapedRecipe lsdRecipe = new ShapedRecipe(lsdRecipeKey, lsdPotion);
		lsdRecipe.shape("SDS"," X ","BBB");
		lsdRecipe.setIngredient('S', Material.SPIDER_EYE);
		lsdRecipe.setIngredient('D', Material.DANDELION);
		lsdRecipe.setIngredient('X', Material.BLAZE_POWDER);
		lsdRecipe.setIngredient('B', Material.BLUE_ORCHID);
		getServer().addRecipe(lsdRecipe);

		// COKE
		cokeRecipeKey = new NamespacedKey(this, "CokeRecipe");
		getLogger().info("Crafting COKE recipe...");
		ItemStack cocaine = ExtraItem.COCAINE.getItemStack();
		ShapedRecipe cokeRecipe = new ShapedRecipe(cokeRecipeKey, cocaine);
		cokeRecipe.shape(" S ", " S ", " S ");
		cokeRecipe.setIngredient('S', Material.SUGAR);
		getServer().addRecipe(cokeRecipe);

		// WEED
		weedRecipeKey = new NamespacedKey(this, "WeedRecipe");
		getLogger().info("Crafting WEED recipe...");
		ItemStack weed = ExtraItem.WEED.getItemStack();
		ItemMeta weedMeta = weed.getItemMeta();
		weedMeta.setDisplayName("Weed");
		weed.setItemMeta(weedMeta);
		ShapedRecipe weedRecipe = new ShapedRecipe(weedRecipeKey, weed);
		weedRecipe.shape(" W ", " W ", " W ");
		weedRecipe.setIngredient('W', Material.WHEAT);
		getServer().addRecipe(weedRecipe);

		// Coffee beans
		coffeeBeansRecipeKey = new NamespacedKey(this, "CoffeeBeansRecipe");
		getLogger().info("Crafting COFFEE BEANS recipe...");
		ItemStack coffeeBeans = ExtraItem.COFFEE_BEANS.getItemStack();
		ShapelessRecipe coffeeBeansRecipe = new ShapelessRecipe(coffeeBeansRecipeKey, coffeeBeans);
		coffeeBeansRecipe.addIngredient(1, Material.COCOA_BEANS);
		getServer().addRecipe(coffeeBeansRecipe);
		
		// Coffee beans
		coffeeRecipeKey = new NamespacedKey(this, "CoffeeRecipe");
		getLogger().info("Crafting COFFEE CUP recipe...");
		ItemStack coffeeCup = ExtraItem.COFFEE_CUP.getItemStack();
		ShapelessCustomRecipe coffeeCupRecipe = new ShapelessCustomRecipe(coffeeRecipeKey, coffeeCup);
		coffeeCupRecipe.addCustomIngredient(8, ExtraItem.COFFEE_BEANS);
		coffeeCupRecipe.addIngredient(1, Material.GLASS_BOTTLE);
		getServer().addRecipe(coffeeCupRecipe);

		// Cheese
		getLogger().info("Crafting CHEESE recipe...");
		ItemStack cheese = ExtraItem.CHEESE.getItemStack();
		ShapelessRecipe cheeseRecipe = new ShapelessRecipe(cheese);
		cheeseRecipe.addIngredient(3, Material.MILK_BUCKET);
		getServer().addRecipe(cheeseRecipe);

		// Tomato
		getLogger().info("Crafting TOMATO recipe...");
		ItemStack tomato = ExtraItem.TOMATO.getItemStack();
		ShapedRecipe tomatoRecipe = new ShapedRecipe(tomato);
		tomatoRecipe.shape(" B ", "B B", " B ");
		tomatoRecipe.setIngredient('B', Material.BEETROOT);
		getServer().addRecipe(tomatoRecipe);

		// Pizza 
		getLogger().info("Crafting Pizza recipe...");
		ItemStack pizza = ExtraItem.PIZZA.getItemStack();
		pizzaRecipe = new ShapelessCustomRecipe(pizza);
		pizzaRecipe.addCustomIngredient(4, ExtraItem.CHEESE);
		pizzaRecipe.addCustomIngredient(4, ExtraItem.TOMATO);
		pizzaRecipe.addIngredient(1, Material.BREAD);
		getServer().addRecipe(pizzaRecipe);
	}

	@EventHandler// (priority = EventPriority.LOWEST)
	public void playerRenameItem(InventoryClickEvent event){
		if (event.getView().getType() == InventoryType.ANVIL) {
			if(ExtraItemManager.isCustomItem(event.getView().getItem(0)) != null) {
				getLogger().info("Player attempted to use custom item in an anvil!");
				event.setResult(Result.DENY);
			}
		}
	}

	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {

		ItemStack consumed = e.getItem();
		Action action = e.getAction();
		Player consumer = e.getPlayer();

		if(null == consumed)
		{
			return;
		}
		
		if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK) ) {

			ExtraItem customItem = ExtraItemManager.isCustomItem(consumed);
			if(customItem != null) {
				ExtraItemManager.triggerEffect(this, consumer, customItem);
				ItemStack item = consumer.getInventory().getItemInHand();
				if(item.getAmount() == 1) {
					item = new ItemStack(Material.AIR, 1);
				}
				else {
					item.setAmount(item.getAmount() - 1);
				}
				consumer.setItemInHand(item);
				consumer.updateInventory();
			}

			if(action.equals(Action.RIGHT_CLICK_BLOCK)) {
				Spaceship ship = new Spaceship();
				ship.fly(e);
			}

		}
		
		
	}

	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player p = event.getPlayer();
		p.discoverRecipe(this.cokeRecipeKey);
		p.discoverRecipe(this.weedRecipeKey);
		p.discoverRecipe(this.coffeeBeansRecipeKey);
		p.discoverRecipe(this.coffeeRecipeKey);
		p.discoverRecipe(this.lsdRecipeKey);
		p.discoverRecipe(this.mdmaRecipeKey);
    }

	
	@EventHandler
	public void onCraftItem(CraftItemEvent event)
	{
		// Recipe
		Recipe recipe = event.getRecipe();
		
		ExtraItem extraItems[] = ExtraItem.values(); 
		for (int i = 0; i <  event.getInventory().getContents().length; i++)
		{
			ItemStack itemstack = event.getInventory().getContents()[i];
			
			for(ExtraItem extraItem : extraItems) {

				if (itemstack.isSimilar(extraItem.getItemStack()))
				{
					if (extraItem.isUsedToMakeNormally(recipe.getResult()
							.getType()))
					{
						event.setResult(Result.DENY);
						getLogger().info("Player attempted to use custom item's normal material to make a default bukkit item out of the material!");
					}
				}
				
				// Custom recipe check!
				if (recipe.getClass() == ShapelessCustomRecipe.class) {
					
					getLogger().info("CUSTOM RECIPE!");
					ShapelessCustomRecipe customRecipe = (ShapelessCustomRecipe)recipe;
					if(customRecipe.customIngredients.get(i) != null) {
						
						if (ExtraItemManager.isCustomItem(itemstack) == null) {
							event.setResult(Result.DENY);
							getLogger().info("Player attempted to use normal item in place of custom material!");
						}
						
					}
					else {
						
					}
				}
				
			}
			
			
		}
		
		
	}

}
