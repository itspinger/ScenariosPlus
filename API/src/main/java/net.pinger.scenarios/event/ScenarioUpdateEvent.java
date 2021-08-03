package net.pinger.scenarios.event;

import net.pinger.scenarios.Scenario;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ScenarioUpdateEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    private final Scenario scenario;
    private final boolean state;

    public ScenarioUpdateEvent(Scenario scenario, boolean state) {
        this.scenario = scenario;
        this.state = state;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public boolean isEnabled() {
        return state;
    }
}
