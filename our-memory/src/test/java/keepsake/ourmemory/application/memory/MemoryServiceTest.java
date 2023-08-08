package keepsake.ourmemory.application.memory;

import keepsake.ourmemory.application.repository.MemoryRepository;
import keepsake.ourmemory.domain.memory.Category;
import keepsake.ourmemory.domain.memory.Content;
import keepsake.ourmemory.domain.memory.Memory;
import keepsake.ourmemory.domain.memory.Star;
import keepsake.ourmemory.domain.memory.Title;
import keepsake.ourmemory.ui.dto.response.MemoriesResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Transactional
@SpringBootTest
public class MemoryServiceTest {
    @Autowired
    private MemoryService memoryService;
    @Autowired
    private MemoryRepository memoryRepository;

    @Test
    void 추억을_조회한다() throws IOException {
        // given
        final Memory memory1 = new Memory(1L, new Title("title1"), Category.CAFE, LocalDateTime.now(), Star.TWO, new Content("content1"));
        final Memory memory2 = new Memory(1L, new Title("title2"), Category.RESTAURANT, LocalDateTime.now(), Star.FIVE, new Content("content2"));
        memoryRepository.save(memory1);
        memoryRepository.save(memory2);

        // when
        final MemoriesResponse memories = memoryService.getMemories(1L);

        // then
        assertAll(
                () -> assertThat(memories.memories()).hasSize(2),
                () -> assertThat(memories.memories().get(0).title()).isEqualTo("title1"),
                () -> assertThat(memories.memories().get(0).category()).isEqualTo("cafe"),
                () -> assertThat(memories.memories().get(0).star()).isEqualTo(2),
                () -> assertThat(memories.memories().get(0).visitedAt()).isNotNull()
        );
    }
}
