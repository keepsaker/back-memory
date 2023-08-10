package keepsake.ourmemory.ui.tag.dto;

import keepsake.ourmemory.application.tag.dto.TagFindResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Getter
public class TagFindResponse {

    private List<TagFindResponseDto> tags;
}
