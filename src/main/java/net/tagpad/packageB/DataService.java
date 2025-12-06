package net.tagpad.packageB;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataService {
    private List<String> dataStore;
    private int maxCapacity;

    public DataService() {
        this(100);
    }

    public DataService(int maxCapacity) {
        this.dataStore = new ArrayList<>();
        this.maxCapacity = maxCapacity;
    }

    public boolean addData(String data) {
        if (dataStore.size() >= maxCapacity) {
            return false;
        }
        dataStore.add(data);
        return true;
    }

    public List<String> getAllData() {
        return new ArrayList<>(dataStore);
    }

    public List<String> searchData(String keyword) {
        return dataStore.stream()
                .filter(item -> item.contains(keyword))
                .collect(Collectors.toList());
    }

    public void clearData() {
        dataStore.clear();
    }

    public int getDataCount() {
        return dataStore.size();
    }

    public boolean removeData(String data) {
        return dataStore.remove(data);
    }
}
