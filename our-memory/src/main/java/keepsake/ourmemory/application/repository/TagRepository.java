package keepsake.ourmemory.application.repository;

import keepsake.ourmemory.domain.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
