package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {
    @DisplayName("주문한 치킨이 10마리가 넘는지 확인하는 기능 구현")
    @Test
    public void isOverTenChickens() {
        List<Menu> menus = MenuRepository.menus();
        Bill bill = new Bill();

        bill.order(menus.get(0), 2);
        bill.order(menus.get(1), 2);
        bill.order(menus.get(2), 2);
        bill.order(menus.get(3), 2);
        bill.order(menus.get(4), 2);

        assertTrue(bill.isOverTenChickens());
    }
}