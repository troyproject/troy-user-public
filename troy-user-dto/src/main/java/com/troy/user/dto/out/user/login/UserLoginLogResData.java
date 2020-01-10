package com.troy.user.dto.out.user.login;

import com.troy.commons.dto.out.ResData;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Han
 */
@Setter
@Getter
public class UserLoginLogResData extends ResData {

    private static final long serialVersionUID = -2593632150732572863L;

    private List<UserLoginLogDetails> list;
    private  Long total;

    public UserLoginLogResData() {

    }
    public UserLoginLogResData(List<UserLoginLogDetails> list) {
        this.list = list;
    }
    public UserLoginLogResData(List<UserLoginLogDetails> list,Long total) {
        this.total=total;
        this.list = list;
    }
}
