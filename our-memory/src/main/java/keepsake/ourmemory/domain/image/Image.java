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
    public static final String IMAGE_DIRECTORY_PATH = "C:/Users/bs860/IdeaProjects/woo/project/back-memory/our-memory/images/";
    public static final String IMAGE_CONTROLLER_URL_PATH = "localhost:8080/images/";
    public static final int THUMBNAIL_INDEX = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  /*  @Column(nullable = false)
    private Long memoryId;*/

    private boolean thumbnail = false;

    @Embedded
    private ImagePath imagePath;

    @Embedded
    private ImageName imageName;

    public Image(ImagePath imagePath, ImageName imageName) {
        this.imagePath = imagePath;
        this.imageName = imageName;
    }

    public Image(boolean thumbnail, ImagePath imagePath, ImageName imageName) {
        this.thumbnail = thumbnail;
        this.imagePath = imagePath;
        this.imageName = imageName;
    }

    public String getUri() {
        return imagePath.getImagePath() + imageName.getImageName();
    }

    public String getPath() {
        return IMAGE_DIRECTORY_PATH + imageName.getImageName();
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
                ", imagePath=" + imagePath +
                ", imageName=" + imageName +
                '}';
    }
}
