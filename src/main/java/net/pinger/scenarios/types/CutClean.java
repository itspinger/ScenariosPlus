package net.pinger.scenarios.types;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.Scenarios;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CutClean extends Scenario {

    public CutClean(Scenarios scenarios) {
        super(scenarios);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();

        if (block.getType() == Material.IRON_ORE) {
            e.setCancelled(true);
            block.setType(Material.AIR);
            block.getState().update();
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.IRON_INGOT));
            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class).setExperience(new Random().nextInt(4));

        } else if (block.getType() == Material.GOLD_ORE && !this.scenarios.getScenarioManager().getScenarioById(2).isEnabled()) {
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

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Cow) {
            e.getDrops().clear();
            e.getDrops().add(new ItemStack(Material.COOKED_BEEF, 3));
            e.getDrops().add(new ItemStack(Material.LEATHER, 1));
        } else if (e.getEntity() instanceof Chicken) {
            e.getDrops().clear();
            e.getDrops().add(new ItemStack(Material.COOKED_CHICKEN, 3));
        } else if (e.getEntity() instanceof Horse) {
            e.getDrops().clear();
            e.getDrops().add(new ItemStack(Material.LEATHER, 1));
        } else if (e.getEntity() instanceof Pig) {
            e.getDrops().clear();
            e.getDrops().add(new ItemStack(Material.GRILLED_PORK, 3));
        }
    }

    @Override
    public String getName() {
        return "CutClean";
    }

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public Material getMaterial() {
        return Material.IRON_INGOT;
    }

    @Override
    public List<String> getExplanation() {
        List<String> explain = new ArrayList<>();
        explain.add("");
        explain.add(ChatColor.YELLOW + "Explanation: ");
        explain.add(ChatColor.BLUE + " - Ores are 100% smelted");
        explain.add(ChatColor.BLUE + " - Food is 100% cooked");
        explain.add(ChatColor.BLUE + " - No furnace needed");
        explain.add(ChatColor.BLUE + " - Flint/Apple rates are 100%");
        return explain;
    }


}
