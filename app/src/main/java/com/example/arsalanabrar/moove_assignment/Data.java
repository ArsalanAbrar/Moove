package com.example.arsalanabrar.moove_assignment;

public class Data
{
    private String title, body, time,avatar_url,comments_url;

    public Data() {
    }

    public Data(String title, String body, String time,String avatar_url,String comments_url) {
        this.title = title;
        this.body = body;
        this.time = time;
        this.avatar_url=avatar_url;
        this.comments_url=comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
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
