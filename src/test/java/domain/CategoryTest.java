package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    @DisplayName("카테고리를 문자열로 반환하는 toString 테스트")
    @Test
    public void testToString() {
        assertThat(Category.CHICKEN.toString()).isEqualTo("[치킨]");
        assertThat(Category.BEVERAGE.toString()).isEqualTo("[음료]");
    }
}