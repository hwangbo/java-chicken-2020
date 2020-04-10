package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TableRepositoryTest {
    @DisplayName("맞는 테이블 번호가 없는 경우 예외처리 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 4, 7, 9})
    void of_1(int number) {
        assertThatThrownBy(() -> TableRepository.of(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(TableRepository.INVALID_TABLE_NUMBER_ERROR_MESSAGE);
    }

    @DisplayName("테이블 번호에 맞는 테이블을 꺼내오는 기능 테스트")
    @ParameterizedTest
    @CsvSource({"1, 0", "2, 1", "3, 2", "5, 3", "6, 4", "8, 5"})
    void of_2(int number, int index) {
        assertThat(TableRepository.of(number)).isEqualTo(TableRepository.tables().get(index));
    }
}