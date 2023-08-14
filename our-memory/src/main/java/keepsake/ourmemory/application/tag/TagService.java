package keepsake.ourmemory.application.tag;

import keepsake.ourmemory.application.repository.TagRepository;
import keepsake.ourmemory.application.tag.dto.TagFindResponseDto;
import keepsake.ourmemory.domain.tag.Tag;
import keepsake.ourmemory.domain.tag.TagColor;
import keepsake.ourmemory.domain.tag.TagName;
import keepsake.ourmemory.ui.tag.dto.TagCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;

    public void createTag(TagCreateRequest request) {
        TagName tagName = new TagName(request.getTagName());
        TagColor tagColor = new TagColor(request.getTagColor());
        Long memberId = request.getMemberId();

        Tag tag = new Tag(memberId, tagName, tagColor);
        tagRepository.save(tag);
    }

    @Transactional(readOnly = true)
    public List<TagFindResponseDto> findTagsByMember(Long memberId) {
        List<Tag> tags = tagRepository.findTagsByMemberIdAndDeletedIsFalse(memberId);
        return tags.stream()
                .map(TagFindResponseDto::from)
                .toList();
    }

    public void deleteTagById(Long tagId) {
        tagRepository.deleteById(tagId);
    }
}
