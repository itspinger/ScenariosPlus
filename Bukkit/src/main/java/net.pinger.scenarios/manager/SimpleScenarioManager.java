package net.pinger.scenarios.manager;

import com.google.common.collect.Sets;
import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.ScenarioManager;
import net.pinger.scenarios.ScenariosPlus;
import net.pinger.scenarios.event.ScenarioUpdateEvent;
import net.pinger.scenarios.implementations.CCScenario;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SimpleScenarioManager implements ScenarioManager {

    private final Set<Scenario> scenarios = Sets.newHashSet();
    private final Set<Scenario> enabledScenarios = Sets.newHashSet();

    private final ScenariosPlus plus;

    public SimpleScenarioManager(ScenariosPlus plus) {
        this.plus = plus;

        // Registering some default scenarios
        this.registerScenario(new CCScenario());
    }

    @Override
    public void registerScenario(Scenario scenario) {
        // Edge case #1
        if (this.getScenarioById(scenario.getId()) != null) {
            throw new IllegalArgumentException("Failed to register the scenario with the already existing id.");
        }

        // Edge case #2
        if (this.getScenarioByNameExact(scenario.getName()) != null) {
            throw new IllegalArgumentException("Failed to register the scenario with the already existing name.");
        }

        this.scenarios.add(scenario);
    }

    @Override
    public void unregisterScenario(Scenario scenario) {
        this.scenarios.remove(scenario);
    }

    @Override
    public void unregisterScenario(Class<? extends Scenario> clazz) {
        for (Scenario scenario : this.scenarios) {
            if (scenario.getClass().equals(clazz) && clazz != Scenario.class) {
                this.unregisterScenario(scenario);
                break;
            }
        }
    }

    @Override
    public Set<? extends Scenario> getScenarios() {
        return this.scenarios;
    }

    @Override
    public void enableScenario(Scenario scenario) {
        if (this.enabledScenarios.contains(scenario))
            return;

        this.enabledScenarios.add(scenario);
        Bukkit.getPluginManager().registerEvents(scenario, this.plus);

        // Calling the change
        Bukkit.getPluginManager().callEvent(new ScenarioUpdateEvent(scenario, true));
    }

    @Override
    public void disableScenario(Scenario scenario) {
        if (!this.enabledScenarios.contains(scenario))
            return;

        this.enabledScenarios.remove(scenario);
        HandlerList.unregisterAll(scenario);

        // Calling the change
        Bukkit.getPluginManager().callEvent(new ScenarioUpdateEvent(scenario, false));
    }

    @Override
    public boolean isScenarioEnabled(int id) {
        Scenario s = this.getScenarioById(id);
        return s != null && this.enabledScenarios.contains(s);
    }

    @Override
    public boolean isScenarioEnabled(String name) {
        Scenario s = this.getScenarioByName(name);
        return s != null && this.enabledScenarios.contains(s);
    }

    public boolean isScenarioEnabled(Scenario scenario) {
        return this.enabledScenarios.contains(scenario);
    }

    @Override
    public Scenario getScenarioByNameExact(String name) {
        return this.scenarios.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Scenario getScenarioByName(String name) {
        return this.scenarios.stream()
                .filter(s -> s.getName().contains(name))
                .findAny()
                .orElse(null);
    }

    @Override
    public Scenario getScenarioById(int id) {
        return this.scenarios.stream()
                .filter(s -> s.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Scenario> matchScenarios(String name) {
        List<Scenario> list = new ArrayList<>();
        for (Scenario scenario : this.scenarios) {

            // Add the scenario if the name matches
            if (scenario.getName().contains(name)) {
                list.add(scenario);
            }
        }

        return list;
    }
}
