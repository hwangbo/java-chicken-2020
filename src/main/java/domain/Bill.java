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

    public boolean isOverTenChickens() {
        int chickenCount = bill.keySet()
                .stream()
                .filter(Menu::isChicken)
                .mapToInt(menu -> bill.get(menu))
                .sum();

        return chickenCount >= 10;
    }

    public double totalPrice(int payment) {
        int totalPrice = bill.keySet().stream().mapToInt(menu -> menu.price(bill.get(menu))).sum();

        if (payment == 2) {
            return totalPrice * 0.9;
        }
        return totalPrice;
    }

    public boolean isEmpty() {
        return this.bill.isEmpty();
    }
}
