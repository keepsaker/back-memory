package keepsake.ourmemory.domain.image;

import keepsake.ourmemory.application.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ImageHandler {
    private static final String IMAGE_CONTROLLER_URL_PATH = "http://13.124.207.219:8080/images/";

    public List<Image> upload(List<MultipartFile> multipartFiles) throws IOException {
        List<Image> result = new ArrayList<>();

        if (Objects.isNull(multipartFiles) || multipartFiles.isEmpty()) {
            return null;
        }

        for (int i = 0; i < multipartFiles.size(); i++) {
            final MultipartFile multipartImage = multipartFiles.get(i);
            String imageName = getCurrentTime() + "_" + multipartImage.getOriginalFilename();
            multipartImage.transferTo(new File(imageName));
            String imagePath = IMAGE_CONTROLLER_URL_PATH + imageName;
            Image resultImage;
            if (i == 0) {
                resultImage = new Image(true, new ImagePath(imagePath), new ImageName(imageName));
            } else {
                resultImage = new Image(new ImagePath(imagePath), new ImageName(imageName));
            }
            result.add(resultImage);
        }
        return result;
    }

    private String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm"));
    }
}
