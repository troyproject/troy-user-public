package com.troy.user.api;

import com.troy.commons.dto.out.Res;
import com.troy.user.api.constants.Constants;
import com.troy.user.dto.out.fileupload.FileUploadResData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传服务
 *
 * @author zhangchengjie
 * @date 2019/08/02
 */
public interface FileUploadApi {

    /**
     * 图片上传
     *
     * @param file
     * @return
     */
    @RequestMapping(value = Constants.URL_FILE_UPLOAD_PIC, method = {RequestMethod.POST})
    Res<FileUploadResData> uploadPic(MultipartFile file);

}
