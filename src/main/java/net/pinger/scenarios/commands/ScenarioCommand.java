package net.pinger.scenarios.commands;

import net.pinger.scenarios.Scenarios;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ScenarioCommand implements CommandExecutor {

    private final Scenarios scenarios;

    public ScenarioCommand(Scenarios scenarios) {
        this.scenarios = scenarios;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

	    if (!((commandSender) instanceof Player)) return true;

	    Player sender = (Player) commandSender;

        if (!sender.hasPermission("scenarios.use")) {
            scenarios.openEnabledScenarios(sender);
            return true;
        }

        scenarios.openScenariosInventory(sender);
        return true;
    }
}
