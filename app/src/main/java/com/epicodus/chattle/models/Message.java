package com.epicodus.chattle.models;

public class Message {
    private String body;
    private String sender;
    private String recipient;

    public Message(String body, String sender, String recipient) {
        this.body = body;
        this.sender = sender;
        this.recipient = recipient;
    }

    public String getBody() {
        return body;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
