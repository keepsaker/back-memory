package keepsake.ourmemory.ui.dto.response;

public record LocationResponse(String latitude, String longitude) {
    public static LocationResponse of(final String latitude, final String longitude) {
        return new LocationResponse(latitude, longitude);
    }
}
