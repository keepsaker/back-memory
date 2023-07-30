package keepsake.ourmemory.domain.memory;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {
    @Lob
    private String content;

    public Content(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Content content1 = (Content) other;
        return Objects.equals(content, content1.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return "Content{" +
                "content='" + content + '\'' +
                '}';
    }
}
