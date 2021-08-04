package net.pinger.scenarios.inventory;

import fr.minuskube.inv.SmartInventory;
import net.pinger.scenarios.ScenariosPlus;
import net.pinger.scenarios.inventory.provider.ScenariosInventoryProvider;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

public class InventoryManager {

    private final SmartInventory scenariosInventory;

    public InventoryManager(ScenariosPlus plus) {
        fr.minuskube.inv.InventoryManager manager = new fr.minuskube.inv.InventoryManager(plus);
        manager.init();

        this.scenariosInventory = SmartInventory.builder()
                                    .title(ChatColor.DARK_GRAY + "Scenarios > View")
                                    .manager(manager)
                                    .type(InventoryType.CHEST)
                                    .provider(new ScenariosInventoryProvider())
                                    .build();
    }

    public void openScenarios(Player player) {
        this.scenariosInventory.open(player);
    }

}
