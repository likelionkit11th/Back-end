package likelion.techit_second.Service;

import likelion.techit_second.model.MediaDescriptorDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface MediaService {
    MediaDescriptorDTO saveFile(MultipartFile file);
    Collection<MediaDescriptorDTO> saveFileBulk(MultipartFile[] files);
    byte[] getFileAsBytes(String resourcePath);
}
