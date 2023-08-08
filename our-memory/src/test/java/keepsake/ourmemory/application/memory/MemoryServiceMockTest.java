package keepsake.ourmemory.application.memory;

import keepsake.ourmemory.application.repository.MemoryRepository;
import keepsake.ourmemory.domain.image.Image;
import keepsake.ourmemory.domain.image.ImageHandler;
import keepsake.ourmemory.domain.image.ImageName;
import keepsake.ourmemory.domain.image.ImagePath;
import keepsake.ourmemory.domain.memory.Category;
import keepsake.ourmemory.domain.memory.Memory;
import keepsake.ourmemory.domain.memory.Star;
import keepsake.ourmemory.ui.dto.request.MemoryCreateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemoryServiceMockTest {

    @Mock
    private MemoryRepository memoryRepository;
    @Mock
    private ImageHandler imageHandler;

    @InjectMocks
    private MemoryService memoryService;

    @Test
    void 추억을_생성한다() throws IOException {
        // given, when
        given(memoryRepository.save(ArgumentMatchers.any()))
                .willReturn(new Memory(null, null, null, null, null, null));
        given(imageHandler.upload(any()))
                .willReturn(List.of(new Image(new ImagePath("path"), new ImageName("name"))));
        MemoryCreateRequest request = new MemoryCreateRequest("title", Category.CAFE.getCategoryName(), LocalDateTime.now(), Star.TWO.getValue(), "content", List.of());

        // then
        assertDoesNotThrow(() -> memoryService.createMemory(1L, request));
    }

    @Test
    void 존재하지_않는_카테고리_추억은_예외가_발생한다() throws IOException {
        // given, when
        given(imageHandler.upload(any()))
                .willReturn(List.of(new Image(new ImagePath("path"), new ImageName("name"))));
        MemoryCreateRequest request = new MemoryCreateRequest("title", "leo", LocalDateTime.now(), Star.TWO.getValue(), "content", List.of());
        // then
        assertThatThrownBy(() -> memoryService.createMemory(1L, request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 카테고리 입니다.");
    }

    @Test
    void 유효한_별점_범위를_벗어나면_예외가_발생한다() throws IOException {
        // given, when
        given(imageHandler.upload(any()))
                .willReturn(List.of(new Image(new ImagePath("path"), new ImageName("name"))));
        MemoryCreateRequest request = new MemoryCreateRequest("title", "leo", LocalDateTime.now(), 6, "content", List.of());

        // then
        assertThatThrownBy(() -> memoryService.createMemory(1L, request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 추억의_머리말은_50자를_넘을_수_없다() throws IOException {
        // given, when
        given(imageHandler.upload(any()))
                .willReturn(List.of(new Image(new ImagePath("path"), new ImageName("name"))));
        String overLengthTitle = "title".repeat(50);
        MemoryCreateRequest request = new MemoryCreateRequest(overLengthTitle, Category.RESTAURANT.getCategoryName(), LocalDateTime.now(), Star.TWO.getValue(), "content", List.of());

        // then
        assertThatThrownBy(() -> memoryService.createMemory(1L, request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("제목은 최대 50자 입니다.");
    }
}
