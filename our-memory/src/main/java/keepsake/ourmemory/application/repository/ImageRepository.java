package keepsake.ourmemory.application.repository;

import keepsake.ourmemory.domain.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
