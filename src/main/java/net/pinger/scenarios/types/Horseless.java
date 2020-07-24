package net.pinger.scenarios.types;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.Scenarios;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.spigotmc.event.entity.EntityMountEvent;

import java.util.LinkedList;
import java.util.List;

public class Horseless extends Scenario {

    public Horseless(Scenarios scenarios) {
        super(scenarios);
    }


    @EventHandler
    public void onMount(EntityMountEvent event) {
        if (event.getMount().getType() == EntityType.HORSE)
            event.setCancelled(true);
    }


    @Override
    public String getName() {
        return "Horseless";
    }

    @Override
    public int getId() {
        return 12;
    }

    @Override
    public Material getMaterial() {
        return Material.SADDLE;
    }

    @Override
    public List<String> getExplanation() {
        List<String> expl = new LinkedList<>();
        expl.add("");
        expl.add(ChatColor.YELLOW + "Explanation: ");
        expl.add(ChatColor.BLUE + " - Mounting Horses is disabled.");
        return expl;
    }
}
