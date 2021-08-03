package net.pinger.scenarios;

import org.bukkit.Material;
import org.bukkit.event.Listener;

import java.util.List;

public abstract class Scenario implements Listener {

    private boolean enabled = false;

    /**
     * Represents the name of this scenario.
     * This name must be unique, otherwise newly registered scenarios will not be added to the structure.
     *
     * @return the name
     */

    public abstract String getName();

    /**
     * Represents the material used in the inventory to display this scenario.
     * This material may not be unique, meaning that multiple scenarios can have the same material value.
     *
     * @return the material
     */

    public abstract Material getMaterial();

    /**
     * This method represents the id of the scenario.
     * <p>
     * If any of the newly added scenarios have the same id,
     * registering the same scenario will throw an {@link IllegalArgumentException}
     *
     * @return the id
     */

    public abstract int getId();

    /**
     * Represents the explanation of this scenario, used in the inventory gui.
     * This is essentially the lore of the scenario.
     *
     * @return the explanation
     */

    public abstract List<String> getExplanation();

    /**
     * Returns whether this scenario is currently enabled
     *
     * @return
     */

    public boolean isEnabled() {
        return enabled;
    }

    void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
