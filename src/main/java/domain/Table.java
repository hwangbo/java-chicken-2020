package domain;

import java.util.Map;

public class Table {
    private final int number;
    private Bill bill = new Bill();

    public Table(final int number) {
        this.number = number;
    }

    public boolean isTableNumber(int tableNumber) {
        return this.number == tableNumber;
    }

    public void orderMenu(Menu menu, int count) {
        this.bill.order(menu, count);
    }

    public Map<Menu, Integer> bill() {
        return this.bill.getBill();
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
