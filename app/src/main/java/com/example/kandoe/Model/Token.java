package com.example.kandoe.Model;

import org.joda.time.DateTime;

/**
 * Created by Michelle on 25-2-2016.
 */
public class Token {
    private String access_token;
    private String token_type;      //Bearer
    private int expires_in;
    private String user;
    private DateTime issued;
    private DateTime expires;

    public Token(String access_token, String token_type, int expires_in, String user, DateTime issued, DateTime expires) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.user = user;
        this.issued = issued;
        this.expires = expires;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getUserName() {
        return user;
    }

    public DateTime getExpires() {
        return expires;
    }
}
