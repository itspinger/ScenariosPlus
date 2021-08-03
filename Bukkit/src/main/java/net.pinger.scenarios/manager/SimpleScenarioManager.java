package net.pinger.scenarios.manager;

import com.google.common.collect.Sets;
import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.ScenarioManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SimpleScenarioManager implements ScenarioManager {

    private final Set<Scenario> scenarios = Sets.newHashSet();

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
