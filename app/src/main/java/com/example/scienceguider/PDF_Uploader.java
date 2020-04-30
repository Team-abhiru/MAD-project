package com.example.scienceguider;

public class PDF_Uploader {

    public String topic;
    public String subject;
    public String url;
    public String key;

    public PDF_Uploader() {
    }

    public PDF_Uploader(String topic, String subject, String url, String key) {
        this.topic = topic;
        this.subject = subject;
        this.url = url;
        this.key = key;
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

    public String getKey() {
        return key;
    }

    public String setKey(String key) {
        return key;
    }
}


