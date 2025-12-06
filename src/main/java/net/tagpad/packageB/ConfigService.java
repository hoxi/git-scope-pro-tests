package net.tagpad.packageB;

import java.util.HashMap;
import java.util.Map;

public class ConfigService {
    private Map<String, String> config;

    public ConfigService() {
        this.config = new HashMap<>();
        loadDefaults();
    }

    private void loadDefaults() {
        config.put("app.name", "TagPad Application");
        config.put("app.version", "1.0.0");
        config.put("app.debug", "false");
    }

    public String get(String key) {
        return config.get(key);
    }

    public void set(String key, String value) {
        config.put(key, value);
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(config.get(key));
    }
}
