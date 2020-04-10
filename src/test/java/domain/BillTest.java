package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BillTest {
    private static List<Menu> menus = MenuRepository.menus();

    @DisplayName("주문시 메뉴가 null인 경우 예외 처리 테스트")
    @Test
    public void order_1() {
        Bill bill = new Bill();
        assertThatThrownBy(() -> bill.order(null, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Bill.INVALID_MENU_ERROR_MESSAGE);
    }

    @DisplayName("주문시 숫자가 범위 밖일 경우 예외 처리 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 100})
    public void order_2(int count) {
        Bill bill = new Bill();
        assertThatThrownBy(() -> bill.order(menus.get(0), count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Bill.INVALID_COUNT_ERROR_MESSAGE);
    }

    @DisplayName("주문시 주문 갯수가 100개 이상일 경우 예외 처리 테스트")
    @Test
    public void order_3() {
        Bill bill = new Bill();
        assertThatThrownBy(() -> {
            bill.order(menus.get(0), 50);
            bill.order(menus.get(1), 50);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Bill.INVALID_TOTAL_COUNT_ERROR_MESSAGE);
    }

    @DisplayName("주문한 치킨이 10마리가 넘는지 확인하는 기능 테스트")
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

    @DisplayName("계산 수단이 잘못 입력되었을 경우 예외처리 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, 3, 5, 99})
    public void totalPrice_1(int payment) {
        assertThatThrownBy(() -> {
            Bill bill = new Bill();

            bill.order(menus.get(0), 1);

            bill.totalPrice(payment);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Bill.INVALID_PAYMENT_ERROR_MESSAGE);
    }

    @DisplayName("현금으로 결제 시, 총합의 10% 할인하는 기능 테스트")
    @Test
    public void totalPrice_2() {
        Bill bill = new Bill();

        bill.order(menus.get(0), 1);

        assertThat(bill.totalPrice(1)).isEqualTo(16000);
        assertThat(bill.totalPrice(2)).isEqualTo(16000 * 0.9);
    }
}