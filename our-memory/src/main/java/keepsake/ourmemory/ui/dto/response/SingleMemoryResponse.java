package keepsake.ourmemory.ui.dto.response;

import keepsake.ourmemory.domain.memory.Memory;

import java.time.LocalDateTime;
import java.util.List;

public record SingleMemoryResponse(String title,
                                   String category,
                                   List<TagResponse> tags,
                                   LocalDateTime visitedAt,
                                   int star,
                                   LocationResponse location) {
    public static SingleMemoryResponse from(Memory memory) {
        return new SingleMemoryResponse(
                memory.getTitleValue(),
                memory.getCategoryValue(),
                getTagResponses(memory),
                memory.getVisitedAt(),
                memory.getStarValue(),
                // TODO : image 추가
                LocationResponse.of(memory.getLatitudeValue(), memory.getLongitudeValue())
        );
    }

    private static List<TagResponse> getTagResponses(Memory memory) {
        return memory.getMemoryTags()
                .stream()
                .map(TagResponse::from)
                .toList();
    }
}
