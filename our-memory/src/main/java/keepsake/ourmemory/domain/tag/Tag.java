package keepsake.ourmemory.domain.tag;

import jakarta.persistence.*;
import keepsake.ourmemory.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import java.util.Objects;

@Entity
@Getter
@SQLDelete(sql = "UPDATE tag SET deleted = true WHERE id = ?")
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

    @Column(nullable = false)
    private boolean deleted = false;

    public Tag(Long memberId, TagName tagName, TagColor tagColor) {
        this.memberId = memberId;
        this.tagName = tagName;
        this.tagColor = tagColor;
    }

    public String getTagNameValue() {
        return tagName.getName();
    }

    public String getTagColorValue() {
        return tagColor.getColor();
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
