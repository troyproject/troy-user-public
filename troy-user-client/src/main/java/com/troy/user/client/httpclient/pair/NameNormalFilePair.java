package com.troy.user.client.httpclient.pair;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.Serializable;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName NameBufferedFilePair
 * @Description 参数名与标准文件的值组合
 * @date 2017年8月1日 下午2:02:25
 * @history 版本 作者 时间 注释
 */
public class NameNormalFilePair implements Serializable {

    private static final long serialVersionUID = -1684369576865247920L;

    private String name;
    private File file;

    public NameNormalFilePair(String name, File file) {
        if (StringUtils.isEmpty(name))
            throw new IllegalArgumentException("Name may not be empty");
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
