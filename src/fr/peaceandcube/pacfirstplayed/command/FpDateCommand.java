package fr.peaceandcube.pacfirstplayed.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;

public class FpDateCommand implements CommandExecutor, TabExecutor {
	public FileConfiguration config = Bukkit.getPluginManager().getPlugin("PACFirstPlayed").getConfig();
	public String playersOnDate = config.getString("players_on_date");
	public String noneOnDate = config.getString("none_on_date");
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 1 && sender.hasPermission("firstplayed.date")) {
			String playerNames = "";
			int playerCount = 0;
			
			for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
				long timestamp = player.getFirstPlayed();
				String joinDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date(timestamp));
				
				if (joinDate.equals(args[0])) {
					playerNames += ChatColor.YELLOW + player.getName() + " ";
					playerCount++;
				}
			}
			
			if (!playerNames.isEmpty()) {
				sender.sendMessage(String.format(ChatColor.LIGHT_PURPLE + this.playersOnDate, playerCount, playerNames));
			} else {
				sender.sendMessage(ChatColor.RED + this.noneOnDate);
			}
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		return new ArrayList<>();
	}
}
