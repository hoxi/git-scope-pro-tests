package net.tagpad.packageB;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConfigService {
    private Map<String, String> config;
    private static ConfigService instance;

    private ConfigService() {
        this.config = new HashMap<>();
        loadDefaults();
    }

    public static ConfigService getInstance() {
        if (instance == null) {
            instance = new ConfigService();
        }
        return instance;
    }

    private void loadDefaults() {
        config.put("app.name", "TagPad Application");
        config.put("app.version", "2.0.0");
        config.put("app.debug", "false");
        config.put("app.timeout", "30000");
    }

    public String get(String key) {
        return config.getOrDefault(key, "");
    }

    public String get(String key, String defaultValue) {
        return config.getOrDefault(key, defaultValue);
    }

    public void set(String key, String value) {
        config.put(key, value);
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(config.get(key));
    }

    public int getInt(String key) {
        try {
            return Integer.parseInt(config.get(key));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public Set<String> getAllKeys() {
        return config.keySet();
    }
}
