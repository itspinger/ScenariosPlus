package net.pinger.scenarios.types;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.Scenarios;
import net.pinger.scenarios.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;
import java.util.Random;

public class Goldless extends Scenario {

    public Goldless(Scenarios scenarios) {
        super(scenarios);
    }

    @EventHandler
    public void onBlastMineGold(EntityExplodeEvent e) {
        Iterator<Block> i = e.blockList().iterator();
        while (i.hasNext()) {
            Block block = i.next();
            if (block.getType() == Material.GOLD_ORE) {
                block.setType(Material.AIR);
                i.remove();
            }
        }
    }

    @EventHandler
    public void onBlockBreakGold(BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.GOLD_ORE) {
            e.setCancelled(true);
            e.getBlock().setType(Material.AIR);
            e.getBlock().getState().update();
            e.getBlock().getWorld().spawn(e.getBlock().getLocation(), ExperienceOrb.class).setExperience(new Random().nextInt(4) + 3);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        // The golden Head
        ItemStack itemStack = new ItemBuilder(Material.GOLDEN_APPLE).setName(ChatColor.GOLD + "Golden Head").setLore(ChatColor.DARK_PURPLE + "Some say consuming the head of a", ChatColor.DARK_PURPLE + "fallen foe strengthens the blood.").toItemStack();

        e.getDrops().add(itemStack);
        e.getDrops().add(new ItemStack(Material.GOLD_INGOT, 8));
    }

    @Override
    public String getName() {
        return "Goldless";
    }

    @Override
    public int getId() {
        return 2;
    }

    @Override
    public Material getMaterial() {
        return Material.GOLD_INGOT;
    }
}
