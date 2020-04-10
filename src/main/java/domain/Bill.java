package domain;

import java.util.HashMap;
import java.util.Map;

public class Bill {
    private static final int CASH = 2;
    private static final double DISCOUNT = 0.9;
    public static final String INVALID_MENU_ERROR_MESSAGE = "메뉴의 값이 null입니다.";
    public static final String INVALID_COUNT_ERROR_MESSAGE = "갯수는 1 이상 99 이하까지 가능합니다.";
    public static final String INVALID_TOTAL_COUNT_ERROR_MESSAGE = "한 테이블이 주문할 수 있는 최대 메뉴 갯수는 99개입니다.";
    public static final String INVALID_PAYMENT_ERROR_MESSAGE = "계산 수단이 잘못 입력되었습니다.";

    private Map<Menu, Integer> bill;

    public Bill() {
        this.bill = new HashMap<>();
    }

    public void order(Menu menu, int count) {
        checkValidMenu(menu);
        checkValidCount(count);
        checkCountOver99(count);
        this.bill.put(menu, count);
    }

    public int totalPrice(int payment) {
        checkValidPayment(payment);
        int totalPrice = bill.keySet()
                .stream()
                .mapToInt(this::menuPrice)
                .sum();

        totalPrice -= discountByTenChickens();

        if (payment == CASH) {
            return (int) (totalPrice * DISCOUNT);
        }

        return totalPrice;
    }

    private int discountByTenChickens() {
        int chickenCount = bill.keySet()
                .stream()
                .filter(Menu::isChicken)
                .mapToInt(menu -> bill.get(menu))
                .sum();

        return (chickenCount / 10) * 10000;
    }

    public int menuPrice(Menu menu) {
        return menu.price(bill.get(menu));
    }

    public boolean isEmpty() {
        return this.bill.isEmpty();
    }

    public Map<Menu, Integer> getBill() {
        return bill;
    }

    private void checkValidPayment(int payment) {
        if (payment != 1 && payment != 2) {
            throw new IllegalArgumentException(INVALID_PAYMENT_ERROR_MESSAGE);
        }
    }

    private void checkValidMenu(Menu menu) {
        if (menu == null) {
            throw new IllegalArgumentException(INVALID_MENU_ERROR_MESSAGE);
        }
    }

    private void checkValidCount(int count) {
        if (count < 1 || count > 99) {
            throw new IllegalArgumentException(INVALID_COUNT_ERROR_MESSAGE);
        }
    }

    private void checkCountOver99(int count) {
        if (count + totalCount() > 99) {
            throw new IllegalArgumentException(INVALID_TOTAL_COUNT_ERROR_MESSAGE);
        }
    }

    private int totalCount() {
        return bill.keySet()
                .stream()
                .mapToInt(menu -> bill.get(menu))
                .sum();
    }
}
