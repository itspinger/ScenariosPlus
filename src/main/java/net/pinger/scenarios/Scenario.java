package net.pinger.scenarios;

import lombok.Getter;
import lombok.Setter;
import net.pinger.scenarios.events.custom.ScenarioUpdateEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.List;

public abstract class Scenario implements Listener {

    private boolean enabled;

    protected final Scenarios scenarios;

    public abstract String getName();

    public abstract int getId();

    public abstract Material getMaterial();

    public abstract List<String> getExplanation();

    public Scenario(Scenarios scenarios) {
        this.scenarios = scenarios;
    }

    public void enable() {
        Bukkit.getPluginManager().registerEvents(this, this.scenarios);
        this.enabled = true;
        Bukkit.getPluginManager().callEvent(new ScenarioUpdateEvent(this));
    }

    public void disable() {
        HandlerList.unregisterAll(this);
        this.enabled = false;
	    Bukkit.getPluginManager().callEvent(new ScenarioUpdateEvent(this));
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
