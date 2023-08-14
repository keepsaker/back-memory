package keepsake.ourmemory.application.repository;

import keepsake.ourmemory.domain.memory.Memory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
    @EntityGraph(attributePaths = {"images"})
    List<Memory> findAllByMemberId(Long memberId);
}
