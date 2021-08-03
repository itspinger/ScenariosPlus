package net.pinger.scenarios.api;

import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.ScenarioManager;

public class ScenariosAPI {

    private static ScenarioManager manager;

    /**
     * This prevents unnecessary initialization of this class
     */

    private ScenariosAPI() {}

    public static void setManager(ScenarioManager mng) {
        if (manager != null)
            throw new IllegalArgumentException("The ScenarioManager had already been initialized");

        manager = mng;
    }

    public static ScenarioManager getManager() {
        return manager;
    }

    public static void registerScenario(Scenario scenario) {
        manager.registerScenario(scenario);
    }

    public static void unregisterScenario(Scenario scenario) {
        manager.unregisterScenario(scenario);
    }

    public static void unregisterScenario(Class<? extends Scenario> clazz) {
        manager.unregisterScenario(clazz);
    }

    public static boolean isScenarioEnabled(String name) {
        return manager.isScenarioEnabled(name);
    }

    public static boolean isScenarioEnabled(int id) {
        return manager.isScenarioEnabled(id);
    }
}
