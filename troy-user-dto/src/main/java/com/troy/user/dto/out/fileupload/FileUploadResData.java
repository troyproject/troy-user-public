package com.troy.user.dto.out.fileupload;

import com.troy.commons.dto.out.ResData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


/**
 * @author zhangchengjie
 * @date 2019/08/02
 */
@Setter
@Getter
public class FileUploadResData extends ResData {

    private static final long serialVersionUID = 1L;

    /**
     * 文件访问url
     */
    private String fileUrl;
}
