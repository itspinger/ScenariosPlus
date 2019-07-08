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

import java.util.ArrayList;
import java.util.List;

public class Scenarios extends JavaPlugin {

    @Getter private ScenarioManager scenarioManager;

    @Override
    public void onEnable() {
        this.scenarioManager = new ScenarioManager(this);
        Bukkit.getPluginManager().registerEvents(new ClickEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new ScenarioEvent(), this);
        getCommand("scenarios").setExecutor(new ScenarioCommand(this));
    }

    public void openScenariosInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, ChatColor.AQUA + "Scenarios");

        for (Scenario scenario : this.scenarioManager.getScenarios()) {
            List<String> lore = new ArrayList<>();
            if (scenario.isEnabled()) {
                lore.add(ChatColor.GREEN + "Enabled");
            } else {
                lore.add(ChatColor.RED + "Disabled");
            }

            lore.addAll(scenario.getExplanation());
            inventory.addItem(itemStack(scenario.getMaterial(), lore, scenario.getName()));
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

}
