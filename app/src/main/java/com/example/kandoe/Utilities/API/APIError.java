package com.example.kandoe.Utilities.API;

/**
 * Created by Thomas on 2016-02-16.
 */

public class APIError {
    private int statusCode;
    private String message;

    public APIError() {
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }
}
