package net.pinger.scenarios.events;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.events.custom.ScenarioUpdateEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ScenarioEvent implements Listener {

	@EventHandler
	public void onEvent(ScenarioUpdateEvent event) {
		Scenario scenario = event.getScenario();
		String option = scenario.isEnabled() ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled";
		Bukkit.broadcastMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "SCENARIOS » " + ChatColor.AQUA + scenario.getName() + ChatColor.GOLD + " has been " + option);
	}

}
