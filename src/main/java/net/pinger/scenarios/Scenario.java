package net.pinger.scenarios;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public abstract class Scenario implements Listener {

    @Getter @Setter
    private boolean enabled;

    protected final Scenarios scenarios;

    public abstract String getName();

    public abstract int getId();

    public abstract Material getMaterial();

    public Scenario(Scenarios scenarios) {
        this.scenarios = scenarios;
    }

    public void enable() {
        Bukkit.getPluginManager().registerEvents(this, this.scenarios);
        setEnabled(true);
    }

    public void disable() {
        HandlerList.unregisterAll(this);
        setEnabled(false);
    }

}
