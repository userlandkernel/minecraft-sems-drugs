package com.kernelprogrammer.bukkit.ExtraRecipe;
import java.util.logging.Logger;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreakDoorEvent;

public class SecureDoor implements Listener{
	private static final Logger log = Logger.getLogger("Minecraft");

	@EventHandler(priority = EventPriority.NORMAL) 
	public void onEntityBreakDoor(EntityBreakDoorEvent event) {
		
		Block blockDoor = event.getBlock();
		Material doorType = blockDoor.getType();
		if(doorType == Material.WARPED_DOOR || doorType == Material.CRIMSON_DOOR && event.getEntityType() != EntityType.PLAYER) {
			//log.info("[NoBreakDoor] " + event.getEntityType().toString() + " tried to break a door at "+ event.getBlock().getWorld().getName() + ":" + 
          	//event.getBlock().getX() + "," + event.getBlock().getY() + "," + event.getBlock().getZ());
			event.setCancelled(true);
			return;
		}
	}
}