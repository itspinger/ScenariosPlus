package net.pinger.scenarios.commands;

import net.pinger.scenarios.Scenarios;
import org.bukkit.ChatColor;
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
        if (!commandSender.hasPermission("scenarios.use")) {
            commandSender.sendMessage(ChatColor.RED + "Not enough permissions");
            return true;
        }

        if (!((commandSender) instanceof Player)) return true;

        scenarios.openScenariosInventory((Player) commandSender);
        return true;
    }
}
