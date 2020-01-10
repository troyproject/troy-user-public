package com.troy.user.client.httpclient.converter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class NameValuePairConverter {

    /**
     * Map to List
     *
     * @param map
     * @return
     */
    public static List<NameValuePair> convert(Map<String, String> map) {
        if (map == null)
            return null;
        List<NameValuePair> nameValuePairList = new ArrayList<>(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry == null)
                continue;
            nameValuePairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return nameValuePairList;
    }
}
