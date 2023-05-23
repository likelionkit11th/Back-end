package likelion.techit_second.model;

public class MediaDescriptorDTO {
    private Integer status;
    private String message;
    private String originalName;
    private String resourcePath;

    public MediaDescriptorDTO() {
    }

    public MediaDescriptorDTO(Integer status, String message, String originalName, String resourcePath) {
        this.status = status;
        this.message = message;
        this.originalName = originalName;
        this.resourcePath = resourcePath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public String toString() {
        return "MediaDescriptorDTO{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", originalName='" + originalName + '\'' +
                ", resourcePath='" + resourcePath + '\'' +
                '}';
    }
}
