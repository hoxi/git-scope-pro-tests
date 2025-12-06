package net.tagpad.packageB;

import java.util.ArrayList;
import java.util.List;

public class DataService {
    private List<String> dataStore;

    public DataService() {
        this.dataStore = new ArrayList<>();
    }

    public void addData(String data) {
        dataStore.add(data);
    }

    public List<String> getAllData() {
        return new ArrayList<>(dataStore);
    }

    public void clearData() {
        dataStore.clear();
    }

    public int getDataCount() {
        return dataStore.size();
    }
}
