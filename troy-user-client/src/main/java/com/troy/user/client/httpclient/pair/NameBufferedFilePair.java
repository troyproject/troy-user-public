package com.troy.user.client.httpclient.pair;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName NameBufferedFilePair
 * @Description 参数名与字节形态文件的值组合
 * @date 2017年8月1日 下午2:02:25
 * @history 版本 作者 时间 注释
 */
public class NameBufferedFilePair implements Serializable {

    private static final long serialVersionUID = -7772471044069358750L;

    private String name;
    private String filename;
    private byte[] fileBuff;

    public NameBufferedFilePair(String name, String filename, byte[] fileBuff) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Name may not be empty");
        }
        if (StringUtils.isEmpty(filename) || filename.indexOf(".") == -1) {
            throw new IllegalArgumentException("Filename may not be empty and must contain file ext name(example: panda.jgp)");
        }
        this.name = name;
        this.filename = filename;
        this.fileBuff = fileBuff;
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

    public byte[] getFileBuff() {
        return fileBuff;
    }

    public void setFileBuff(byte[] fileBuff) {
        this.fileBuff = fileBuff;
    }
}
