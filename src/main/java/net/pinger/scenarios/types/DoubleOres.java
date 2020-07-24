package net.pinger.scenarios.types;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.Scenarios;
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

public class DoubleOres extends Scenario {

    public DoubleOres(Scenarios scenarios) {
        super(scenarios);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player p = event.getPlayer();

        if (scenarios.getScenarioManager().getScenarioByName("Triple Ores").isEnabled()) {
            return;
        }

        if (block.getType() == Material.DIAMOND_ORE) {
            if (scenarios.getPlayerCache().hasPassedLimit(p.getUniqueId(), block.getType())) return;

            event.setCancelled(true);

            block.setType(Material.AIR);
            block.getState().update();

            if (!scenarios.getScenarioManager().getScenarioByName("Diamondless").isEnabled()) {
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.DIAMOND, 2));
            }

            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class).setExperience(new Random().nextInt(12) + 3);
        } else if (block.getType() == Material.GOLD_ORE) {
            if (scenarios.getPlayerCache().hasPassedLimit(p.getUniqueId(), block.getType())) return;

            event.setCancelled(true);

            block.setType(Material.AIR);
            block.getState().update();

            if (!scenarios.getScenarioManager().getScenarioByName("Goldless").isEnabled()) {
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GOLD_INGOT, 2));
            }

            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class).setExperience(new Random().nextInt(9) + 3);
        } else if (block.getType() == Material.IRON_ORE) {
            if (scenarios.getPlayerCache().hasPassedLimit(p.getUniqueId(), block.getType())) return;

            event.setCancelled(true);

            block.setType(Material.AIR);
            block.getState().update();
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.IRON_INGOT, 2));
            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class).setExperience(new Random().nextInt(5) + 3);
        } else if (block.getType() == Material.COAL_ORE) {
            event.setCancelled(true);

            block.setType(Material.AIR);
            block.getState().update();
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.COAL, 2));
            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class).setExperience(new Random().nextInt(4) + 3);
        }
    }

    @Override
    public String getName() {
        return "Double Ores";
    }

    @Override
    public int getId() {
        return 18;
    }

    @Override
    public Material getMaterial() {
        return Material.EMERALD;
    }

    @Override
    public List<String> getExplanation() {
        List<String> expl = new LinkedList<>();
        expl.add("");
        expl.add(ChatColor.YELLOW + "Explanation: ");
        expl.add(ChatColor.BLUE + " - When you mine an ore, you get 2x the drops.");
        return expl;
    }
}
