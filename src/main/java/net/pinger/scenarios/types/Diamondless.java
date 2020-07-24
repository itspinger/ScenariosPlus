package net.pinger.scenarios.types;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.Scenarios;
import net.pinger.scenarios.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Diamondless extends Scenario {


    public Diamondless(Scenarios scenarios) {
        super(scenarios);
    }

    @EventHandler
    public void onBlastMineDiamond(EntityExplodeEvent e) {
        Iterator<Block> i = e.blockList().iterator();
        while (i.hasNext()) {
            Block block = i.next();
            if (block.getType() == Material.DIAMOND_ORE) {
                block.setType(Material.AIR);
                i.remove();
            }
        }
    }

    @EventHandler
    public void onBlockBreakGold(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (e.getBlock().getType() == Material.DIAMOND_ORE) {
            if (scenarios.getPlayerCache().hasPassedLimit(p.getUniqueId(), e.getBlock().getType())) return;

            e.setCancelled(true);
            e.getBlock().setType(Material.AIR);
            e.getBlock().getState().update();
            e.getBlock().getWorld().spawn(e.getBlock().getLocation(), ExperienceOrb.class).setExperience(new Random().nextInt(7) + 3);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        // The golden Head
        e.getDrops().add(new ItemStack(Material.DIAMOND));
    }


    @Override
    public String getName() {
        return "Diamondless";
    }

    @Override
    public int getId() {
        return 9;
    }

    @Override
    public Material getMaterial() {
        return Material.DIAMOND_AXE;
    }

    @Override
    public List<String> getExplanation() {
        List<String> explain = new LinkedList<>();
        explain.add("");
        explain.add(ChatColor.YELLOW + "Explanation: ");
        explain.add(ChatColor.BLUE + " - Diamond drops are disabled");
        explain.add(ChatColor.BLUE + " - Blast Mining diamonds is disabled");
        explain.add(ChatColor.BLUE + " - Everytime you kill a person you receieve 1 diamond from them.");
        return explain;
    }
}
