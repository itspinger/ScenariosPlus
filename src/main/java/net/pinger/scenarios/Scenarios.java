package net.pinger.scenarios;

import lombok.Getter;
import net.pinger.scenarios.commands.ScenarioCommand;
import net.pinger.scenarios.events.ClickEvent;
import net.pinger.scenarios.events.ScenarioEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;
import java.util.List;

public class Scenarios extends JavaPlugin {

    private ScenarioManager scenarioManager;

    @Override
    public void onEnable() {
        this.scenarioManager = new ScenarioManager(this);
        Bukkit.getPluginManager().registerEvents(new ClickEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new ScenarioEvent(), this);
        getCommand("scenarios").setExecutor(new ScenarioCommand(this));
    }

    public void openScenariosInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 18, ChatColor.AQUA + "Scenarios");
        new BukkitRunnable() {
            @Override
            public void run() {
                inventory.clear();

                if (!player.getOpenInventory().getTitle().equals(inventory.getTitle())) {
                    cancel();
                }

                for (Scenario scenario : scenarioManager.getScenarios()) {
                    List<String> lore = new ArrayList<>();
                    if (scenario.isEnabled()) {
                        lore.add(ChatColor.GREEN + "Enabled");
                    } else {
                        lore.add(ChatColor.RED + "Disabled");
                    }

                    lore.addAll(scenario.getExplanation());
                    inventory.addItem(itemStack(scenario.getMaterial(), lore, scenario.getName()));
                }
            }
        }.runTaskTimer(this, 0, 1L);

        player.openInventory(inventory);
    }

    public void openEnabledScenarios(Player player) {
    	Inventory inventory = Bukkit.createInventory(null, 9, ChatColor.AQUA + "Enabled Scenarios");

    	for (Scenario scenario : this.scenarioManager.getEnabledScenarios()) {
    		inventory.addItem(itemStack(scenario.getMaterial(), scenario.getExplanation(), scenario.getName()));
	    }

	    player.openInventory(inventory);
    }

    public ItemStack itemStack(Material material, List<String> lore, String name) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + name);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ScenarioManager getScenarioManager() {
        return scenarioManager;
    }
}
