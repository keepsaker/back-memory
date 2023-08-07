package keepsake.ourmemory.repository;

import keepsake.ourmemory.domain.memory.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryRepository extends JpaRepository<Memory, Long> {

}
