package keepsake.ourmemory.application.memory;

import keepsake.ourmemory.domain.memory.Category;
import keepsake.ourmemory.domain.memory.Star;
import keepsake.ourmemory.ui.dto.request.MemoryCreateRequest;
import keepsake.ourmemory.ui.dto.response.MemoriesResponse;
import keepsake.ourmemory.ui.dto.response.SingleMemoryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Transactional
@SpringBootTest
class MemoryServiceTest {
    @Autowired
    private MemoryService memoryService;

    @Test
    @Transactional
    void 추억을_조회한다() {
        // given
        MemoryCreateRequest request1 = new MemoryCreateRequest("title1", Category.CAFE.getCategoryName(), LocalDateTime.now(), Star.TWO.getValue(), "content1", List.of("image"));
        MemoryCreateRequest request2 = new MemoryCreateRequest("title2", Category.RESTAURANT.getCategoryName(), LocalDateTime.now(), Star.FIVE.getValue(), "content2", List.of("image"));
        memoryService.createMemory(1L, request1);
        memoryService.createMemory(1L, request2);

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

    @Test
    @Transactional
    void 하나의_추억을_조회한다() {
        // given
        MemoryCreateRequest request = new MemoryCreateRequest("title1", Category.CAFE.getCategoryName(), LocalDateTime.now(), Star.TWO.getValue(), "content1", List.of("image"));
        Long memoryId = memoryService.createMemory(1L, request);

        // when
        SingleMemoryResponse response = memoryService.getMemory(1L, memoryId);

        // then
        assertAll(
                () -> assertThat(response.star()).isEqualTo(request.getStar()),
                () -> assertThat(response.title()).isEqualTo(request.getTitle())
        );
    }
}
