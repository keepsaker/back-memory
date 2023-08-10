package keepsake.ourmemory.application.repository;

import keepsake.ourmemory.domain.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findTagsByMemberId(Long memberId);
}
