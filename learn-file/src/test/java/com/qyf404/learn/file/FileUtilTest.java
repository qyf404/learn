package com.qyf404.learn.file;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FileUtilTest {
    private String rarFilePath = "./src/test/resources/msg.rar";
    private String zipFilePath = "./src/test/resources/msg.zip";
    private String filePath = "./src/test/resources/msg.txt";

    @Test
    public void should_unzip() {
        String destinationPath = "/tmp/" + System.currentTimeMillis();
        assertThat(new File(destinationPath).exists(), is(false));
        boolean isSuccess = FileUtil.unzip(zipFilePath, destinationPath);
        assertThat(isSuccess, is(true));
        assertThat(new File(destinationPath).exists(), is(true));
        assertThat(new File(destinationPath).listFiles().length, greaterThan(0));
        assertThat(new File(destinationPath + "/msg.txt").exists(), is(true));
    }

    @Test
    public void should_unzip_fail_when_file_is_not_exists() {
        String destinationPath = "/tmp/" + System.currentTimeMillis();
        assertThat(new File(destinationPath).exists(), is(false));
        boolean isSuccess = FileUtil.unzip("./src/test/resources/xxx.zip", destinationPath);
        assertThat(isSuccess, is(false));
        assertThat(new File(destinationPath).exists(), is(true));
        assertThat(new File(destinationPath).listFiles().length, is(0));
    }

    @Test
    public void should_unzip_fail_when_file_is_not_zip() {
        String destinationPath = "/tmp/" + System.currentTimeMillis();
        assertThat(new File(destinationPath).exists(), is(false));
        boolean isSuccess = FileUtil.unzip(filePath, destinationPath);
        assertThat(isSuccess, is(false));
        assertThat(new File(destinationPath).exists(), is(true));
        assertThat(new File(destinationPath).listFiles().length, is(0));
    }

    @Test
    @Ignore
    public void should_unrar() {
        String destinationPath = "/tmp/" + System.currentTimeMillis();
        assertThat(new File(destinationPath).exists(), is(false));
        boolean isSuccess = FileUtil.unrar(rarFilePath, destinationPath);
        assertThat(isSuccess, is(true));
        assertThat(new File(destinationPath).exists(), is(true));
        assertThat(new File(destinationPath).listFiles().length, greaterThan(0));
        assertThat(new File(destinationPath + "/msg.txt").exists(), is(true));
    }
}
