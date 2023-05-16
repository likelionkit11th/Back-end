package com.minturtle.community.controller;

import com.minturtle.community.model.MediaDescriptorDto;
import com.minturtle.community.service.MediaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;


    @PostMapping("upload")
    public ResponseEntity<MediaDescriptorDto> uploadMedia(@RequestParam MultipartFile file){
        final MediaDescriptorDto dto = mediaService.saveFile(file);

        return ResponseEntity.status(dto.getStatus()).body(dto);
    }


    @PostMapping("upload-bulk")
    public ResponseEntity<Collection<MediaDescriptorDto>> uploadMedias(@RequestParam("file") MultipartFile[] files){


        return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(mediaService.saveFileBulk(files));
    }

    @GetMapping("**")
    public byte[] staticLikeEndPoint(HttpServletRequest req, HttpServletResponse res){
        res.setContentType(MediaType.IMAGE_PNG_VALUE);
        return mediaService.getFileAsBytes(req.getRequestURI().split("media")[1]);

    }
}
