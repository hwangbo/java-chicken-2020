package domain;

public class Menu {
    private final int number;
    private final String name;
    private final Category category;
    private final int price;

    public Menu(final int number, final String name, final Category category, final int price) {
        this.number = number;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public int price(int count) {
        return this.price * count;
    }

    public boolean isMenu(int number) {
        return this.number == number;
    }

    public boolean isChicken() {
        return this.category == Category.CHICKEN;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return category + " " + number + " - " + name + " : " + price + "원";
    }
}
