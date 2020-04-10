package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TableTest {
    @DisplayName("테이블 번호가 일치하는 지 확인하는 메서드 테스트")
    @ParameterizedTest
    @CsvSource({"0, 1", "1, 2", "2, 3", "3, 5", "4, 6", "5, 8"})
    public void isTableNumber(int index, int tableNumber) {
        List<Table> tables = TableRepository.tables();
        assertTrue(tables.get(index).isTableNumber(tableNumber));
    }

    @DisplayName("주문한 메뉴를 갖고 있는지 확인하는 메서드 테스트")
    @Test
    public void orderMenu() {
        Table table = new Table(1);

        table.orderMenu(MenuRepository.menus().get(0), 1);

        Map<Menu, Integer> expected = new HashMap<>();
        expected.put(MenuRepository.menus().get(0), 1);

        assertThat(table.bill()).isEqualTo(expected);
    }

    @DisplayName("테이블에 주문이 없는지 확인하는 메서드 테스트")
    @Test
    public void isEmpty() {
        assertTrue(new Table(1).isEmpty());
    }

    @DisplayName("테이블 번호를 문자열로 반환하는 toString 테스트")
    @Test
    public void testToString() {
        assertThat(new Table(1).toString()).isEqualTo("1");
    }
}