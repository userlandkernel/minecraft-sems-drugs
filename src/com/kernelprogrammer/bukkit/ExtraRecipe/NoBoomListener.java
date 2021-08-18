package com.kernelprogrammer.bukkit.ExtraRecipe;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.html.parser.Entity;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class NoBoomListener implements Listener {
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {
		event.getPlayer().sendMessage(ChatColor.GREEN + "AntiBoom Enabled on this server!");
	}
  
	@EventHandler
	public void CreepernoBoom(ExplosionPrimeEvent event) {
	  if (event.getEntityType() == EntityType.CREEPER)
		event.setCancelled(true); 
	}
	
	@EventHandler
	public void GhastnoBoom(ExplosionPrimeEvent event) {
	  if (event.getEntityType() == EntityType.FIREBALL)
		event.setCancelled(true); 
	}
}
