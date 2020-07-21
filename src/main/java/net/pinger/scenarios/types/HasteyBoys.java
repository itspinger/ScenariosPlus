package net.pinger.scenarios.types;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.Scenarios;
import net.pinger.scenarios.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;
import java.util.List;

public class HasteyBoys extends Scenario {

    public HasteyBoys(Scenarios scenarios) {
        super(scenarios);
    }

    private final Material[] tools = new Material[] {Material.WOOD_AXE, Material.WOOD_SPADE, Material.WOOD_PICKAXE, Material.WOOD_HOE,
                                                    Material.STONE_AXE, Material.STONE_SPADE, Material.STONE_PICKAXE, Material.STONE_HOE,
                                                    Material.GOLD_AXE, Material.GOLD_SPADE, Material.GOLD_PICKAXE, Material.GOLD_HOE,
                                                    Material.IRON_AXE, Material.IRON_SPADE, Material.IRON_PICKAXE, Material.IRON_HOE,
                                                    Material.DIAMOND_AXE, Material.DIAMOND_SPADE, Material.DIAMOND_PICKAXE, Material.DIAMOND_HOE};

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        ItemStack stack = event.getRecipe().getResult();
        for (Material material : tools) {
            if (stack.getType() == material) {
                ItemBuilder builder = new ItemBuilder(stack.getType());
                builder.addEnchantment(Enchantment.DIG_SPEED, 3);
                builder.addEnchantment(Enchantment.DURABILITY, 5);
                stack = builder.toItemStack();
            }
        }

        event.setCurrentItem(stack);
    }

    @Override
    public String getName() {
        return "Hastey Boys";
    }

    @Override
    public int getId() {
        return 10;
    }

    @Override
    public Material getMaterial() {
        return Material.DIAMOND_PICKAXE;
    }

    @Override
    public List<String> getExplanation() {
        List<String> expl = new LinkedList<>();
        expl.add("");
        expl.add(ChatColor.YELLOW + "Explanation: ");
        expl.add(ChatColor.BLUE + " - All tools are enchanted with efficiency 3.");
        return expl;
    }
}
