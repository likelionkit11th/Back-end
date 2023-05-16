package com.example.TechitMission2.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MediaDescriptorDto {
    private Integer status;
    private String message;
    private String originalName;
    private String resourcePath;
}
