package keepsake.ourmemory.domain.image;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImagePath {
    @Column(nullable = false)
    private String imagePath;

    public ImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final ImagePath imagePath = (ImagePath) other;
        return Objects.equals(this.imagePath, imagePath.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imagePath);
    }

    @Override
    public String toString() {
        return "ImagePath{" +
                "path='" + imagePath + '\'' +
                '}';
    }
}
