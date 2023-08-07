package keepsake.ourmemory.application.memory;

import keepsake.ourmemory.api.memory.MemoryCreateRequest;
import keepsake.ourmemory.domain.memory.*;
import keepsake.ourmemory.repository.MemoryRepository;
import keepsake.ourmemory.application.memory.dto.CategoryDto;
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

    public Long createMemory(MemoryCreateRequest request) {
        Title title = new Title(request.getTitle());
        Category category = Category.from(request.getCategory());
        Star star = Star.from(request.getStar());
        Content content = new Content(request.getContent());
        Memory memory = new Memory(1L, title, category, request.getVisitedAt(), star, content);

        memoryRepository.save(memory);

        return memory.getId();
    }
  
    public List<CategoryDto> findCategories() {
        return Arrays.stream(Category.values())
                .map(category -> new CategoryDto(category.getName()))
                .toList();
    }
}
