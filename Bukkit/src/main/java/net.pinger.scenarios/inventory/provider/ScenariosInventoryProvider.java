package net.pinger.scenarios.inventory.provider;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.Pagination;
import fr.minuskube.inv.content.SlotIterator;
import net.pinger.bukkit.item.ItemBuilder;
import net.pinger.bukkit.item.mask.impl.LoadingMask;
import net.pinger.common.lang.Lists;
import net.pinger.common.lang.Strings;
import net.pinger.scenarios.Scenario;
import net.pinger.scenarios.api.ScenariosAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ScenariosInventoryProvider implements InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {

    }

    @Override
    public void update(Player player, InventoryContents contents) {
        int refresh = contents.property("refresh", 0);
        contents.setProperty("refresh", refresh + 1);

        if (refresh % 3 != 0)
            return;

        int state = contents.property("state", 0);
        contents.setProperty("state", state + 1);

        // Checking what inventory should player have
        if (player.hasPermission("scenarios.edit")) {
            this.getEditableInventory(player, contents, state);
        } else {
            this.getDefaultInventory(player, contents, state);
        }
    }

    private void getDefaultInventory(Player player, InventoryContents contents, int state) {
        // Getting the pagination
        Pagination pagination = contents.pagination();

        // Scenarios
        Scenario[] scenarios = ScenariosAPI.getManager().getScenarios().toArray(new Scenario[0]);
        ClickableItem[] items = new ClickableItem[scenarios.length];

        for (int i = 0; i < scenarios.length; i++) {
            Scenario scenario = scenarios[i];

            items[i] = ClickableItem.empty(this.getItemStack(scenario, state, false));
        }

        pagination.setItemsPerPage(45);
        pagination.setItems(items);
        pagination.addToIterator(contents.newIterator(SlotIterator.Type.HORIZONTAL, 1, 1));
    }

    /**
     * This method refers to when a player has the permission to enable / disable guis.
     *
     * @param player the player
     * @param contents the current inventory contents
     */

    private void getEditableInventory(Player player, InventoryContents contents, int state) {
        // Getting the pagination
        Pagination pagination = contents.pagination();

        // Scenarios
        Scenario[] scenarios = ScenariosAPI.getManager().getScenarios().toArray(new Scenario[0]);
        ClickableItem[] items = new ClickableItem[scenarios.length];

        for (int i = 0; i < scenarios.length; i++) {
            Scenario scenario = scenarios[i];

            items[i] = ClickableItem.of(this.getItemStack(scenario, state, true), e -> {
                if (ScenariosAPI.isScenarioEnabled(scenario))
                    ScenariosAPI.getManager().disableScenario(scenario);
                else
                    ScenariosAPI.getManager().enableScenario(scenario);
            });
        }

        pagination.setItemsPerPage(45);
        pagination.setItems(items);
        pagination.addToIterator(contents.newIterator(SlotIterator.Type.HORIZONTAL, 1, 1));
    }

    private ItemStack getItemStack(Scenario scenario, int state, boolean edit) {
        ItemBuilder builder = new ItemBuilder(scenario.getMaterial());

        // Name
        builder.setName(new LoadingMask(ChatColor.AQUA, ChatColor.DARK_AQUA).getMaskedString(scenario.getName(), state));

        // Lore
        List<String> lore = Lists.newArrayList();
        if (edit) {
            lore.add(ScenariosAPI.isScenarioEnabled(scenario) ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled");
            lore.add(Strings.EMPTY);
        }

        lore.add(ChatColor.YELLOW + "Explanation:");
        lore.addAll(scenario.getExplanation());

        // Setting the lore
        builder.setLore(lore);
        return builder.toItemStack();
    }
}
