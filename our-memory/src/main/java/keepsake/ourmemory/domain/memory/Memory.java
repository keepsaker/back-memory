package keepsake.ourmemory.domain.memory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import keepsake.ourmemory.domain.BaseEntity;
import keepsake.ourmemory.domain.image.Image;
import keepsake.ourmemory.domain.tag.MemoryTag;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Embedded
    private Title title;

    @Enumerated(STRING)
    private Category category;

    private LocalDateTime visitedAt;

    @Enumerated(STRING)
    private Star star;

    @Embedded
    private Content content;

    @Enumerated(STRING)
    private MemoryStatus memoryStatus = MemoryStatus.PRIVATE;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "memory_id", nullable = false, updatable = false)
    List<Image> images = new ArrayList<>();

    private boolean deleted = false;

    @Embedded
    private Coordinate coordinate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "memory_id", nullable = false, updatable = false)
    private List<MemoryTag> memoryTags = new ArrayList<>();

    public Memory(Long memberId,
                  Title title,
                  Category category,
                  LocalDateTime visitedAt,
                  Star star,
                  Content content,
                  List<Image> images,
                  Coordinate coordinate) {
        this.memberId = memberId;
        this.title = title;
        this.category = category;
        this.visitedAt = visitedAt;
        this.star = star;
        this.content = content;
        this.images = images;
        this.coordinate = coordinate;
    }

    public Memory(
            Long memberId,
            Title title,
            Category category,
            LocalDateTime visitedAt,
            Star star,
            Content content) {
        this(memberId, title, category, visitedAt, star, content, null, new Coordinate());
    }

    public Memory(
            Long memberId,
            Title title,
            Category category,
            LocalDateTime visitedAt,
            Star star,
            Content content,
            List<Image> images) {
        this(memberId, title, category, visitedAt, star, content, images, new Coordinate());
    }

    public String getTitleValue() {
        return title.getTitle();
    }

    public String getCategoryValue() {
        return category.getCategoryName();
    }

    public int getStarValue() {
        return star.getValue();
    }

    public String getLatitudeValue() {
        return coordinate.getLatitude();
    }

    public String getLongitudeValue() {
        return coordinate.getLongitude();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final Memory memory = (Memory) other;
        return Objects.equals(id, memory.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Memory{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", visitedAt=" + visitedAt +
                ", star=" + star +
                ", content='" + content + '\'' +
                ", memoryStatus=" + memoryStatus +
                ", coordinate=" + coordinate +
                '}';
    }
}
