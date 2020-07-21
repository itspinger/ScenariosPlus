package net.pinger.scenarios.events;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.Scenarios;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ClickEvent implements Listener {

    private final Scenarios scenarios;

    public ClickEvent(Scenarios scenarios) {
        this.scenarios = scenarios;
    }

    @EventHandler
    public void onClick(org.bukkit.event.inventory.InventoryClickEvent e) {
    	if (e.getCurrentItem().getType() == Material.AIR) {
    		return;
	    }

        if (e.getClickedInventory().getName().equalsIgnoreCase(ChatColor.AQUA + "Scenarios")) {
            e.setCancelled(true);
            for (Scenario scenario : scenarios.getScenarioManager().getScenarios()) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + ChatColor.BOLD.toString() + scenario.getName())) {
                    if (!scenario.isEnabled()) {
                        scenario.enable();
                    } else {
                        scenario.disable();
                    }
                }
            }
        }
    }
}
