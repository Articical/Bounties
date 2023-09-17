package uk.rythefirst.bounties;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BountiesCMD implements CommandExecutor{
	
	public static HashMap<Player, Inventory> binvp = new HashMap<Player, Inventory>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd,  String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			return false;
		}
		
		Player p = (Player) sender;
		
		GUIBuilder builder = new GUIBuilder();
		
		builder.buildInv();
		
		Inventory bInv = builder.inv;
		
		if(binvp.containsKey(p)) {
			binvp.remove(p);
		}
		
		binvp.put(p, bInv);
		
		p.openInventory(bInv);
		
		return true;
		
	}

}
