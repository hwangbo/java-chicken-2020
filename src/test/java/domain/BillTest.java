package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BillTest {
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
        assertThatThrownBy(() -> bill.order(MenuRepository.of(1), count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Bill.INVALID_COUNT_ERROR_MESSAGE);
    }

    @DisplayName("주문시 주문 갯수가 100개 이상일 경우 예외 처리 테스트")
    @Test
    public void order_3() {
        Bill bill = new Bill();
        assertThatThrownBy(() -> {
            bill.order(MenuRepository.of(1), 50);
            bill.order(MenuRepository.of(2), 50);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Bill.INVALID_TOTAL_COUNT_ERROR_MESSAGE);
    }

    @DisplayName("계산 수단이 잘못 입력되었을 경우 예외처리 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, 3, 5, 99})
    public void totalPrice_1(int payment) {
        assertThatThrownBy(() -> {
            Bill bill = new Bill();

            bill.order(MenuRepository.of(1), 1);

            bill.totalPrice(payment);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Bill.INVALID_PAYMENT_ERROR_MESSAGE);
    }

    @DisplayName("총합 금액을 반환하는 기능 중 치킨이 총 10마리 미만일 경우 테스트")
    @Test
    public void totalPrice_2() {
        Bill bill = new Bill();

        bill.order(MenuRepository.of(1), 3);
        bill.order(MenuRepository.of(2), 4);

        assertThat(bill.totalPrice(1)).isEqualTo(16000 * 7);
        assertThat(bill.totalPrice(2)).isEqualTo((int) (16000 * 7 * 0.9));
    }

    @DisplayName("총합 금액을 반환하는 기능 중 치킨이 총 20마리 일 경우 테스트")
    @Test
    public void totalPrice_3() {
        Bill bill = new Bill();

        bill.order(MenuRepository.of(1), 10);
        bill.order(MenuRepository.of(2), 10);

        assertThat(bill.totalPrice(1)).isEqualTo(300000);
        assertThat(bill.totalPrice(2)).isEqualTo((int) (300000 * 0.9));
    }
}