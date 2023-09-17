package uk.rythefirst.bounties;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Chat {
	
public static String prefixFormatted = ChatColor.GOLD + "[" + ChatColor.DARK_AQUA + "BT" + ChatColor.GOLD + "] ";
	
	public static void SendCenteredServerChat(String message) {
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			
			Messages.sendCenteredMessage(p, "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Bounties");
			Messages.sendCenteredMessage(p, "");
			Messages.sendCenteredMessage(p, ChatColor.DARK_PURPLE + message);
			
		}
		
	}

}
