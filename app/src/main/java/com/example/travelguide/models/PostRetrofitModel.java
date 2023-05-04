package com.example.travelguide.models;


public class PostRetrofitModel {
    String Reply;

    public PostRetrofitModel() {
    }

    public PostRetrofitModel(String reply) {
        Reply = reply;
    }

    public String getReply() {
        return Reply;
    }

    public void setReply(String reply) {
        Reply = reply;
    }
}
