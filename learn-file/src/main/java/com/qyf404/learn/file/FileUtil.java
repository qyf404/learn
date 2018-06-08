package com.qyf404.learn.file;
import com.github.junrar.extract.ExtractArchive;
import net.lingala.zip4j.core.ZipFile;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FileUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    public static boolean unzip(String sourceZip, String destinationPath) {
        try {
            ZipFile zipFile = new ZipFile(sourceZip);

            zipFile.extractAll(destinationPath);
            return true;
        } catch (Exception e) {
            LOGGER.error("unzip fail.", e);
            return false;
        }
    }

    public static boolean unrar(String sourceRar, String destinationPath) {
        return unrar(new File(sourceRar), new File(destinationPath));
    }

    private static boolean unrar(File sourceRar, File destinationPath) {
        ExtractArchive extractArchive = new ExtractArchive();
        try {
            FileUtils.forceMkdir(destinationPath);
            extractArchive.extractArchive(sourceRar, destinationPath);
            return true;
        } catch (Exception e) {
            LOGGER.error("unrar fail.", e);
            return false;
        }
    }

}
