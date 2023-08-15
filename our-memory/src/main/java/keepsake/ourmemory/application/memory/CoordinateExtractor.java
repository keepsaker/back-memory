package keepsake.ourmemory.application.memory;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.exif.GpsDirectory;
import keepsake.ourmemory.domain.image.Image;
import keepsake.ourmemory.domain.memory.Coordinate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CoordinateExtractor {

    public static Coordinate extractCoordinate(final Image image) throws IOException {
        File file = new File(image.getOriginalPath());
        try {
            GpsDirectory gpsDirectory = ImageMetadataReader.readMetadata(file).getFirstDirectoryOfType(GpsDirectory.class);
            if (gpsDirectory.containsTag(GpsDirectory.TAG_LATITUDE) && gpsDirectory.containsTag(GpsDirectory.TAG_LONGITUDE)) {
                String latitude = String.valueOf(gpsDirectory.getGeoLocation().getLatitude());
                String longitude = String.valueOf(gpsDirectory.getGeoLocation().getLongitude());
                return Coordinate.of(latitude, longitude);
            }
        } catch (ImageProcessingException e) {
            return Coordinate.empty();
        }
        return Coordinate.empty();
    }
}
