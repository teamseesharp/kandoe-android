package com.example.kandoe.Model;

import org.joda.time.DateTime;

/**
 * Created by JoachimDs on 15/05/2015.
 */
public class Token {
    private String access_token;
    private String token_type;      //Bearer
    private int expires_in;
    private String userName;
    private DateTime issued;
    private DateTime expires;

    public Token(String access_token, String token_type, int expires_in, String userName, DateTime issued, DateTime expires) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.userName = userName;
        this.issued = issued;
        this.expires = expires;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getUserName() {
        return userName;
    }

    public DateTime getExpires() {
        return expires;
    }
}
