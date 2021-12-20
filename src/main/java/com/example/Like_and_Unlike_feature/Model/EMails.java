package com.example.Like_and_Unlike_feature.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "emails")
public class EMails {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String to;
        private String subject;
        private String message;

    public EMails() {
    }

    public EMails(Long id, String to, String subject, String message) {
        this.id = id;
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "EmailRequest{" +
                    "to='" + to + '\'' +
                    ", subject='" + subject + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

