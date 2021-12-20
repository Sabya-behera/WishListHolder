package com.example.Like_and_Unlike_feature.Service;

public interface EmailService {
    public boolean sendEmail(String subject, String message, String to);

    boolean sendWithAttachment(String subject, String message, String to);
}
