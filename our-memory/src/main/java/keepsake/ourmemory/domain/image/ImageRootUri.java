package keepsake.ourmemory.domain.image;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageRootUri {
    private String imageUri;

    public ImageRootUri(String imageUri) {
        this.imageUri = imageUri;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        ImageRootUri imageRootUri1 = (ImageRootUri) other;
        return Objects.equals(imageUri, imageRootUri1.imageUri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageUri);
    }

    @Override
    public String toString() {
        return "ImageRootUri{" +
                "imageUri='" + imageUri + '\'' +
                '}';
    }
}
