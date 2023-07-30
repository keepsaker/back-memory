package keepsake.ourmemory.domain.tag;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import keepsake.ourmemory.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Embedded
    private TagName tagName;

    @Embedded
    private TagColor tagColor;

    public Tag(Long memberId, TagName tagName, TagColor tagColor) {
        this.memberId = memberId;
        this.tagName = tagName;
        this.tagColor = tagColor;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final Tag tag = (Tag) other;
        return Objects.equals(id, tag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", tagName=" + tagName +
                ", tagColor=" + tagColor +
                '}';
    }
}
