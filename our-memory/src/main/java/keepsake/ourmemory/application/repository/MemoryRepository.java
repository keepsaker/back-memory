package keepsake.ourmemory.application.repository;

import keepsake.ourmemory.domain.memory.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
    List<Memory> findAllByMemberId(Long memberId); // 1 + 1 + 1 번 쿼리가 나갈 것 (쿼리수가 100개 이하 이내에서)
}
