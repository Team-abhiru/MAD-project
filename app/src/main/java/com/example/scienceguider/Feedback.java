package com.example.scienceguider;

public class Feedback {

    private String comment;
    private String subject;
    private String feedback;
    private String feedbackID;

    public Feedback() {
    }

    public Feedback(String comment, String subject, String feedback, String feedbackID) {
        this.comment = comment;
        this.subject = subject;
        this.feedback = feedback;
        this.feedbackID = feedbackID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(String feedbackID) {
        this.feedbackID = feedbackID;
    }
}
