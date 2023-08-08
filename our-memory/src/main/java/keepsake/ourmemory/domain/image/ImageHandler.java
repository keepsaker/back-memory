package keepsake.ourmemory.domain.image;

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
    private static final String IMAGE_CONTROLLER_URL_PATH = "http://13.124.207.219/images/";
    private static final String IMAGE_DIRECTORY_PATH = "/keepsaker/images/";
    public static final int THUMBNAIL_INDEX = 0;

    public List<Image> upload(List<MultipartFile> multipartFiles) throws IOException {
        List<Image> result = new ArrayList<>();
        if (Objects.isNull(multipartFiles) || multipartFiles.isEmpty()) {
            return result;
        }
        File directory = new File(IMAGE_DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        for (int index = 0; index < multipartFiles.size(); index++) {
            final MultipartFile multipartImage = multipartFiles.get(index);
            String imageName = getCurrentTime() + "_" + multipartImage.getOriginalFilename();
            multipartImage.transferTo(new File(IMAGE_DIRECTORY_PATH + imageName));

            Image resultImage;
            if (index == THUMBNAIL_INDEX) {
                resultImage = new Image(true, new ImagePath(IMAGE_CONTROLLER_URL_PATH), new ImageName(imageName));
            } else {
                resultImage = new Image(new ImagePath(IMAGE_CONTROLLER_URL_PATH), new ImageName(imageName));
            }
            result.add(resultImage);
        }
        return result;
    }

    private String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
    }
}
