package keepsake.ourmemory.domain.memory;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Star {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int value;

    Star(int value) {
        this.value = value;
    }

    public static Star from(int value) {
        return Arrays.stream(values())
                .filter(s -> s.value == value)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
