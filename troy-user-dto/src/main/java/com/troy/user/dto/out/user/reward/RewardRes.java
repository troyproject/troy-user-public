package com.troy.user.dto.out.user.reward;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Han
 */
@Setter
@Getter
public class RewardRes implements Serializable {

    private static final long serialVersionUID = 1543234543626436174L;

    private Boolean ok;
    private String result;
    private String wrong;

    @Override
    public String toString() {
        return "RewardRes{" +
                "ok=" + ok +
                ", result='" + result + '\'' +
                ", wrong='" + wrong + '\'' +
                '}';
    }
}
