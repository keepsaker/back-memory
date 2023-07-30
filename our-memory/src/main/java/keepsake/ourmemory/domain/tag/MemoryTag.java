package keepsake.ourmemory.domain.tag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import keepsake.ourmemory.domain.BaseEntity;
import keepsake.ourmemory.domain.memory.Memory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoryTag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memory_id")
    private Memory memory;

    @Column(nullable = false)
    private Long tagId;

    public MemoryTag(Memory memory, Long tagId) {
        this.memory = memory;
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final MemoryTag memoryTag = (MemoryTag) other;
        return Objects.equals(id, memoryTag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MemoryTag{" +
                "id=" + id +
                ", memory=" + memory +
                ", tagId=" + tagId +
                '}';
    }
}
