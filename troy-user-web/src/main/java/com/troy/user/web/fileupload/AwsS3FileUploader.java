package com.troy.user.web.fileupload;

import cn.hutool.core.util.IdUtil;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.service.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.sql.rowset.serial.SerialException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * AWS S3 对象存储工具
 *
 * @author zhangchengjie
 * @date 2019/08/02
 */
@Service
@Slf4j
public class AwsS3FileUploader implements InitializingBean {

    @Value("${troy.aws.s3.awsAccessKey}")
    private String awsAccessKey;

    @Value("${troy.aws.s3.awsSecretKey}")
    private String awsSecretKey;

    @Value("${troy.aws.s3.bucketName}")
    private String bucketName;

    private AmazonS3 amazonS3;

//    @PostConstruct
//    public void init() {
//
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 初始化 AmazonS3Client
        this.amazonS3 = new AmazonS3Client(new BasicAWSCredentials(awsAccessKey, awsSecretKey));
        Region northeastRegion = Region.getRegion(Regions.AP_NORTHEAST_1);
        this.amazonS3.setRegion(northeastRegion);
    }

    /**
     * 文件上传
     *
     * @param file           目标文件
     * @param remoteFileName 远程访问文件名称
     * @return 远程访问url, url不预签名
     */
    public String uploadToS3(File file, String remoteFileName) {
        // 上传文件
        this.amazonS3.putObject(new PutObjectRequest(bucketName, remoteFileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        // 获取一个request
        GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(
                bucketName, remoteFileName);

        // 生成公用的url
        URL awsS3FileUrl = this.amazonS3.getUrl(bucketName, remoteFileName);

        log.info("文件上传完成, s3访问路径为:{}", awsS3FileUrl);
        return awsS3FileUrl.toString();
    }

    /**
     * 文件上传
     *
     * @param multipartFile 目标文件
     * @return 远程访问url, url不预签名
     */
    public String uploadToS3(MultipartFile multipartFile) {
        try {
            // MultipartFile转File
            File file = this.multipartFileToFile(multipartFile);

            // 上传到AWS S3
            String awsS3FileUrl = uploadToS3(file, IdUtil.simpleUUID());

            // 删除文件
            file.delete();
            return awsS3FileUrl;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(StateTypeSuper.FAIL_DEFAULT);
        }

    }

    /**
     * MultipartFile 转 File
     *
     * @param multipartFile
     * @return
     * @throws Exception
     */
    private File multipartFileToFile(MultipartFile multipartFile) throws Exception {
        File toFile = null;
        if (multipartFile.equals("") || multipartFile.getSize() <= 0) {
            multipartFile = null;
        } else {
            InputStream ins = null;
            try {
                ins = multipartFile.getInputStream();
                toFile = new File(multipartFile.getOriginalFilename());
                inputStreamToFile(ins, toFile);
            } finally {
                if (ins != null) {
                    ins.close();
                }
            }
        }
        return toFile;
    }

    private void inputStreamToFile(InputStream ins, File file) throws Exception {
        OutputStream os = new FileOutputStream(file);
        try {
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
            }
            if (ins != null) {
                ins.close();
            }
        }
    }


}
