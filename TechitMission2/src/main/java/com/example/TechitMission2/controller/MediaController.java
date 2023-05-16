package com.example.TechitMission2.controller;

import com.example.TechitMission2.model.MediaDescriptorDto;
import com.example.TechitMission2.service.MediaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

@RestController
@RequestMapping("media")
@RequiredArgsConstructor
public class MediaController {
    private static final Logger logger = LoggerFactory.getLogger(MediaController.class);
    private final MediaService mediaService;

    @PostMapping("upload")
    public ResponseEntity<MediaDescriptorDto> uploadMedia(
        @RequestParam("file") MultipartFile file){
        MediaDescriptorDto descriptorDto = this.mediaService.saveFile(file);
        return ResponseEntity
                .status(descriptorDto.getStatus())
                .body(descriptorDto);
    }

    @PostMapping("upload-bulk")
    public ResponseEntity<Collection<MediaDescriptorDto>> uploadMediaBulk(
            @RequestParam("file") MultipartFile[] files
    ){
        return ResponseEntity
                .status(HttpStatus.MULTI_STATUS)
                .body(this.mediaService.saveFileBulk(files));
    }

    @GetMapping("**")
    public ResponseEntity<byte[]> staticLikeEndpoint(HttpServletRequest request) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            return new ResponseEntity<>(
                    this.mediaService.getFileAsBytes(request.getRequestURI().split("media")[1]),
                    headers,
                    HttpStatus.OK
            );
    }
}
