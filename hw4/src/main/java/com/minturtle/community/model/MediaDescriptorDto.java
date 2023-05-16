package com.minturtle.community.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MediaDescriptorDto {
    private Integer status;
    private String message;
    private String originalName;
    private String resourcePath;
}
