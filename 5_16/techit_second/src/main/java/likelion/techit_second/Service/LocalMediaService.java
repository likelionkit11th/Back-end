package likelion.techit_second.Service;

import likelion.techit_second.model.MediaDescriptorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class LocalMediaService implements MediaService{
    private static final Logger logger = LoggerFactory.getLogger(LocalMediaService.class);
    private final String basePath = "./media";
    @Override
    public MediaDescriptorDTO saveFile(MultipartFile file) {

        return this.saveToDir(file);
    }

    @Override
    public Collection<MediaDescriptorDTO> saveFileBulk(MultipartFile[] files) {
        Collection<MediaDescriptorDTO> resultList = new ArrayList<>();
        for(MultipartFile file: files){
            resultList.add(this.saveToDir(file));
        }
        return resultList;
    }

    @Override
    public byte[] getFileAsBytes(String resourcePath) {
        try{
            return Files.readAllBytes(Path.of(basePath, resourcePath));
        }catch(IOException e){
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private MediaDescriptorDTO saveToDir(MultipartFile file){
        MediaDescriptorDTO descriptorDTO = new MediaDescriptorDTO();
        descriptorDTO.setStatus(200);
        descriptorDTO.setOriginalName(file.getOriginalFilename());
        try{
            LocalDateTime now = LocalDateTime.now();
            String targetDir = Path.of(basePath, now.format(DateTimeFormatter.BASIC_ISO_DATE)).toString();
            String newFileName = now.format(DateTimeFormatter.ofPattern("HHmmss")) +"_" +file.getOriginalFilename();
            File dirNow = new File(targetDir);
            if(!dirNow.exists()) dirNow.mkdirs();
            file.transferTo(Path.of(targetDir, newFileName));
            descriptorDTO.setResourcePath(Path.of(targetDir, newFileName).toString().substring(1));
            return descriptorDTO;
        }catch (IOException e) {
            logger.error(e.getMessage());
            descriptorDTO.setMessage("failed");
            descriptorDTO.setStatus(500);
            return descriptorDTO;
        }
    }
}
