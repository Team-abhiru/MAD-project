package com.example.scienceguider;

import android.widget.EditText;

public class uploadPDF {

    public String url;
    public String email;
    public String subject;
    public String topic;

    public uploadPDF(String s, String toString, String string){

    }

    public uploadPDF(String toString, String s, String email, String url){
        this.email = email;
        this.url = url;
        this.subject = subject;
        this.topic = topic;
    }

    public uploadPDF(EditText editEmail) {
    }

    public String getName(){

        return email;
    }

    public String getUrl(){

        return url;
    }

    public String getSubject(){
        return subject;
    }

    public String getTopic(){
        return topic;
    }


}
