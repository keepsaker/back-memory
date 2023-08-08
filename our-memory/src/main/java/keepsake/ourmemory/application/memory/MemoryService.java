package keepsake.ourmemory.application.memory;

import keepsake.ourmemory.application.memory.dto.CategoryDto;
import keepsake.ourmemory.application.repository.MemoryRepository;
import keepsake.ourmemory.domain.image.Image;
import keepsake.ourmemory.domain.image.ImageHandler;
import keepsake.ourmemory.domain.memory.Category;
import keepsake.ourmemory.domain.memory.Content;
import keepsake.ourmemory.domain.memory.Memory;
import keepsake.ourmemory.domain.memory.Star;
import keepsake.ourmemory.domain.memory.Title;
import keepsake.ourmemory.ui.dto.request.MemoryCreateRequest;
import keepsake.ourmemory.ui.dto.response.MemoriesResponse;
import keepsake.ourmemory.ui.dto.response.MemoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemoryService {
    private final MemoryRepository memoryRepository;
    private final ImageHandler imageHandler;

    public Long createMemory(Long memberId, MemoryCreateRequest request) throws IOException {
        List<Image> images = imageHandler.upload(request.getImages());
        Title title = new Title(request.getTitle());
        Category category = Category.from(request.getCategory());
        Star star = Star.from(request.getStar());
        Content content = new Content(request.getContent());
        Memory memory = new Memory(memberId, title, category, request.getVisitedAt(), star, content, images);

        memoryRepository.save(memory);

        return memory.getId();
    }

    @Transactional(readOnly = true)
    public List<CategoryDto> findCategories() {
        return Arrays.stream(Category.values())
                .map(category -> new CategoryDto(category.getCategoryName()))
                .toList();
    }

    @Transactional(readOnly = true)
    public MemoriesResponse getMemories(Long memberId) {
        List<Memory> memories = memoryRepository.findAllByMemberId(memberId);
        List<MemoryResponse> responses = memories.stream()
                .map(MemoryResponse::from)
                .toList();

        return new MemoriesResponse(responses);
    }
}
