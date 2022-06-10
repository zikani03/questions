package me.zikani.interviews.models;

public class ErrorResponse {
    private String resource;
    private String message;

    public ErrorResponse(String resource, String message) {
        this.resource = resource;
        this.message = message;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


