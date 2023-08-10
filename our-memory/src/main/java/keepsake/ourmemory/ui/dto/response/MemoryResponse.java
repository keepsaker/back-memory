package keepsake.ourmemory.ui.dto.response;

import keepsake.ourmemory.domain.image.Image;
import keepsake.ourmemory.domain.memory.Memory;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public record MemoryResponse(String title,
                             String category,
                             List<TagResponse> tags,
                             LocalDateTime visitedAt,
                             int star,
                             List<String> images,
                             LocationResponse location) {
    public static MemoryResponse from(Memory memory) {
        return new MemoryResponse(
                memory.getTitleValue(),
                memory.getCategoryValue(),
                getTagResponses(memory),
                memory.getVisitedAt(),
                memory.getStarValue(),
                extractImages(memory),
                LocationResponse.of(memory.getLatitudeValue(), memory.getLongitudeValue())
        );
    }

    private static List<TagResponse> getTagResponses(Memory memory) {
        return memory.getMemoryTags()
                .stream()
                .map(TagResponse::from)
                .toList();
    }

    private static List<String> extractImages(Memory memory) {
        if (memory.getImages() == null || memory.getImages().isEmpty()) {
            return Collections.emptyList();
        }
        return memory.getImages()
                .stream()
                .map(Image::getOriginalUri)
                .toList();
    }
}
