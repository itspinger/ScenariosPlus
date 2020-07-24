package net.pinger.scenarios.types;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.Scenarios;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;
import java.util.List;

public class Rodless extends Scenario {

    public Rodless(Scenarios scenarios) {
        super(scenarios);
    }

    @EventHandler
    public void onDrop(EntityDeathEvent event) {
        for (ItemStack stack : event.getDrops()) {
            if (stack == null)
                continue;

            if (stack.getType() == Material.FISHING_ROD)
                event.getDrops().remove(stack);
        }
    }

    @EventHandler
    public void onHumanDrop(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getType() == Material.FISHING_ROD) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("Rods may not be dropped in a rodless game.");
        }
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
        CraftingInventory inventory = event.getInventory();
        ItemStack result = event.getRecipe().getResult();
        List<HumanEntity> viewers = event.getViewers();

        if (result.getType() == Material.FISHING_ROD) {
            inventory.setResult(new ItemStack(Material.AIR));

            for (HumanEntity entity : viewers) {
                if (entity instanceof Player) {
                    Player player = (Player) entity;
                    player.sendMessage(ChatColor.RED + "Rods may not be crafted in a rodless game.");
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Rodless";
    }

    @Override
    public int getId() {
        return 14;
    }

    @Override
    public Material getMaterial() {
        return Material.FISHING_ROD;
    }

    @Override
    public List<String> getExplanation() {
        List<String> explain = new LinkedList<>();
        explain.add("");
        explain.add(ChatColor.YELLOW + "Explanation: ");
        explain.add(ChatColor.BLUE + " - Gain 2 hearts upon right clicking a soup.");
        return explain;
    }
}
