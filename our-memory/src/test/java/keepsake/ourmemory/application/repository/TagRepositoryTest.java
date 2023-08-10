package keepsake.ourmemory.application.repository;

import keepsake.ourmemory.domain.tag.Tag;
import keepsake.ourmemory.domain.tag.TagColor;
import keepsake.ourmemory.domain.tag.TagName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @Test
    void 멤버의_태그를_조회한다() {
        // given
        Long memberId = 1L;
        Tag tag1 = new Tag(memberId, new TagName("tagName1"), new TagColor("tagColor1"));
        Tag tag2 = new Tag(memberId, new TagName("tagName2"), new TagColor("tagColor2"));
        Tag tag3 = new Tag(2L, new TagName("tagName3"), new TagColor("tagColor3"));

        tagRepository.save(tag1);
        tagRepository.save(tag2);
        tagRepository.save(tag3);

        // when
        List<Tag> tagsByMember = tagRepository.findTagsByMemberId(memberId);

        // then
        assertThat(tagsByMember).usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .containsExactlyInAnyOrder(tag1, tag2);
    }
}
