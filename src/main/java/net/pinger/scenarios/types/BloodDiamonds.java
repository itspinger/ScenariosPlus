package net.pinger.scenarios.types;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.Scenarios;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;

public class BloodDiamonds extends Scenario {

    public BloodDiamonds(Scenarios scenarios) {
        super(scenarios);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (e.getBlock().getType() == Material.DIAMOND_ORE) {
            player.damage(0.5);
        }
    }

    @Override
    public String getName() {
        return "Blood Diamonds";
    }

    @Override
    public int getId() {
        return 4;
    }

    @Override
    public Material getMaterial() {
        return Material.DIAMOND;
    }

    @Override
    public List<String> getExplanation() {
        List<String> explain = new ArrayList<>();
        explain.add("");
        explain.add(ChatColor.YELLOW + "Explanation: ");
        explain.add(ChatColor.BLUE + " - When you mine a diamond you take 0.5 damage");
        return explain;
    }
}
