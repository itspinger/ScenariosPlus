package net.pinger.scenarios.types;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.Scenarios;
import net.pinger.scenarios.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class TimeBomb extends Scenario {

    private Collection<Scenario> enabled;

    public TimeBomb(Scenarios scenarios) {
        super(scenarios);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        Inventory inv = p.getInventory();
        Location loc = p.getLocation();

        p.getLocation().getBlock().breakNaturally();
        p.getLocation().getBlock().setType(Material.CHEST);
        p.getLocation().add(1, 0, 0).getBlock().breakNaturally();
        p.getLocation().add(1, 0, 0).getBlock().setType(Material.CHEST);
        p.getLocation().add(0, 1, 0).getBlock().setType(Material.AIR);
        p.getLocation().add(1, 1, 0).getBlock().setType(Material.AIR);
        Chest chest = (Chest) loc.getBlock().getState();
        this.addToChest(inv, chest.getInventory());

        event.getDrops().clear();
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                loc.getWorld().createExplosion(loc, 6F);
                loc.getWorld().strikeLightning(loc);
                Bukkit.broadcastMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "SCENARIOS » " + ChatColor.YELLOW + p.getName() + ChatColor.WHITE + "'s corpse has blown up.");
            }
        }.runTaskLater(scenarios, 20 * 30L);
    }

    public void addToChest(Inventory player, Inventory chest) {
        PlayerInventory inventory = (PlayerInventory) player;
        enabled = scenarios.getScenarioManager().getEnabledScenarios();

        chest.addItem(inventory.getArmorContents());

        ItemStack itemStack = new ItemBuilder(Material.GOLDEN_APPLE).setName(ChatColor.GOLD + "Golden Head").setLore(ChatColor.DARK_PURPLE + "Some say consuming the head of a", ChatColor.DARK_PURPLE + "fallen foe strengthens the blood.").toItemStack();
        boolean found = false;

        for (ItemStack stack : inventory.getContents()) {
            if (stack == null) continue;

            if (stack.getType() == Material.GOLDEN_APPLE) {
                if (stack.isSimilar(itemStack)) {
                    found = true;
                    stack.setAmount(stack.getAmount() + 1);
                }
            }

            chest.addItem(stack);
        }

        for (Scenario scenario : enabled) {
            if (scenario.getId() == 2) {
                chest.addItem(new ItemStack(Material.GOLD_INGOT, 8));
            } else if (scenario.getId() == 9) {
                chest.addItem(new ItemStack(Material.DIAMOND));
            }
        }

        if (!found) chest.addItem(itemStack);
    }


    @Override
    public String getName() {
        return "TimeBomb";
    }

    @Override
    public int getId() {
        return 11;
    }

    @Override
    public Material getMaterial() {
        return Material.CHEST;
    }

    @Override
    public List<String> getExplanation() {
        List<String> expl = new LinkedList<>();
        expl.add("");
        expl.add(ChatColor.YELLOW + "Explanation: ");
        expl.add(ChatColor.BLUE + " - Whenever a player dies, their stuff will be placed in a chest. ");
        expl.add(ChatColor.BLUE + " - After the 30 seconds, the chest full of items will explode..");
        return expl;
    }
}
