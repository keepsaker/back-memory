package keepsake.ourmemory.application.memory;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.exif.GpsDirectory;
import keepsake.ourmemory.application.memory.dto.CategoryDto;
import keepsake.ourmemory.application.repository.MemoryRepository;
import keepsake.ourmemory.domain.image.Image;
import keepsake.ourmemory.domain.image.ImageHandler;
import keepsake.ourmemory.domain.memory.Category;
import keepsake.ourmemory.domain.memory.Content;
import keepsake.ourmemory.domain.memory.Coordinate;
import keepsake.ourmemory.domain.memory.Memory;
import keepsake.ourmemory.domain.memory.Star;
import keepsake.ourmemory.domain.memory.Title;
import keepsake.ourmemory.ui.dto.request.MemoryCreateRequest;
import keepsake.ourmemory.ui.dto.response.MemoriesResponse;
import keepsake.ourmemory.ui.dto.response.MemoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
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
        Coordinate coordinate = extractCoordinate(images.get(0));
        Title title = new Title(request.getTitle());
        Category category = Category.from(request.getCategory());
        Star star = Star.from(request.getStar());
        Content content = new Content(request.getContent());
        Memory memory;
        if (images == null || images.isEmpty()) {
            memory = new Memory(memberId, title, category, request.getVisitedAt(), star, content, images);
        } else {
            memory = new Memory(memberId, title, category, request.getVisitedAt(), star, content, images, coordinate);
        }
        memoryRepository.save(memory);
        return memory.getId();
    }

    private Coordinate extractCoordinate(final Image image) throws IOException {
        File file = new File(image.getPath());
        try {
            GpsDirectory gpsDirectory = ImageMetadataReader.readMetadata(file).getFirstDirectoryOfType(GpsDirectory.class);
            if (gpsDirectory.containsTag(GpsDirectory.TAG_LATITUDE) && gpsDirectory.containsTag(GpsDirectory.TAG_LONGITUDE)) {
                String latitude = String.valueOf(gpsDirectory.getGeoLocation().getLatitude());
                String longitude = String.valueOf(gpsDirectory.getGeoLocation().getLongitude());
                return Coordinate.of(latitude, longitude);
            }
        } catch (ImageProcessingException e) {
            throw new RuntimeException(e);
        }
        return Coordinate.empty();
    }

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
