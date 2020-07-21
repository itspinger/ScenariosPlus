package net.pinger.scenarios.types;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.Scenarios;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NoClean extends Scenario {

	private List<UUID> players;

	public NoClean(Scenarios scenarios) {
		super(scenarios);
		this.players = new ArrayList<>();
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Entity killer = event.getEntity().getKiller();

		if (killer != null) {
			Player player = (Player) killer;
			UUID uuid = player.getUniqueId();

			players.add(uuid);
			new BukkitRunnable() {
				@Override
				public void run() {
					players.remove(uuid);
				}
			}.runTaskLater(this.scenarios, 30 * 20L);

			final int[] timer = {30};
			player.sendMessage(ChatColor.GREEN + "Your NoClean protection expires in " + ChatColor.YELLOW + timer[0] + ChatColor.GREEN + " seconds");
			new BukkitRunnable() {
				@Override
				public void run() {
					if (players.contains(uuid)) {
						timer[0] = timer[0] - 5;
						player.sendMessage(ChatColor.GREEN + "Your NoClean protection expires in " + ChatColor.YELLOW + timer[0] + ChatColor.GREEN + " seconds");
					} else {
						cancel();
					}
				}
			}.runTaskTimer(this.scenarios, 100L, 100L);
		}
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		Entity damaged = event.getEntity();
		Entity damager = event.getDamager();

		if (damaged instanceof Player) {
			Player player = (Player) damaged;

			if (this.players.contains(player.getUniqueId())) {
				event.setCancelled(true);
				if (damager instanceof Player) {
					Player dmg = (Player) damager;

					dmg.sendMessage(ChatColor.RED + "This player is under the protection.");
				}
			}
		}

		if (damager instanceof Player) {
			Player player = (Player) damager;

			this.players.remove(player.getUniqueId());
		}

	}

	@Override
	public String getName() {
		return "NoClean";
	}

	@Override
	public int getId() {
		return 7;
	}

	@Override
	public Material getMaterial() {
		return Material.DIAMOND_SWORD;
	}

	@Override
	public List<String> getExplanation() {
		List<String> explain = new ArrayList<>();
		explain.add("");
		explain.add(ChatColor.YELLOW + "Explanation: ");
		explain.add(ChatColor.BLUE + " - Every time you kill a person you gain 30 seconds of Resistance");
		explain.add(ChatColor.BLUE + " - Trying to damage users will take your NoClean away");
		return explain;
	}
}
