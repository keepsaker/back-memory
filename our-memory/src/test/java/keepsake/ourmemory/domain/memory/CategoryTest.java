package keepsake.ourmemory.domain.memory;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CategoryTest {

    @Test
    void 카테고리를_변환한다() {
        // given
        Category actual = Category.CAFE;
        String categoryName = actual.getCategoryName();

        // when
        Category expected = Category.from(categoryName);

        // then
        assertThat(actual).isSameAs(expected);
    }

    @Test
    void 존재하지_않는_카테고리_변환_시_예외발생() {
        assertThatThrownBy(() -> Category.from("our memory........."))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
