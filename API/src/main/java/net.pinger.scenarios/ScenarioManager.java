package net.pinger.scenarios;

import java.util.List;
import java.util.Set;

public interface ScenarioManager {

    /**
     * Register an array of scenarios to the data holder.
     * <p>
     * This method is not responsible for enabling and / or disabling scenarios,
     * but only for registering custom scenarios
     *
     * @param scenarios the scenarios that are supposed to be registered
     */

    void registerScenario(Scenario... scenarios);

    /**
     * Registers a certain scenario to the corresponding data holder.
     * <p>
     * This method is not responsible for enabling and / or disabling scenarios,
     * but only for registering custom scenarios.
     *
     * @param scenario the scenario that we want to register
     */

    void registerScenario(Scenario scenario);

    /**
     * Unregisters the specified scenario from the data structure.
     *
     * @param scenario the scenario
     */

    void unregisterScenario(Scenario scenario);

    /**
     * This method unregisters the specified scenario within its class.
     *
     * @param clazz the class of the scenario
     */

    void unregisterScenario(Class<? extends Scenario> clazz);

    /**
     * Returns all scenarios that are currently registered within this plugin.
     * For example, if {@link #unregisterScenario(Scenario)} is used on a scenario, that scenario will not appear in this set.
     *
     * @return all scenarios
     */

    Set<? extends Scenario> getScenarios();

    /**
     * Returns all scenarios that are currently enabled.
     *
     * @return the scenarios that are enabled
     */

    Set<? extends Scenario> getEnabledScenarios();

    /**
     * Returns all scenarios that are currently disabled.
     *
     * @return the scenarios that are currently disabled
     */

    Set<? extends Scenario> getDisabledScenarios();

    /**
     * This method enables a certain scenario.
     *
     * @param scenario the scenario that is supposed to be enabled
     */

    void enableScenario(Scenario scenario);

    /**
     * This method disables a certain scenario.
     *
     * @param scenario the scenario that is supposed to be disabled
     */

    void disableScenario(Scenario scenario);

    /**
     * This method checks if a scenario with a specific id is enabled.
     * If the id is non-existent, false will be returned.
     *
     * @param id the id of the sceanario
     * @return whether the scenario is enabled
     */

    boolean isScenarioEnabled(int id);

    /**
     * This method checks if a scenario with a mentioned name is enabled.
     * If the name is non-existent, false will be returned.
     *
     * @param name the name of the scenario
     * @return if the scenario is enabled
     */

    boolean isScenarioEnabled(String name);

    /**
     * This method checks if a certain scenario is enabled.
     *
     * @param scenario the scenario
     * @return if the scenario is enabled
     */

    boolean isScenarioEnabled(Scenario scenario);

    /**
     * Returns a scenario based on the specified name.
     * If this name doesn't find a matching scenario, it will result in this method returning null.
     *
     * @param name the name of the scenario
     * @return the matching scenario
     */

    Scenario getScenarioByNameExact(String name);

    /**
     * This method returns a first scenario that matches the given name.
     * Note that if you want to find the scenario that matches the exact string, you should use {@link #getScenarioByNameExact(String)}
     *
     * @param name the name of the scenario
     * @return the matching scenario
     */

    Scenario getScenarioByName(String name);

    /**
     * This method returns a scenario with the specified id.
     * Note that each scenario has a unique id, so multiple scenarios may not have the same id.
     *
     * @param id the id of the scenario
     * @return the matching scenario
     */

    Scenario getScenarioById(int id);

    /**
     * This method finds all scenarios that match the given name.
     * Unlike {@link #getScenarioByName(String)} which only finds the first match, this method will always return all of the matches.
     *
     * @param name the name of the scenario
     * @return the matching scenarios
     */

    List<Scenario> matchScenarios(String name);

}
