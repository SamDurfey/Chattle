package com.epicodus.chattle.models;

import java.util.ArrayList;

public class Conversation {
    private ArrayList<Message> messages = new ArrayList<>();
    private String lastMessage;
    private String me;
    private String you;

    public Conversation(ArrayList<Message> messages, String me, String you) {
        this.messages = messages;
        this.me = me;
        this.you = you;
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

    public String getMe() {
        return me;
    }

    public void setMe(String me) {
        this.me = me;
    }

    public String getYou() {
        return you;
    }

    public void setYou(String you) {
        this.you = you;
    }
}
