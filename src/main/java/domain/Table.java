package domain;

public class Table {
    private final int number;

    public Table(final int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    public boolean isTableNumber(int tableNumber) {
        return this.number == tableNumber;
    }
}
