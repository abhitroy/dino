package org.viciousanddelicious.viciousdelicious;

/**
 * Created by rahul on 15/4/18.
 */

public class user_model {
    private String user;
    private String email;


    public user_model(String user, String email) {
        this.user=user;
        this.email=email;

        // Initialize to current time

    }
    public String getUser()
    {
        return user;
    }
    public String getEmail()
    {
     return email;
    }
    public void setUser(String user)
    {
        this.user=user;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }

}
