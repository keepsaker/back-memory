package keepsake.ourmemory.ui.tag;

import keepsake.ourmemory.application.tag.TagService;
import keepsake.ourmemory.application.tag.dto.TagFindResponseDto;
import keepsake.ourmemory.ui.tag.dto.TagCreateRequest;
import keepsake.ourmemory.ui.tag.dto.TagFindResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TagController {

    private final TagService tagService;

    @PostMapping("/tags")
    public ResponseEntity<Void> createTag(@RequestBody TagCreateRequest request) {
        tagService.createTag(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/tags/members/{memberId}")
    public ResponseEntity<TagFindResponse> findTagsByMember(@PathVariable Long memberId) {
        List<TagFindResponseDto> tags = tagService.findTagsByMember(memberId);
        return ResponseEntity.ok(new TagFindResponse(tags));
    }

    @DeleteMapping("tags/{tagId}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long tagId) {
        tagService.deleteTagById(tagId);
        return ResponseEntity.noContent().build();
    }
}
