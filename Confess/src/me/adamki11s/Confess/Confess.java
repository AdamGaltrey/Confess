package me.adamki11s.Confess;

import java.util.logging.Logger;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Confess extends JavaPlugin {

	public static PermissionHandler Permissions;
	public static Player player;
	private final double version_no = 1.1;
	Logger log = Logger.getLogger("Minecraft");
	int[] removeIDs = null;
	int amount = 0;
	public boolean permissionsEnabled = false;
	
	public void onEnable() {
		// PluginManager pm = getServer().getPluginManager();
		setupPermissions();
		log.info("[CONFESS] Confess v" + version_no + " enabled.");
	}

	public void onDisable() {
		log.info("[CONFESS] Confess " + version_no + " disabled.");
	}

	private void setupPermissions() {
		Plugin Confess = this.getServer().getPluginManager()
				.getPlugin("Permissions");

		if (this.Permissions == null) {
			if (Confess != null) {
				this.Permissions = ((Permissions) Confess).getHandler();
				log.info("[CONFESS] Permissions system detected.");
				permissionsEnabled = true;
			} else {
				log.info("[CONFESS] Permission system not detected, defaulting to OP.txt");
			}
		}
	}
	
	public Player getPlayer(){
		return null;	
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player)sender;
		if (label.equalsIgnoreCase("confess")) {
			// VARS
			if(args.length >= 2){				
				String trytry = args[1];
				Player trytrytarg = this.getServer().getPlayer(trytry);
				if(trytrytarg == null){
					sender.sendMessage(ChatColor.RED + "[CONFESS] Specified player is not online.");
					return false;
				}
			}


			int a, invcount = 0;
			// VARS
			if(permissionsEnabled){
			if (!Permissions.has(player, "confess.get") || !Permissions.has(player, "confess.*")) {
				sender.sendMessage(ChatColor.RED + "[CONFESS] You are not allowed access to this command.");
				return false;
			} else {
			if (args.length == 2 && (args[0].equalsIgnoreCase("get") || args[0].equalsIgnoreCase("show"))){
						String targString = args[1];
						Player targ = this.getServer().getPlayer(targString);

						ItemStack[] inventory = targ.getInventory()
								.getContents();

						for (a = 0; a < 36; a++) {
							if (inventory[a] != null) {
								sender.sendMessage(ChatColor.AQUA
										+ "Contents : " + ChatColor.GREEN
										+ inventory[a]);
								invcount++;
							}
						}
						sender.sendMessage(ChatColor.RED + "[CONFESS] "
								+ ChatColor.WHITE + "Total of " + invcount
								+ " items.");
						return true;
						// boolean itemcount = targ.getInventory().contains(1);
					} 
			}
			}
			
			if(!permissionsEnabled){
				if (!sender.isOp()) {
					sender.sendMessage(ChatColor.RED + "[CONFESS] You are not allowed access to this command.");
					return false;
				} else {
				if (args.length == 2 && (args[0].equalsIgnoreCase("get") || args[0].equalsIgnoreCase("show"))){
							String targString = args[1];
							Player targ = this.getServer().getPlayer(targString);

							ItemStack[] inventory = targ.getInventory()
									.getContents();

							for (a = 0; a < 36; a++) {
								if (inventory[a] != null) {
									sender.sendMessage(ChatColor.AQUA
											+ "Contents : " + ChatColor.GREEN
											+ inventory[a]);
									invcount++;
								}
							}
							sender.sendMessage(ChatColor.RED + "[CONFESS] "
									+ ChatColor.WHITE + "Total of " + invcount
									+ " items.");
							return true;
							// boolean itemcount = targ.getInventory().contains(1);
						} 
				}
				}
			
			if(permissionsEnabled){
			if (!Permissions.has(player, "confess.check") || !Permissions.has(player, "confess.*") || player.isOp() == true) {
				sender.sendMessage(ChatColor.RED + "[CONFESS] You are not allowed access to this command.");
				return false;
			} else {
					if (args.length == 2 && args[0].equalsIgnoreCase("check")) {

						String targString = args[1];

						boolean hasWater = false, hasLava = false, hasFire = false, hasTNT = false, hasBedrock = false;
						Player targCheck = this.getServer().getPlayer(
								targString);
						ItemStack[] inventoryCheck = targCheck.getInventory()
								.getContents();

						for (a = 0; a < 36; a++) {
							if (inventoryCheck[a] != null) {
								if (targCheck.getInventory().contains(8)
										|| targCheck.getInventory().contains(9)
										|| targCheck.getInventory().contains(
												326)) {
									hasWater = true;
								}
								if (targCheck.getInventory().contains(10)
										|| targCheck.getInventory()
												.contains(11)
										|| targCheck.getInventory().contains(
												327)) {
									hasLava = true;
								}
								if (targCheck.getInventory().contains(46)) {
									hasTNT = true;
								}
								if (targCheck.getInventory().contains(51)) {
									hasFire = true;
								}
								if (targCheck.getInventory().contains(7)) {
									hasBedrock = true;
								}
							}

							if (hasWater) {
								sender.sendMessage(ChatColor.RED
										+ "[CONFESS] Player : " + ChatColor.AQUA
										+ targCheck.getDisplayName()
										+ ChatColor.RED + " has " + ChatColor.AQUA
										+ "water.");
							}
							if (hasLava) {
								sender.sendMessage(ChatColor.RED
										+ "[CONFESS] Player : " + ChatColor.AQUA
										+ targCheck.getDisplayName()
										+ ChatColor.RED + " has lava.");
							}
							if (hasTNT) {
								sender.sendMessage(ChatColor.RED
										+ "[CONFESS] Player : " + ChatColor.AQUA
										+ targCheck.getDisplayName()
										+ ChatColor.RED + " has " + ChatColor.GOLD
										+ "TNT.");
							}
							if (hasFire) {
								sender.sendMessage(ChatColor.RED
										+ "[CONFESS] Player : " + ChatColor.AQUA
										+ targCheck.getDisplayName()
										+ ChatColor.RED + " has fire.");
							}
							if (hasBedrock) {
								sender.sendMessage(ChatColor.RED
										+ "[CONFESS] Player : " + ChatColor.AQUA
										+ targCheck.getDisplayName()
										+ ChatColor.RED + " has " + ChatColor.GRAY
										+ "bedrock.");
							}
							if(!hasBedrock && !hasFire && !hasTNT && !hasLava && !hasWater){
								sender.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.GOLD + "Specified player has no dangerous items :)");
							}
							return true;
						}
					}
			}
			}
			
			if(!permissionsEnabled){
				if(!sender.isOp()){
					sender.sendMessage(ChatColor.RED + "[CONFESS] You are not allowed access to this command.");
					return false;
				} else {
						if (args.length == 2 && args[0].equalsIgnoreCase("check")) {

							String targString = args[1];

							boolean hasWater = false, hasLava = false, hasFire = false, hasTNT = false, hasBedrock = false;
							Player targCheck = this.getServer().getPlayer(
									targString);
							ItemStack[] inventoryCheck = targCheck.getInventory()
									.getContents();

							for (a = 0; a < 36; a++) {
								if (inventoryCheck[a] != null) {
									if (targCheck.getInventory().contains(8)
											|| targCheck.getInventory().contains(9)
											|| targCheck.getInventory().contains(
													326)) {
										hasWater = true;
									}
									if (targCheck.getInventory().contains(10)
											|| targCheck.getInventory()
													.contains(11)
											|| targCheck.getInventory().contains(
													327)) {
										hasLava = true;
									}
									if (targCheck.getInventory().contains(46)) {
										hasTNT = true;
									}
									if (targCheck.getInventory().contains(51)) {
										hasFire = true;
									}
									if (targCheck.getInventory().contains(7)) {
										hasBedrock = true;
									}
								}

								if (hasWater) {
									sender.sendMessage(ChatColor.RED
											+ "[CONFESS] Player : " + ChatColor.AQUA
											+ targCheck.getDisplayName()
											+ ChatColor.RED + " has " + ChatColor.AQUA
											+ "water.");
								}
								if (hasLava) {
									sender.sendMessage(ChatColor.RED
											+ "[CONFESS] Player : " + ChatColor.AQUA
											+ targCheck.getDisplayName()
											+ ChatColor.RED + " has lava.");
								}
								if (hasTNT) {
									sender.sendMessage(ChatColor.RED
											+ "[CONFESS] Player : " + ChatColor.AQUA
											+ targCheck.getDisplayName()
											+ ChatColor.RED + " has " + ChatColor.GOLD
											+ "TNT.");
								}
								if (hasFire) {
									sender.sendMessage(ChatColor.RED
											+ "[CONFESS] Player : " + ChatColor.AQUA
											+ targCheck.getDisplayName()
											+ ChatColor.RED + " has fire.");
								}
								if (hasBedrock) {
									sender.sendMessage(ChatColor.RED
											+ "[CONFESS] Player : " + ChatColor.AQUA
											+ targCheck.getDisplayName()
											+ ChatColor.RED + " has " + ChatColor.GRAY
											+ "bedrock.");
								}
								if(!hasBedrock && !hasFire && !hasTNT && !hasLava && !hasWater){
									sender.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.GOLD + "Specified player has no dangerous items :)");
								}
								return true;
							}
						}
				}
				}
			
			if(permissionsEnabled){
			if (!Permissions.has(player, "confess.delete") || !Permissions.has(player, "confess.*")) {
				sender.sendMessage(ChatColor.RED + "[CONFESS] You are not allowed access to this command.");
				return false;
			} else {
						if (args.length == 3 && (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete"))) {
							Player remTarg = this.getServer()
									.getPlayer(args[1]);

							if (args[2].equalsIgnoreCase("water")) {
								remTarg.getInventory().remove(8);
								remTarg.getInventory().remove(9);
								remTarg.getInventory().remove(326);
								sender.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.AQUA + "Water Deleted Successfully");
								remTarg.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.WHITE + "Water in inventory deleted.");
								return true;
							}
							if (args[2].equalsIgnoreCase("lava")) {
								remTarg.getInventory().remove(10);
								remTarg.getInventory().remove(11);
								remTarg.getInventory().remove(327);
								sender.sendMessage(ChatColor.RED + "[CONFESS] " + "Lava Deleted Successfully");
								remTarg.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.WHITE + "Lava in inventory deleted.");
								return true;
							}
							if (args[2].equalsIgnoreCase("tnt")) {
								remTarg.getInventory().remove(46);
								sender.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.GOLD + "TNT Deleted Successfully");
								remTarg.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.WHITE + "TNT in inventory deleted.");
								return true;
							}
							if (args[2].equalsIgnoreCase("bedrock")) {
								remTarg.getInventory().remove(7);
								sender.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.GRAY + "Bedrock Deleted Successfully");
								remTarg.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.WHITE + "Bedrock in inventory deleted.");
								return true;
							}
							if (args[2].equalsIgnoreCase("fire")) {
								remTarg.getInventory().remove(51);
								sender.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.RED + "Fire Deleted Successfully");
								remTarg.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.WHITE + "Fire in inventory deleted.");
								return true;
							}
							if (args[2].equalsIgnoreCase("all")) {
								remTarg.getInventory().remove(8);
								remTarg.getInventory().remove(9);
								remTarg.getInventory().remove(326);
								remTarg.getInventory().remove(10);
								remTarg.getInventory().remove(11);
								remTarg.getInventory().remove(327);
								remTarg.getInventory().remove(46);
								remTarg.getInventory().remove(7);
								remTarg.getInventory().remove(51);
								sender.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.RED + "All Dangerous Items Deleted Successfully");
								remTarg.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.WHITE + "Dangerous Items in inventory deleted.");
								return true;
							}
						}
			}
						// END OF ARGS CHECKS
		}
		}
		
		if(!permissionsEnabled){
			if (!sender.isOp()) {
				sender.sendMessage(ChatColor.RED + "[CONFESS] You are not allowed access to this command.");
				return false;
			} else {
						if (args.length == 3 && (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete"))) {
							Player remTarg = this.getServer()
									.getPlayer(args[1]);

							if (args[2].equalsIgnoreCase("water")) {
								remTarg.getInventory().remove(8);
								remTarg.getInventory().remove(9);
								remTarg.getInventory().remove(326);
								sender.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.AQUA + "Water Deleted Successfully");
								remTarg.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.WHITE + "Water in inventory deleted.");
								return true;
							}
							if (args[2].equalsIgnoreCase("lava")) {
								remTarg.getInventory().remove(10);
								remTarg.getInventory().remove(11);
								remTarg.getInventory().remove(327);
								sender.sendMessage(ChatColor.RED + "[CONFESS] " + "Lava Deleted Successfully");
								remTarg.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.WHITE + "Lava in inventory deleted.");
								return true;
							}
							if (args[2].equalsIgnoreCase("tnt")) {
								remTarg.getInventory().remove(46);
								sender.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.GOLD + "TNT Deleted Successfully");
								remTarg.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.WHITE + "TNT in inventory deleted.");
								return true;
							}
							if (args[2].equalsIgnoreCase("bedrock")) {
								remTarg.getInventory().remove(7);
								sender.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.GRAY + "Bedrock Deleted Successfully");
								remTarg.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.WHITE + "Bedrock in inventory deleted.");
								return true;
							}
							if (args[2].equalsIgnoreCase("fire")) {
								remTarg.getInventory().remove(51);
								sender.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.RED + "Fire Deleted Successfully");
								remTarg.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.WHITE + "Fire in inventory deleted.");
								return true;
							}
							if (args[2].equalsIgnoreCase("all")) {
								remTarg.getInventory().remove(8);
								remTarg.getInventory().remove(9);
								remTarg.getInventory().remove(326);
								remTarg.getInventory().remove(10);
								remTarg.getInventory().remove(11);
								remTarg.getInventory().remove(327);
								remTarg.getInventory().remove(46);
								remTarg.getInventory().remove(7);
								remTarg.getInventory().remove(51);
								sender.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.RED + "All Dangerous Items Deleted Successfully");
								remTarg.sendMessage(ChatColor.RED + "[CONFESS] " + ChatColor.WHITE + "Dangerous Items in inventory deleted.");
								return true;
							}
						}
			}
						// END OF ARGS CHECKS
		}
	
		
		
		return false;
	}

}
