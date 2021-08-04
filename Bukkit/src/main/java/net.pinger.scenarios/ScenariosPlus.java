package net.pinger.scenarios;

import net.pinger.scenarios.api.ScenariosAPI;
import net.pinger.scenarios.commands.ScenariosCommand;
import net.pinger.scenarios.inventory.InventoryManager;
import net.pinger.scenarios.manager.SimpleScenarioManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ScenariosPlus extends JavaPlugin {

    private InventoryManager manager;

    @Override
    public void onEnable() {
        this.manager = new InventoryManager(this);
        ScenariosAPI.setManager(new SimpleScenarioManager(this));

        // Register the command
        getCommand("scenarios").setExecutor(new ScenariosCommand(this));
    }

    public InventoryManager getManager() {
        return manager;
    }
}
