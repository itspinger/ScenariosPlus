package net.pinger.scenarios.types;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.Scenarios;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;
import java.util.List;

public class Bowless extends Scenario {

    public Bowless(Scenarios scenarios) {
        super(scenarios);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        for (ItemStack stack : e.getDrops()) {
            if (stack.getType() == Material.BOW) {
                e.getDrops().remove(stack);
            }
        }
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent e) {
        if (e.getRecipe().getResult().getType() == Material.BOW) {
            e.getInventory().setResult(new ItemStack(Material.AIR));
            for (LivingEntity livingEntity : e.getViewers()) {
                if (livingEntity instanceof Player) {
                    Player player = (Player) livingEntity;
                    player.sendMessage(ChatColor.RED + "Bows cannot be crafted in Bowless games.");
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Bowless";
    }

    @Override
    public int getId() {
        return 5;
    }

    @Override
    public Material getMaterial() {
        return Material.BOW;
    }

    @Override
    public List<String> getExplanation() {
        List<String> explain = new LinkedList<>();
        explain.add("");
        explain.add(ChatColor.YELLOW + "Explanation: ");
        explain.add(ChatColor.BLUE + " - Bows are not allowed");
        explain.add(ChatColor.BLUE + " - Bows cannot be dropped by a mob");
        return explain;
    }
}
