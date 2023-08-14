package keepsake.ourmemory.application.tag;

import keepsake.ourmemory.application.repository.TagRepository;
import keepsake.ourmemory.domain.tag.Tag;
import keepsake.ourmemory.domain.tag.TagColor;
import keepsake.ourmemory.domain.tag.TagName;
import keepsake.ourmemory.ui.tag.dto.TagCreateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @InjectMocks
    private TagService tagService;

    @Mock
    private TagRepository tagRepository;

    @Test
    void 태그를_생성한다() {
        Tag dummyTag = makeDummyTag();
        given(tagRepository.save(any()))
                .willReturn(dummyTag);

        TagCreateRequest request = new TagCreateRequest(1L, "tagName", "tagColor");
        assertDoesNotThrow(() -> tagService.createTag(request));
    }

    private Tag makeDummyTag() {
        return new Tag(1L, new TagName("tagNAme"), new TagColor("tagColor"));
    }

}
