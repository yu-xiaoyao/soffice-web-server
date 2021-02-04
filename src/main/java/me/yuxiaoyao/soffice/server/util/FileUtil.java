package me.yuxiaoyao.soffice.server.util;

import me.yuxiaoyao.soffice.server.exception.IOWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author kerryzhang on 2021/2/3
 */

public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * @param filename
     * @param prefix
     * @return
     */
    public static File createTmpFile(String filename, String prefix) {

        try {
            return File.createTempFile(filename, prefix);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new IOWrapperException(e.getMessage(), e);
        }
    }
}
