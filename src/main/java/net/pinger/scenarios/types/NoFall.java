package net.pinger.scenarios.types;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.Scenarios;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.LinkedList;
import java.util.List;

public class NoFall extends Scenario {

	public NoFall(Scenarios scenarios) {
		super(scenarios);
	}

	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		Entity entity = event.getEntity();

		if (entity instanceof Player) {
			if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
				event.setCancelled(true);
			}
		}
	}

	@Override
	public String getName() {
		return "NoFall";
	}

	@Override
	public int getId() {
		return 8;
	}

	@Override
	public Material getMaterial() {
		return Material.DIAMOND_BOOTS;
	}

	@Override
	public List<String> getExplanation() {
		List<String> explain = new LinkedList<>();
		explain.add("");
		explain.add(ChatColor.YELLOW + "Explanation: ");
		explain.add(ChatColor.BLUE + " - You cannot take any fall damage");
		return explain;
	}
}
