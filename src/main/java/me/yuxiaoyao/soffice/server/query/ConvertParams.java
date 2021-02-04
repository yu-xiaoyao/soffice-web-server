package me.yuxiaoyao.soffice.server.query;

import java.util.Map;

/**
 * @author kerryzhang on 2021/2/3
 * 这里的参数从这里传过来
 * @see org.jodconverter.remote.task.RemoteConversionTask
 */

public class ConvertParams {
    /**
     * s 开头的
     */
    private final Map<String, String> source;

    public ConvertParams(Map<String, String> source) {
        this.source = source;
    }

    public Map<String, String> getSource() {
        return source;
    }
}
