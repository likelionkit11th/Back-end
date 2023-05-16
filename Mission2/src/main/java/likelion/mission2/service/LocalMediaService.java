package likelion.mission2.service;

import likelion.mission2.controller.BoardController;
import likelion.mission2.model.MediaDescriptorDto;
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
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private final String basePath = "./media";


    @Override
    public MediaDescriptorDto saveFile(MultipartFile file) {

        return this.saveToDir(file);
    }

    @Override
    public Collection<MediaDescriptorDto> saveFileBulk(MultipartFile[] files) {
        Collection<MediaDescriptorDto> resultList = new ArrayList<>();
        for (MultipartFile file: files){
            resultList.add(this.saveToDir(file));
        }
        return resultList;
    }

    @Override
    public byte[] getFileAsBytes(String resourcePath) {
        try{
            return Files.readAllBytes(Path.of(basePath, resourcePath));
        } catch(IOException e){
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private MediaDescriptorDto saveToDir(MultipartFile file){
        MediaDescriptorDto descriptorDtodto = new MediaDescriptorDto();
        descriptorDtodto.setStatus(200);
        descriptorDtodto.setOriginalName(file.getOriginalFilename());
        try{
            LocalDateTime now = LocalDateTime.now();
            String targetDir = Path.of(
                    basePath,
                    now.format(DateTimeFormatter.BASIC_ISO_DATE)).toString();

            String newFileName = now.format(DateTimeFormatter.ofPattern("HHmmss"))+"_"+file.getOriginalFilename();
            File dirNow = new File(targetDir);
            if(!dirNow.exists()) dirNow.mkdir();
            file.transferTo(Path.of(
                    targetDir,
                    newFileName
            ));
            descriptorDtodto.setResourcePath(Path.of(
                    targetDir,
                    newFileName).toString().substring(1)
            );
            return descriptorDtodto;
        } catch (IOException e){
            logger.error(e.getMessage());
            descriptorDtodto.setMessage("faild");
            descriptorDtodto.setStatus(500);
            return descriptorDtodto;
        }
    }
}
