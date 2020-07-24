package net.pinger.scenarios.utils;

import net.pinger.scenarios.Scenarios;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

public class PlayerCache {

    private final Scenarios scenarios;

    private final Map<UUID, Integer> diamondMap = new LinkedHashMap<>(),
            goldMap = new LinkedHashMap<>(),
            ironMap = new LinkedHashMap<>();

    public PlayerCache(Scenarios scenario) {
        this.scenarios = scenario;
    }

    private Map<UUID, Integer> getMapFromType(MapType type) {
        Map<UUID, Integer> map = new LinkedHashMap<>();

        if (type == MapType.DIAMOND_MAP) return this.diamondMap;
        else if (type == MapType.GOLD_MAP) return this.goldMap;
        else if (type == MapType.IRON_MAP) return this.ironMap;


        Bukkit.getLogger().log(Level.WARNING, "Couldn't find the map. Contact Pinger#5246 on discord for support.");
        return map;
    }

    public boolean addToMap(MapType type, UUID uuid) {
        Map<UUID, Integer> mapFromType = this.getMapFromType(type);

        if (mapFromType.containsKey(uuid)) {
            if (mapFromType.get(uuid) >= type.getLimit()) {
                return false;
            }

            mapFromType.put(uuid, mapFromType.get(uuid) + 1);
            return true;
        } else {
            mapFromType.put(uuid, 1);
            return true;
        }
    }

    public boolean addToMap(Material material, UUID uuid) {
        return this.addToMap(this.getTypeFromMaterial(material), uuid);
    }

    public boolean addToMap(Block block, UUID uuid) {
        return this.addToMap(block.getType(), uuid);
    }

    public boolean hasPassedLimit(UUID id, MapType type) {
        Map<UUID, Integer> mapFromType = this.getMapFromType(type);
        if (!mapFromType.containsKey(id)) return false;
        else return mapFromType.get(id) >= type.getLimit();
    }

    public boolean hasPassedLimit(UUID id, Material material) {
        return this.hasPassedLimit(id, this.getTypeFromMaterial(material));
    }

    private MapType getTypeFromMaterial(Material material) {
        for (MapType type : MapType.values()) {
            if (type.getMat() == null)
                continue;

            if (type.getMat() == material)
                return type;
        }

        return null;
    }

    public enum MapType {
        DIAMOND_MAP(Material.DIAMOND_ORE, 16),
        GOLD_MAP(Material.GOLD_ORE, 32),
        IRON_MAP(Material.IRON_ORE, 64);

        private final Material mat;
        private final int limit;

        MapType(Material mat, int limit) {
            this.mat = mat;
            this.limit = limit;
        }

        public Material getMat() {
            return mat;
        }

        public int getLimit() {
            return limit;
        }
    }

}
