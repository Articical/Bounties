package uk.rythefirst.bounties;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin{
	
	public static Plugin instance;
	
	private static  Economy econ;
    private static Permission perms;
    private static Chat chat;
	
	public void onEnable() {
		
		instance = this;
		
		//Setup Vault
		if (!setupEconomy()) {
		    this.getLogger().severe("Disabled due to no Vault dependency found!");
		    Bukkit.getPluginManager().disablePlugin(this);
		    return;
		}
		this.setupPermissions();
		this.setupChat();
		
		this.getCommand("bounties").setExecutor(new BountiesCMD());
		this.getCommand("setbounty").setExecutor(new SetbountyCMD());
		
	}
	
	private boolean setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Economy getEconomy() {
        return econ;
    }
    
    public static Permission getPermissions() {
        return perms;
    }
    
    public static Chat getChat() {
        return chat;
    }

}
