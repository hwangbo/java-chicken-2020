package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BillTest {
    private static List<Menu> menus = MenuRepository.menus();

    @DisplayName("주문한 치킨이 10마리가 넘는지 확인하는 기능 구현")
    @Test
    public void isOverTenChickens() {
        Bill bill = new Bill();

        bill.order(menus.get(0), 2);
        bill.order(menus.get(1), 2);
        bill.order(menus.get(2), 2);
        bill.order(menus.get(3), 2);
        bill.order(menus.get(4), 2);

        assertTrue(bill.isOverTenChickens());
    }

    @DisplayName("현금으로 결제 시, 총합의 10% 할인하는 기능 구현")
    @Test
    public void payWithCash() {
        Bill bill = new Bill();

        bill.order(menus.get(0), 1);

        assertThat(bill.totalPrice(1)).isEqualTo(16000);
        assertThat(bill.totalPrice(2)).isEqualTo(16000 * 0.9);
    }
}