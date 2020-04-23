package com.example.scienceguider;

public class PDF_Uploader {

    public String topic;
    public String subject;
    public String url;

    public PDF_Uploader() {
    }

    public PDF_Uploader(String topic, String subject, String url) {
        this.topic = topic;
        this.subject = subject;
        this.url = url;
    }

    public String getTopic() {
        return topic;
    }

    public String getSubject() {
        return subject;
    }

    public String getUrl() {
        return url;
    }
}
