package keepsake.ourmemory.domain.memory;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coordinate {
    private String latitude;
    private String longitude;

    public Coordinate(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final Coordinate that = (Coordinate) other;
        return Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
