package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class MenuTest {
    @DisplayName("메뉴의 갯수에 따른 가격을 가져오는 기능 테스트")
    @ParameterizedTest
    @CsvSource({"0, 2, 32000", "1, 2, 32000", "2, 2, 32000", "3, 2, 32000", "4, 2, 34000", "5, 2, 34000", "6, 2, 2000", "7, 2, 2000"})
    public void price(int index, int count, int totalPrice) {
        assertThat(MenuRepository.menus().get(index).price(count)).isEqualTo(totalPrice);
    }
}