package com.epicodus.chattle.models;

import java.util.ArrayList;

public class Conversation {
    private ArrayList<Message> messages = new ArrayList<>();
    private String lastMessage;
    private String user1ID;
    private String user2ID;

    public Conversation(ArrayList<Message> messages, String user1ID, String user2ID) {
        this.messages = messages;
        this.user1ID = user1ID;
        this.user2ID = user2ID;
        this.lastMessage = messages.get(messages.size() -1).getBody();
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getUser1ID() {
        return user1ID;
    }

    public void setUser1ID(String me) {
        this.user1ID = me;
    }

    public String getUser2ID() {
        return this.user2ID;
    }

    public void setUser2ID(String you) {
        this.user2ID = you;
    }
}
