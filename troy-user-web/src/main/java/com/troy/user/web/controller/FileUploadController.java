package com.troy.user.web.controller;

import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResFactory;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.user.api.FileUploadApi;
import com.troy.user.dto.out.fileupload.FileUploadResData;
import com.troy.user.web.fileupload.AwsS3FileUploader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @author zhangchengjie
 * @date 2019/08/02
 */
@RestController
@Api(tags = "文件上传")
public class FileUploadController extends AbstractController implements FileUploadApi {

    @Autowired
    private AwsS3FileUploader awsS3FileUploader;

    @Override
    @ApiOperation(value = "图片上传", notes = "form表单enctype=\"multipart/form-data\", input文件框name=\"file\"")
    public Res<FileUploadResData> uploadPic(MultipartFile file) {
        String awsS3FileUrl = awsS3FileUploader.uploadToS3(file);
        FileUploadResData resData = new FileUploadResData();
        resData.setFileUrl(awsS3FileUrl);
        return ResFactory.getInstance().createRes(StateTypeSuper.SUCCESS_DEFAULT, resData);
    }

}
