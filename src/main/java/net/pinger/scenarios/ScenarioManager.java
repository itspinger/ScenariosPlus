package net.pinger.scenarios;

import lombok.Getter;
import net.pinger.scenarios.types.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ScenarioManager {

    private final Scenarios scenario;
    @Getter private Set<Scenario> scenarios = new HashSet<>();

    public ScenarioManager(Scenarios scenarios) {
        this.scenario = scenarios;

        this.scenarios.add(new CutClean(this.scenario));
        this.scenarios.add(new Bowless(this.scenario));
        this.scenarios.add(new BloodDiamonds(this.scenario));
        this.scenarios.add(new Goldless(this.scenario));
        this.scenarios.add(new Timber(this.scenario));
    }

    public Scenario getScenarioByName(String name) {
        return this.scenarios.stream()
                .filter(scen -> scen.getName().equalsIgnoreCase(name))
                .findAny()
                .orElse(null);
    }

    public Collection<Scenario> getEnabledScenarios() {
        return this.scenarios.stream()
                .filter(Scenario::isEnabled)
                .collect(Collectors.toList());
    }

    public Scenario getScenarioById(int id) {
        return this.scenarios.stream()
                .filter(scen -> scen.getId() == id)
                .findAny()
                .orElse(null);
    }

    public void enableScenario(int id) {
        Scenario scenario = this.getScenarioById(id);

        if (scenario == null) {
            Bukkit.broadcastMessage(ChatColor.RED + "Failed to enable an unknown scenario");
            return;
        }

        scenario.setEnabled(true);
    }

    public void enableScenario(String id) {
        Scenario scenario = this.getScenarioByName(id);

        if (scenario == null) {
            Bukkit.broadcastMessage(ChatColor.RED + "Failed to enable an unknown scenario");
            return;
        }

        scenario.setEnabled(true);
    }

}
