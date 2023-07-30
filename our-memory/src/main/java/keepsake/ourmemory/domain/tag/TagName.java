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
public class TagName {
    @Column(nullable = false)
    private String name;

    public TagName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final TagName tagName1 = (TagName) other;
        return Objects.equals(name, tagName1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "TagName{" +
                "tagName='" + name + '\'' +
                '}';
    }
}
