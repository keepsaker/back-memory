package keepsake.ourmemory.domain.tag;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagColor {
    @Column(nullable = false)
    private String color;

    public TagColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final TagColor tagColor1 = (TagColor) other;
        return Objects.equals(color, tagColor1.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return "TagColor{" +
                "tagColor='" + color + '\'' +
                '}';
    }
}
