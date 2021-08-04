package net.pinger.scenarios.implementations;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.api.ScenariosAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CCScenario implements Scenario {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        Player p = e.getPlayer();

        if (ScenariosAPI.isScenarioEnabled("Triple Ores") || ScenariosAPI.isScenarioEnabled("Double Ores")) {
            return;
        }

        if (block.getType() == Material.IRON_ORE) {
//            if (scenarios.getPlayerCache().hasPassedLimit(p.getUniqueId(), block.getType())) return;

            e.setCancelled(true);
            block.setType(Material.AIR);
            block.getState().update();
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.IRON_INGOT));
            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class).setExperience(new Random().nextInt(4));
        } else if (block.getType() == Material.GOLD_ORE && ScenariosAPI.isScenarioEnabled(2)) {
//            if (scenarios.getPlayerCache().hasPassedLimit(p.getUniqueId(), block.getType())) return;

            e.setCancelled(true);
            block.setType(Material.AIR);
            block.getState().update();
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GOLD_INGOT));
            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class).setExperience(new Random().nextInt(6));

        } else if (block.getType() == Material.GRAVEL) {
            e.setCancelled(true);
            block.setType(Material.AIR);
            block.getState().update();
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.FLINT));
        }
    }

    @Override
    public String getName() {
        return "CutClean";
    }

    @Override
    public Material getMaterial() {
        return Material.IRON_INGOT;
    }

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public List<String> getExplanation() {
        List<String> explain = new LinkedList<>();
        explain.add(ChatColor.BLUE + " - Ores are 100% smelted");
        explain.add(ChatColor.BLUE + " - Food is 100% cooked");
        explain.add(ChatColor.BLUE + " - No furnace needed");
        explain.add(ChatColor.BLUE + " - Flint/Apple rates are 100%");
        return explain;
    }
}
