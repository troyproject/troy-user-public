package com.troy.user.client.httpclient.pair;

import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.io.Serializable;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName NameBufferedFilePair
 * @Description 参数名与流式文件的值组合
 * @date 2017年8月1日 下午2:02:25
 * @history 版本 作者 时间 注释
 */
public class NameStreamedFilePair implements Serializable {

    private static final long serialVersionUID = -2961896601251039508L;

    private String name;
    private String filename;
    private InputStream inputStream;

    public NameStreamedFilePair(String name, String filename, InputStream inputStream) {
        if (StringUtils.isEmpty(name))
            throw new IllegalArgumentException("Name may not be empty");
        if (StringUtils.isEmpty(filename) || filename.indexOf(".") == -1)
            throw new IllegalArgumentException("Filename may not be empty and must contain file ext name(example: panda.jgp)");
        this.name = name;
        this.filename = filename;
        this.inputStream = inputStream;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
