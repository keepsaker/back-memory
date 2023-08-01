package keepsake.ourmemory.application;

import keepsake.ourmemory.application.repository.MemoryRepository;
import keepsake.ourmemory.domain.memory.Memory;
import keepsake.ourmemory.ui.dto.LocationResponse;
import keepsake.ourmemory.ui.dto.MemoriesResponse;
import keepsake.ourmemory.ui.dto.MemoryResponse;
import keepsake.ourmemory.ui.dto.TagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemoryService {
    private final MemoryRepository memoryRepository;

    public MemoriesResponse getMemories(Long memberId) {
//        List<Memory> memories = memoryRepository.findAllByMemberId(memberId);
        List<Memory> memories = memoryRepository.findAll();
        List<MemoryResponse> responses = memories.stream()
                .map(memory -> new MemoryResponse(
                        memory.getTitleValue(),
                        memory.getCategoryValue(),
                        memory.getMemoryTags()
                                .stream()
                                .map(memoryTag -> new TagResponse(
                                        memoryTag.getTag().getId(),
                                        memoryTag.getTag().getTagNameValue(),
                                        memoryTag.getTag().getTagColorValue())
                                )
                                .toList(),
                        memory.getVisitedAt(),
                        memory.getStarValue(),
                        // TODO : image 추가
                        new LocationResponse(
                                memory.getLatitudeValue(),
                                memory.getLongitudeValue()
                        )
                ))
                .toList();

        return new MemoriesResponse(responses);
    }
}
