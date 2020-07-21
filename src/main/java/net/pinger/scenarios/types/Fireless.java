package net.pinger.scenarios.types;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.Scenarios;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.LinkedList;
import java.util.List;

public class Fireless extends Scenario {

	public Fireless(Scenarios scenarios) {
		super(scenarios);
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		final EntityDamageEvent.DamageCause cause = e.getCause();
		if (cause.equals(EntityDamageEvent.DamageCause.LAVA) || cause.equals(EntityDamageEvent.DamageCause.FIRE) || cause.equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
			e.setCancelled(true);
		}
	}

	@Override
	public String getName() {
		return "Fireless";
	}

	@Override
	public int getId() {
		return 6;
	}

	@Override
	public Material getMaterial() {
		return Material.FIRE;
	}

	@Override
	public List<String> getExplanation() {
		List<String> explain = new LinkedList<>();
		explain.add("");
		explain.add(ChatColor.YELLOW + "Explanation: ");
		explain.add(ChatColor.BLUE + " - Fire is disabled in overworld and works in nether");
		return explain;
	}
}
