package com.seoljy.HW42TechitMisson2.service;

import com.seoljy.HW42TechitMisson2.model.MediaDescriptorDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface MediaService {
    MediaDescriptorDto saveFile(MultipartFile file);
    Collection<MediaDescriptorDto> saveFileBulk(MultipartFile[] files);
    byte[] getFileAsBytes(String resourcePath);
}
