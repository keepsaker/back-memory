package keepsake.ourmemory.domain.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class ImageHandler {
    @Value("${image.directory-path}")
    public String imageRelativePath;

    @Value("${image.web-uri}")
    public String imageRootUri;

    public List<Image> saveAndConvert(List<MultipartFile> multipartFiles) throws IOException {
        if (Objects.isNull(multipartFiles) || multipartFiles.isEmpty()) {
            return Collections.emptyList();
        }
        List<Image> result = new ArrayList<>();
        String absolutePath = new File(imageRelativePath).getAbsolutePath();
        File directory = new File(absolutePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        for (int index = 0; index < multipartFiles.size(); index++) {
            MultipartFile multipartImage = multipartFiles.get(index);
            String imageName = getCurrentTime() + "_" + multipartImage.getOriginalFilename();

            multipartImage.transferTo(new File(absolutePath + "/" + imageName));

            Image resultImage;
            if (index == Image.THUMBNAIL_INDEX) {
                resultImage = new Image(true, new ImageRootUri(imageRootUri), new ImageRootPath(imageRelativePath), new ImageName(imageName));
            } else {
                resultImage = new Image(new ImageRootUri(imageRootUri), new ImageRootPath(imageRelativePath), new ImageName(imageName));
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
