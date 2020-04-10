package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuRepositoryTest {
    @DisplayName("맞는 메뉴 번호가 없는 경우 예외처리 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 7, 9, 20, 23})
    void of_1(int number) {
        assertThatThrownBy(() -> MenuRepository.of(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MenuRepository.INVALID_MENU_NUMBER_ERROR_MESSAGE);
    }

    @DisplayName("메뉴 번호에 맞는 메뉴를 꺼내오는 기능 테스트")
    @ParameterizedTest
    @CsvSource({"1, 0", "2, 1", "3, 2", "4, 3", "5, 4", "6, 5", "21, 6", "22, 7"})
    void of_2(int number, int index) {
        assertThat(MenuRepository.of(number)).isEqualTo(MenuRepository.menus().get(index));
    }
}