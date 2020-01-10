package com.troy.user.dto.out.user;

import com.troy.commons.dto.out.ResData;

/**
 * @author Han
 */
public class UserDetailsResData extends ResData {

    private static final long serialVersionUID = -2593610950732572863L;

    private UserDetails details;

    public UserDetailsResData() {

    }

    public UserDetailsResData(UserDetails details) {
        this.details = details;
    }

    public UserDetails getDetails() {
        return details;
    }

    public void setDetails(UserDetails details) {
        this.details = details;
    }
}
