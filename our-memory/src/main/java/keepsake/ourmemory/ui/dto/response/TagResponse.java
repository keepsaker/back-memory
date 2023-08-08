package keepsake.ourmemory.ui.dto.response;

import keepsake.ourmemory.domain.tag.MemoryTag;

public record TagResponse(Long id, String name, String color) {
    public static TagResponse from(final MemoryTag memoryTag) {
        return new TagResponse(
                memoryTag.getTag().getId(),
                memoryTag.getTag().getTagNameValue(),
                memoryTag.getTag().getTagColorValue()
        );
    }
}
