package me.yuxiaoyao.soffice.server.service.impl;

import me.yuxiaoyao.soffice.server.exception.ConvertFailedException;
import me.yuxiaoyao.soffice.server.exception.FormatNotSupportException;
import me.yuxiaoyao.soffice.server.query.ConvertParams;
import me.yuxiaoyao.soffice.server.service.ConvertService;
import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.document.DefaultDocumentFormatRegistry;
import org.jodconverter.core.document.DocumentFormat;
import org.jodconverter.core.job.ConversionJob;
import org.jodconverter.core.job.ConversionJobWithOptionalSourceFormatUnspecified;
import org.jodconverter.core.office.OfficeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author kerryzhang on 2021/2/1
 */

@Service
public class ConvertServiceImpl implements ConvertService {
    private static final Logger logger = LoggerFactory.getLogger(ConvertServiceImpl.class);

    /**
     * 定义
     *
     * @see org.jodconverter.boot.autoconfigure.JodConverterLocalAutoConfiguration
     */
    @Resource
    private DocumentConverter documentConverter;


    private DocumentFormat getOutputDocumentFormat(String outputFormatExt) {
        DocumentFormat outputFormat = DefaultDocumentFormatRegistry.getFormatByExtension(outputFormatExt);
        if (outputFormat == null) {
            throw new FormatNotSupportException(outputFormatExt);
        }
        return outputFormat;
    }

    private DocumentFormat getInputDocumentFormat(String inputFormatExt) {
        if (inputFormatExt != null) {
            DocumentFormat inputFormat = DefaultDocumentFormatRegistry.getFormatByExtension(inputFormatExt);
            if (inputFormat == null) {
                logger.warn("input format not found for file extend = {} auto match format", inputFormatExt);
            }
            return inputFormat;
        }
        return null;
    }


    @Override
    public void convert(File inputFile, String inputFormatExt, File outputFile, String outputFormatExt, ConvertParams convertParams) {
        DocumentFormat inputFormat = getInputDocumentFormat(inputFormatExt);
        DocumentFormat outputFormat = getOutputDocumentFormat(outputFormatExt);

        ConversionJobWithOptionalSourceFormatUnspecified formatUnspecified = documentConverter.convert(inputFile);
        if (inputFormat != null) {
            formatUnspecified.as(inputFormat);
        }
        ConversionJob job = formatUnspecified.to(outputFile).as(outputFormat);
        execute(job);
    }

    @Override
    public void convert(File inputFile, String inputFormatExt, OutputStream outputStream, String outputFormatExt, ConvertParams convertParams) {
        DocumentFormat inputFormat = getInputDocumentFormat(inputFormatExt);
        DocumentFormat outputFormat = getOutputDocumentFormat(outputFormatExt);

        ConversionJobWithOptionalSourceFormatUnspecified formatUnspecified = documentConverter.convert(inputFile);
        if (inputFormat != null) {
            formatUnspecified.as(inputFormat);
        }
        ConversionJob job = formatUnspecified.to(outputStream).as(outputFormat);
        execute(job);
    }

    @Override
    public void convert(InputStream is, String inputFormatExt, File outputFile, String outputFormatExt, ConvertParams convertParams) {
        DocumentFormat inputFormat = getInputDocumentFormat(inputFormatExt);
        DocumentFormat outputFormat = getOutputDocumentFormat(outputFormatExt);

        ConversionJobWithOptionalSourceFormatUnspecified formatUnspecified = documentConverter.convert(is);
        if (inputFormat != null) {
            formatUnspecified.as(inputFormat);
        }
        ConversionJob job = formatUnspecified.to(outputFile).as(outputFormat);
        execute(job);

    }


    @Override
    public void convert(InputStream is, String inputFormatExt, OutputStream outputStream, String outputFormatExt, ConvertParams convertParams) {
        DocumentFormat inputFormat = getInputDocumentFormat(inputFormatExt);
        DocumentFormat outputFormat = getOutputDocumentFormat(outputFormatExt);

        ConversionJobWithOptionalSourceFormatUnspecified formatUnspecified = documentConverter.convert(is);
        if (inputFormat != null) {
            formatUnspecified.as(inputFormat);
        }
        ConversionJob job = formatUnspecified.to(outputStream).as(outputFormat);

        execute(job);
    }

    private void execute(ConversionJob job) {
        try {
            job.execute();
        } catch (OfficeException e) {
            logger.error(e.getMessage(), e);
            throw new ConvertFailedException(e.getMessage(), e);
        }
    }
}
