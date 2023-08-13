package keepsake.ourmemory.application.memory;

import keepsake.ourmemory.application.memory.dto.CategoryDto;
import keepsake.ourmemory.application.repository.MemoryRepository;
import keepsake.ourmemory.domain.memory.*;
import keepsake.ourmemory.ui.dto.request.MemoryCreateRequest;
import keepsake.ourmemory.ui.dto.response.MemoriesResponse;
import keepsake.ourmemory.ui.dto.response.MemoryResponse;
import keepsake.ourmemory.ui.dto.response.SingleMemoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class MemoryService {
    private final MemoryRepository memoryRepository;

    public Long createMemory(Long memberId, MemoryCreateRequest request) {
        Title title = new Title(request.getTitle());
        Category category = Category.from(request.getCategory());
        Star star = Star.from(request.getStar());
        Content content = new Content(request.getContent());
        Memory memory = new Memory(memberId, title, category, request.getVisitedAt(), star, content);

        memoryRepository.save(memory);

        return memory.getId();
    }

    public List<CategoryDto> findCategories() {
        return Arrays.stream(Category.values())
                .map(category -> new CategoryDto(category.getCategoryName()))
                .toList();
    }

    @Transactional(readOnly = true)
    public MemoriesResponse getMemories(Long memberId) {
//        List<Memory> memories = memoryRepository.findAllByMemberId(memberId);
        List<Memory> memories = memoryRepository.findAll();
        List<MemoryResponse> responses = memories.stream()
                .map(MemoryResponse::from)
                .toList();

        return new MemoriesResponse(responses);
    }

    @Transactional(readOnly = true)
    public SingleMemoryResponse getMemory(Long memberId, Long memoryId) {
        // TODO: 인가 필요
        Memory memory = memoryRepository.findById(memoryId)
                .orElseThrow(IllegalArgumentException::new);
        return SingleMemoryResponse.from(memory);
    }
}
