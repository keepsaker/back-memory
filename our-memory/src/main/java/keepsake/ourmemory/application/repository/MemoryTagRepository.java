package keepsake.ourmemory.application.repository;

import keepsake.ourmemory.domain.tag.MemoryTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryTagRepository extends JpaRepository<MemoryTag, Long> {
}
