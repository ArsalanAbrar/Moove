package com.example.arsalanabrar.moove_assignment;

public class Comment_data {
    private String  body, time,avatar_url;

    public Comment_data(String body, String time,String avatar_url) {
        this.body = body;
        this.time = time;
        this.avatar_url=avatar_url;

    }
    public String getTime() {
        return time;
    }

    public void setTime(String time1) {
        this.time = time1;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String genre) {
        this.body = genre;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
