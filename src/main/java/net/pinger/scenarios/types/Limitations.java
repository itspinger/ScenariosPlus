package net.pinger.scenarios.types;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.Scenarios;
import net.pinger.scenarios.utils.PlayerCache;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.*;

public class Limitations extends Scenario {

    public Limitations(Scenarios scenarios) {
        super(scenarios);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player p = event.getPlayer();
        Block block = event.getBlock();

        if (block.getType() == Material.DIAMOND_ORE) {
            boolean res = this.scenarios.getPlayerCache().addToMap(block, p.getUniqueId());
            if (!res) {
                p.sendMessage(ChatColor.RED + "You may not mine any more diamonds.");
                event.setCancelled(true);
            }
        } else if (block.getType() == Material.GOLD_ORE) {
            boolean res = this.scenarios.getPlayerCache().addToMap(block, p.getUniqueId());
            if (!res) {
                p.sendMessage(ChatColor.RED + "You may not mine any more gold.");
                event.setCancelled(true);
            }
        } else if (block.getType() == Material.IRON_ORE) {
            boolean res = this.scenarios.getPlayerCache().addToMap(block, p.getUniqueId());
            if (!res) {
                p.sendMessage(ChatColor.RED + "You may not mine any more iron.");
                event.setCancelled(true);
            }
        }
    }


    @Override
    public String getName() {
        return "Limitations";
    }

    @Override
    public int getId() {
        return 16;
    }

    @Override
    public Material getMaterial() {
        return Material.ANVIL;
    }

    @Override
    public List<String> getExplanation() {
        List<String> expl = new LinkedList<>();
        expl.add("");
        expl.add(ChatColor.YELLOW + "Explanation: ");
        expl.add(ChatColor.BLUE + " - You can mine up to 16 diamonds.");
        expl.add(ChatColor.BLUE + " - You can mine up to 32 gold.");
        expl.add(ChatColor.BLUE + " - You can mine up to 64 iron.");
        return expl;
    }
}
