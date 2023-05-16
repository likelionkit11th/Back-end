package com.seoljy.HW42TechitMisson2.model;

public class MediaDescriptorDto {
    private Integer status;
    private String message;
    private String originName;
    private String resourcePath;

    public MediaDescriptorDto() {
    }

    public MediaDescriptorDto(Integer status, String message, String originName, String resourcePath) {
        this.status = status;
        this.message = message;
        this.originName = originName;
        this.resourcePath = resourcePath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public String toString() {
        return "MediaDescriptorDto{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", originName='" + originName + '\'' +
                ", resourcePath='" + resourcePath + '\'' +
                '}';
    }
}
