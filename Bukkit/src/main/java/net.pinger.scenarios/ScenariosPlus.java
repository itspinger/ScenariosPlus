package net.pinger.scenarios;

import net.pinger.scenarios.api.ScenariosAPI;
import net.pinger.scenarios.manager.SimpleScenarioManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ScenariosPlus extends JavaPlugin {

    @Override
    public void onEnable() {
        ScenariosAPI.setManager(new SimpleScenarioManager());
    }
}
