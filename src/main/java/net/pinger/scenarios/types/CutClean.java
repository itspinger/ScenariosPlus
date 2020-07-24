package net.pinger.scenarios.types;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.ScenarioManager;
import net.pinger.scenarios.Scenarios;
import net.pinger.scenarios.utils.ItemBuilder;
import net.pinger.scenarios.utils.PlayerCache;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CutClean extends Scenario {

    public CutClean(Scenarios scenarios) {
        super(scenarios);
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        Player p = e.getPlayer();

        if (scenarios.getScenarioManager().getScenarioByName("Triple Ores").isEnabled() || scenarios.getScenarioManager().getScenarioByName("Double Ores").isEnabled()) {
            return;
        }

        if (block.getType() == Material.IRON_ORE) {
            if (scenarios.getPlayerCache().hasPassedLimit(p.getUniqueId(), block.getType())) return;

            e.setCancelled(true);
            block.setType(Material.AIR);
            block.getState().update();
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.IRON_INGOT));
            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class).setExperience(new Random().nextInt(4));

        } else if (block.getType() == Material.GOLD_ORE && !this.scenarios.getScenarioManager().getScenarioById(2).isEnabled()) {
            if (scenarios.getPlayerCache().hasPassedLimit(p.getUniqueId(), block.getType())) return;

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

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        // The golden Head
        ItemStack itemStack = new ItemBuilder(Material.GOLDEN_APPLE).setName(ChatColor.GOLD + "Golden Head").setLore(ChatColor.DARK_PURPLE + "Some say consuming the head of a", ChatColor.DARK_PURPLE + "fallen foe strengthens the blood.").toItemStack();

        e.getDrops().add(itemStack);
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
        List<String> explain = new LinkedList<>();
        explain.add("");
        explain.add(ChatColor.YELLOW + "Explanation: ");
        explain.add(ChatColor.BLUE + " - Ores are 100% smelted");
        explain.add(ChatColor.BLUE + " - Food is 100% cooked");
        explain.add(ChatColor.BLUE + " - No furnace needed");
        explain.add(ChatColor.BLUE + " - Flint/Apple rates are 100%");
        return explain;
    }


}
