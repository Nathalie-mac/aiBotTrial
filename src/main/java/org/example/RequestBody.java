package org.example;

public class RequestBody {
    public boolean direct_request = true;
    public String text;
    public String user_id;

    public RequestBody(String text, String userId) {
        this.text = text;
        this.user_id = userId;
    }
}