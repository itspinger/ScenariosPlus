package net.pinger.scenarios;

import net.pinger.scenarios.types.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ScenarioManager {

    private final Scenarios scenario;
    private final List<Scenario> scenarios = new LinkedList<>();

    public ScenarioManager(Scenarios scenarios) {
        this.scenario = scenarios;

        this.scenarios.add(new CutClean(this.scenario));
        this.scenarios.add(new TimeBomb(this.scenario));
        this.scenarios.add(new Bowless(this.scenario));
        this.scenarios.add(new Limitations(this.scenario));
        this.scenarios.add(new BloodDiamonds(this.scenario));
        this.scenarios.add(new Timber(this.scenario));
        this.scenarios.add(new Horseless(this.scenario));
        this.scenarios.add(new Fireless(this.scenario));
        this.scenarios.add(new TripleOres(this.scenario));
        this.scenarios.add(new NoFall(this.scenario));
        this.scenarios.add(new Soup(this.scenario));
	    this.scenarios.add(new NoClean(this.scenario));
        this.scenarios.add(new Goldless(this.scenario));
        this.scenarios.add(new DoubleOres(this.scenario));
        this.scenarios.add(new Diamondless(this.scenario));
        this.scenarios.add(new HasteyBoys(this.scenario));
        this.scenarios.add(new Rodless(this.scenario));
        this.scenarios.add(new BleedingSweets(this.scenario));


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

    public List<Scenario> getScenarios() {
        return scenarios;
    }
}

