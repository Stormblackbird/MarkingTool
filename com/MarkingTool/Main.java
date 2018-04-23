/**
 *Made by: Stormblackbird
 *Version: 1.0.1
 *By order of: Hyperion Parks
 */

package com.MarkingTool;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import com.MarkingTool.PointCalculator;

public class Main extends JavaPlugin implements Listener{
	
	ArrayList listenForPlayers = new ArrayList();
	PointCalculator calculate = new PointCalculator(this);
	
	double length;
	double angle;
	double oldX;
	double oldZ;
	double y;
	
	@Override
	public void onEnable() {
		getLogger().info("Plugin enabled.");
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Plugin disabled.");
	}
	

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("MarkingTool")) {
			if(sender.hasPermission("markingtool")) {
			if(args.length == 3) {
				if(args[0].equalsIgnoreCase("Calculate") ) {
					
					listenForPlayers.add(sender.getName());
					sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "[Marking Tool]" + ChatColor.GOLD + " Right click the first point");
					
					length = Double.parseDouble(args[1]);
					angle = Double.parseDouble(args[2]);
					
					return true;
				}
				else {
					sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "[Marking Tool]" + ChatColor.GOLD + " Usage: /MarkingTool Calculate [length] [angle].");
				}
			//x = getX() + length*sin(angle/360*2*math.PI);
			//z = getZ() + length*cos((angle + 180)/360*2*math.PI);
				return true;
			}
			else {
				sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "[Marking Tool]" + ChatColor.GOLD + " Usage: /MarkingTool Calculate [length] [angle].");
			}
			return true;
		}
		else {
			sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "[Marking Tool]" + ChatColor.GOLD + " You do not have the permission to use this command!");
		}
			
		return true;
		}
		
		else return false;
	}
	
	@EventHandler
	public void blockClick(PlayerInteractEvent e) {
		if (listenForPlayers.contains(e.getPlayer().getName())) {
			Block b = e.getClickedBlock();
			oldX = b.getLocation().getX();
			oldZ = b.getLocation().getZ();
			y = b.getLocation().getY();
			
			double newX = Math.round(calculate.calculateX(oldX,angle,length));
			double newZ = Math.round(calculate.calculateZ(oldZ, angle, length));
			
			Location loc = new Location(e.getPlayer().getWorld(), newX, y, newZ);
			
			loc.getBlock().setType(b.getType());
			
			listenForPlayers.remove(e.getPlayer().getName());
			e.getPlayer().sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "[Marking Tool]" + ChatColor.GOLD + " The calculated point is: " + newX + ", " + y + ", " + newZ);
		}
	}
}
