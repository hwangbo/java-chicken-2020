package domain;

import java.util.HashMap;
import java.util.Map;

public class Bill {
    private Map<Menu, Integer> bill;

    public Bill() {
        this.bill = new HashMap<>();
    }

    public void order(Menu menu, int count) {
        this.bill.put(menu, count);
    }

    public Map<Menu, Integer> getBill() {
        return bill;
    }
}
