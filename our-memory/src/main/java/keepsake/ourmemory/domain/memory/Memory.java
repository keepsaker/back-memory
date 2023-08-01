package keepsake.ourmemory.domain.memory;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import keepsake.ourmemory.domain.BaseEntity;
import keepsake.ourmemory.domain.tag.MemoryTag;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Embedded
    private Title title;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDateTime visitedAt;

    @Enumerated(EnumType.STRING)
    private Star star;

    @Embedded
    private Content content;

    @Enumerated(EnumType.STRING)
    private MemoryStatus memoryStatus;

    private boolean deleted = false;

    // TODO : 내부 필드값이 모두 null이면 coordinate 객체 자체가 null이 되는 문제
    @Embedded
    private Coordinate coordinate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "memory_id", nullable = false, updatable = false)
    private List<MemoryTag> memoryTags = new ArrayList<>();

    public Memory(Long memberId,
                  Title title,
                  Category category,
                  LocalDateTime visitedAt,
                  Star star,
                  Content content,
                  MemoryStatus memoryStatus,
                  Coordinate coordinate) {
        this.memberId = memberId;
        this.title = title;
        this.category = category;
        this.visitedAt = visitedAt;
        this.star = star;
        this.content = content;
        this.memoryStatus = memoryStatus;
        this.coordinate = coordinate;
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
