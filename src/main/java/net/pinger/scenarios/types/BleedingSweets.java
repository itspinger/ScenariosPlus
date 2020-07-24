package net.pinger.scenarios.types;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.Scenarios;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BleedingSweets extends Scenario {

    public BleedingSweets(Scenarios scenarios) {
        super(scenarios);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        List<ItemStack> stacks = new ArrayList<>();
        stacks.add(new ItemStack(Material.DIAMOND));
        stacks.add(new ItemStack(Material.GOLD_INGOT, 5));
        stacks.add(new ItemStack(Material.BOOK));
        stacks.add(new ItemStack(Material.STRING));
        stacks.add(new ItemStack(Material.ARROW, 16));

        event.getDrops().addAll(stacks);
    }

    @Override
    public String getName() {
        return "Bleeding Sweets";
    }

    @Override
    public int getId() {
        return 15;
    }

    @Override
    public Material getMaterial() {
        return Material.GOLDEN_APPLE;
    }

    @Override
    public List<String> getExplanation() {
        List<String> explain = new LinkedList<>();
        explain.add("");
        explain.add(ChatColor.YELLOW + "Explanation: ");
        explain.add(ChatColor.BLUE + " - Player drops a diamond, 5 gold, a book, 1 string and 16 arrows on death.");
        return explain;
    }
}
