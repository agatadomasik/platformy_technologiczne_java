package org.example;

import java.io.Serializable;

public class Message implements Serializable {
    private int number;
    private String content;
    public Message(int n, String content){
        this.number = n;
        this.content = content;
    }
    public String getContent(){
        return content;
    }
}