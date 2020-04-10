package domain;

import java.util.HashMap;
import java.util.Map;

public class Bill {
    private static final int CASH = 2;
    private static final int TEN = 10;
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

    private int totalCount() {
        return bill.keySet()
                .stream()
                .mapToInt(menu -> bill.get(menu))
                .sum();
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

        return chickenCount >= TEN;
    }

    public double totalPrice(int payment) {
        checkValidPayment(payment);
        int totalPrice = bill.keySet().stream().mapToInt(menu -> menu.price(bill.get(menu))).sum();

        if (payment == CASH) {
            return totalPrice * DISCOUNT;
        }
        return totalPrice;
    }

    private void checkValidPayment(int payment) {
        if (payment != 1 && payment != 2) {
            throw new IllegalArgumentException(INVALID_PAYMENT_ERROR_MESSAGE);
        }
    }

    public boolean isEmpty() {
        return this.bill.isEmpty();
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
}
