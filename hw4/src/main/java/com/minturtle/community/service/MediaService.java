package com.minturtle.community.service;

import com.minturtle.community.model.MediaDescriptorDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface MediaService {

    MediaDescriptorDto saveFile(MultipartFile file);
    Collection<MediaDescriptorDto> saveFileBulk(MultipartFile[] file);
    byte[] getFileAsBytes(String resourcePath);
}
