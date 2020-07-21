package net.pinger.scenarios.types;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.Scenarios;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;
import java.util.List;

public class Timber extends Scenario {

    public Timber(Scenarios scenarios) {
        super(scenarios);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.LOG || e.getBlock().getType() == Material.LOG_2) {
            Block block = e.getBlock();
            breakBlock(block);
        }
    }

    /**
     * Breaks the whole fucking tree
     * Crazy am I right?
     *
     * @param block1
     */

    private void breakBlock(Block block1) {
        for (BlockFace blockFace : BlockFace.values()) {
            if (block1.getRelative(blockFace).getType() == Material.LOG || block1.getRelative(blockFace).getType() == Material.LOG_2) {
                Block block = block1.getRelative(blockFace);
                block.breakNaturally();
                block1.breakNaturally();
                breakBlock(block);
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.APPLE, 1));
            }
        }
    }

    @Override
    public String getName() {
        return "Timber";
    }

    @Override
    public int getId() {
        return 3;
    }

    @Override
    public Material getMaterial() {
        return Material.APPLE;
    }

    @Override
    public List<String> getExplanation() {
        List<String> explain = new LinkedList<>();
        explain.add("");
        explain.add(ChatColor.YELLOW + "Explanation: ");
        explain.add(ChatColor.BLUE + " - Breaking a log will result in breaking the whole tree");
        return explain;
    }
}
