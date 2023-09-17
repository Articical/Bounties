package uk.rythefirst.bounties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DataHandler {
	
	private static TreeMap<UUID, Double> bountyLst = new TreeMap<UUID, Double>();
	private static TreeMap<UUID, UUID> setterLst = new TreeMap<UUID, UUID>();
	
	public static TreeMap<UUID,Double> getBounties() {
		
		return bountyLst;
		
	}
	
	@SuppressWarnings("unchecked")
	public static void loadBounties() {
		
		Plugin pl = Main.instance;
		
		List<String> bties = (List<String>) pl.getConfig().getList("bountieslst");
		
		List<String> setters = (List<String>) pl.getConfig().getList("bsetters");
		
		for(int i=0; i < bties.size(); i++) {
			
			String[] strblst = bties.get(i).split("#@#");
			
			bountyLst.put(UUID.fromString(strblst[0]), Double.parseDouble(strblst[1]));
			
		}
		
		for(int i=0; i < setters.size();i++) {
			
			String[] setLst = setters.get(i).split("#@#");
			
			setterLst.put(UUID.fromString(setLst[0]), UUID.fromString(setLst[1]));
			
		}
		
		Bukkit.getLogger().log(Level.INFO, "Bounties Loaded!");
		
	}
	
	public static void saveBounties() {
		
		Plugin pl = Main.instance;
		
		ArrayList<String> bties = new ArrayList<String>();
		
		ArrayList<String> setters = new ArrayList<String>();
		
		for(Map.Entry<UUID,Double> entry : bountyLst.entrySet()) {
			
			String strbs = entry.getKey().toString() + "#@#" + entry.getValue().toString();
			
			bties.add(strbs);
			
		}
		
		for(Map.Entry<UUID, UUID> entry : setterLst.entrySet()) {
			
			String setterS = entry.getKey().toString() + "#@#" + entry.getValue().toString();
			
			setters.add(setterS);
			
		}
		
		pl.getConfig().set("bountieslst", bties);
		pl.getConfig().set("bsetters", setters);
		
		Main.instance.saveConfig();
		
	}
	
public static boolean setBounty(Player target, Player setter, Double amount) {
		
		if(!(bountyLst.size() <=18)) {
			return false;
		}
		
		setterLst.put(target.getUniqueId(), setter.getUniqueId());
		
		bountyLst.put(target.getUniqueId(), amount);
		
		saveBounties();
		
		return true;
		
	}
	
	public static Boolean hasBounty(UUID uuid) {
		
		return bountyLst.containsKey(uuid);
	}
	
	public static void RedeemBounty(Player killer, Bounty bounty) {
		
	}
	
	public static void RemoveBounty(Player p) {
		
		if(bountyLst.containsKey(p.getUniqueId())){
			
			bountyLst.remove(p.getUniqueId());
			
			setterLst.remove(p.getUniqueId());
			
		}
		
	}
	
	public static Bounty getBounty(UUID uuid) {
		
		if(!hasBounty(uuid)) {
			
			Player pl = Bukkit.getPlayer(uuid);
			Bounty bounty = new Bounty(pl,false,0,null);
			
			return bounty;
		}
		
		OfflinePlayer setter = Bukkit.getOfflinePlayer(setterLst.get(uuid));
		
		Double value = bountyLst.get(uuid);
		
		OfflinePlayer pl = Bukkit.getOfflinePlayer(uuid);
		
		Bounty bounty = new Bounty(pl,true,value,setter);
		
		return bounty;
	}

}
