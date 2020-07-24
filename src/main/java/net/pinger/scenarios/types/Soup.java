package net.pinger.scenarios.types;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.Scenarios;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;
import java.util.List;

public class Soup extends Scenario {

    public Soup(Scenarios scenarios) {
        super(scenarios);
    }

    @EventHandler
    public void onConsume(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem().getType() == Material.MUSHROOM_SOUP) {
                event.setCancelled(true);
                event.getPlayer().setItemInHand(new ItemStack(Material.BOWL));
                event.getPlayer().setHealth(event.getPlayer().getHealth() + 4D);
            }
        }
    }

    @Override
    public String getName() {
        return "Soup";
    }

    @Override
    public int getId() {
        return 13;
    }

    @Override
    public Material getMaterial() {
        return Material.MUSHROOM_SOUP;
    }

    @Override
    public List<String> getExplanation() {
        List<String> explain = new LinkedList<>();
        explain.add("");
        explain.add(ChatColor.YELLOW + "Explanation: ");
        explain.add(ChatColor.BLUE + " - Crafting rods is disabled.");
        return explain;
    }
}
