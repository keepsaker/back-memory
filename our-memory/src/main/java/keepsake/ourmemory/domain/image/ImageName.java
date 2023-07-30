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
public class ImageName {
    @Column(nullable = false)
    private String imageName;

    public ImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final ImageName imageName1 = (ImageName) other;
        return Objects.equals(imageName, imageName1.imageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageName);
    }

    @Override
    public String toString() {
        return "ImageName{" +
                "imageName='" + imageName + '\'' +
                '}';
    }
}
