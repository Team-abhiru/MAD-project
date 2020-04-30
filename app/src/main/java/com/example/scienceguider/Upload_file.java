package com.example.scienceguider;

public class Upload_file {

    String fileName;
    String ID;
    String url;

    public Upload_file() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Upload_file(String fileName, String ID, String url) {
        this.fileName = fileName;
        this.ID = ID;
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
