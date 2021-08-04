package net.pinger.scenarios.implementations;

import net.pinger.scenarios.Scenario;
import org.bukkit.Material;

import java.util.List;

public class FirelessScenario implements Scenario {
    /**
     * Represents the name of this scenario.
     * This name must be unique, otherwise newly registered scenarios will not be added to the structure.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * Represents the material used in the inventory to display this scenario.
     * This material may not be unique, meaning that multiple scenarios can have the same material value.
     *
     * @return the material
     */
    @Override
    public Material getMaterial() {
        return null;
    }

    /**
     * This method represents the id of the scenario.
     * <p>
     * If any of the newly added scenarios have the same id,
     * registering the same scenario will throw an {@link IllegalArgumentException}
     *
     * @return the id
     */
    @Override
    public int getId() {
        return 0;
    }

    /**
     * Represents the explanation of this scenario, used in the inventory gui.
     * This is essentially the lore of the scenario.
     *
     * @return the explanation
     */
    @Override
    public List<String> getExplanation() {
        return null;
    }
}
