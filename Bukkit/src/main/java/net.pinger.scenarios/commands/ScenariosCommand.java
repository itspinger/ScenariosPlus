package net.pinger.scenarios.commands;

import net.pinger.scenarios.ScenariosPlus;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ScenariosCommand implements CommandExecutor {

    private final ScenariosPlus plus;

    public ScenariosCommand(ScenariosPlus plus) {
        this.plus = plus;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "This command is player only!");
            return true;
        }

        Player p = (Player) commandSender;
        this.plus.getManager().openScenarios(p);
        return true;
    }
}
