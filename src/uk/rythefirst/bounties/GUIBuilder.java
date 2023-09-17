package uk.rythefirst.bounties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import net.md_5.bungee.api.ChatColor;

public class GUIBuilder {
	
public Inventory inv;
	
	public void buildInv() {
		
		inv = buildBInv();
		
	}
	
	public Inventory buildBInv() {
		
		Inventory Inv = Bukkit.createInventory(null, 18, "" + ChatColor.DARK_RED + ChatColor.BOLD + "Active Bounties");
		
		TreeMap<UUID,Double> bounties = DataHandler.getBounties();
		
		for(Map.Entry<UUID, Double> entry : bounties.entrySet()) {
			
			Bounty b = DataHandler.getBounty(entry.getKey());
			
			OfflinePlayer p = Bukkit.getOfflinePlayer(entry.getKey());
			
			ItemStack is = new ItemStack(Material.PLAYER_HEAD);
			SkullMeta meta = (SkullMeta) is.getItemMeta();
			meta.setOwningPlayer(p);
			meta.setDisplayName(p.getName());
			List<String> llst = new ArrayList<String>();
			
			llst.add(ChatColor.GOLD + "Value: $" + b.getValue().toString());
			llst.add(ChatColor.GOLD + "Set by: " + b.getWhoSet().getName());
			
			meta.setLore(llst);
			
			is.setItemMeta(meta);
			
			Inv.addItem(is);
			
		}
		
		return Inv;
		
	}

}
