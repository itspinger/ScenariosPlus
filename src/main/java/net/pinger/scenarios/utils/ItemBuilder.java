package net.pinger.scenarios.utils;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    private ItemStack s;

    /**
     * Constructor of the class
     *
     * @param material
     */

    public ItemBuilder(Material material) {
        this(material, 1);
    }

    /**
     * Constructor of the class
     *
     * @param material
     * @param amount
     */

    public ItemBuilder(Material material, int amount) {
        s = new ItemStack(material, amount);
    }

    /**
     * Sets a valid name for the specified ItemStack
     */

    public ItemBuilder setName(String name) {
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(name);
        s.setItemMeta(meta);
        return this;
    }

    /**
     * Sets a valid lore for the specified ItemStack
     */

    public ItemBuilder setLore(String... lore) {
        ItemMeta meta = s.getItemMeta();
        meta.setLore(Arrays.asList(lore));
        s.setItemMeta(meta);
        return this;
    }

    /**
     * Sets a valid lore for the specified ItemStack
     */

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta meta = s.getItemMeta();
        meta.setLore(lore);
        s.setItemMeta(meta);
        return this;
    }


    /**
     * Sets a valid DyeColor for a specific ItemStack
     */

    public ItemBuilder setDyeColor(DyeColor dyeColor) {
        s.setDurability((short) dyeColor.getData());
        return this;
    }

    /**
     * Adds an echantment for the specified ItemStack
     */

    public ItemBuilder addEnchantment(Enchantment enchantment) {
        addEnchantment(enchantment, 1);
        return this;
    }

    /**
     * Adds an echantment with wanted level for the specified ItemStack
     */

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        s.addUnsafeEnchantment(enchantment, level);
        return this;
    }


    /**
     * Returns the ItemStack
     */

    public ItemStack toItemStack() {
        return s;
    }


}
