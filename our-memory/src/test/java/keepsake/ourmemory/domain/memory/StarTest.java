package keepsake.ourmemory.domain.memory;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
class StarTest {

    @Test
    void 별점_정수에_맞는_Star인스턴스를_반환한다() {
        Star three = Star.from(3);
        assertThat(three).isEqualTo(Star.THREE);
    }

    @Test
    void 존재하지_않는_별점으로_변환_시도_시_예외가_발생한다() {
        assertThatThrownBy(() -> Star.from(6))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
