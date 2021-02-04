package me.yuxiaoyao.soffice.server.service;

import me.yuxiaoyao.soffice.server.query.ConvertParams;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author kerryzhang on 2021/2/1
 */

public interface ConvertService {

    void convert(File inputFile, String inputFormatExt, File outputFile, String outputFormatExt, ConvertParams convertParams);

    void convert(File inputFile, String inputFormatExt, OutputStream outputStream, String outputFormatExt, ConvertParams convertParams);


    /**
     * convert
     *
     * @param is
     * @param inputFormatExt
     * @param outputFile
     * @param outputFormatExt
     * @param convertParams
     */
    void convert(InputStream is, String inputFormatExt, File outputFile, String outputFormatExt, ConvertParams convertParams);

    /**
     * convert
     *
     * @param is
     * @param inputFormatExt
     * @param outputStream
     * @param outputFormatExt
     * @param convertParams
     */
    void convert(InputStream is, String inputFormatExt, OutputStream outputStream, String outputFormatExt, ConvertParams convertParams);
}
