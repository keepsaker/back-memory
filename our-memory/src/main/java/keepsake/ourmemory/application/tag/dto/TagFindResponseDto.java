package keepsake.ourmemory.application.tag.dto;

import keepsake.ourmemory.domain.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TagFindResponseDto {

    private Long tagId;
    private String tagName;
    private String tagColor;

    public static TagFindResponseDto from(Tag tag) {
        return new TagFindResponseDto(
                tag.getId(),
                tag.getTagNameValue(),
                tag.getTagColorValue()
        );
    }
}
