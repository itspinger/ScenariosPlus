package net.pinger.scenarios.events.custom;

import net.pinger.scenarios.Scenario;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ScenarioUpdateEvent extends Event {

	private final static HandlerList handlersList = new HandlerList();

	private final Scenario scenario;

	public ScenarioUpdateEvent(Scenario scenario) {
		this.scenario = scenario;
	}

	@Override
	public HandlerList getHandlers() {
		return handlersList;
	}

	public static HandlerList getHandlerList() {
		return handlersList;
	}

    public Scenario getScenario() {
        return scenario;
    }
}
