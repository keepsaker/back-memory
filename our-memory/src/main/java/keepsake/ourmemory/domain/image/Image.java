package keepsake.ourmemory.domain.image;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import keepsake.ourmemory.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends BaseEntity {

    public static final int THUMBNAIL_INDEX = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean thumbnail = false;

    @Embedded
    private ImageRootUri imageRootUri;

    @Embedded
    private ImageRootPath imageRootPath;

    @Embedded
    private ImageName imageName;

    public Image(ImageRootUri imageRootUri, ImageRootPath imageRootPath, ImageName imageName) {
        this(false, imageRootUri, imageRootPath, imageName);
    }

    public Image(boolean thumbnail, ImageRootUri imageRootUri, ImageRootPath imageRootPath, ImageName imageName) {
        this.thumbnail = thumbnail;
        this.imageRootUri = imageRootUri;
        this.imageRootPath = imageRootPath;
        this.imageName = imageName;
    }

    public String getOriginalUri() {
        return imageRootUri.getImageUri() + imageName.getImageName();
    }

    public String getOriginalPath() {
        return imageRootPath.getImagePath() + imageName.getImageName();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final Image image = (Image) other;
        return Objects.equals(id, image.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", thumbnail=" + thumbnail +
                ", imageUri=" + imageRootUri +
                ", imagePath=" + imageRootPath +
                ", imageName=" + imageName +
                '}';
    }
}
