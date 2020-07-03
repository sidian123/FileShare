package com.clinical.jingyi.file.share.utils;

import com.clinical.jingyi.file.share.Config;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author sidian
 * @date 2020/7/3 13:45
 */
@Component
public class FileUtils implements ApplicationContextAware {
    static Config config;

    public static Path getPath(String path){
        assert path!=null;
        return Path.of(config.getLocation(),path);
    }

    public static Path getRoot(){
        return Path.of(config.getLocation());
    }

    /**
     * 保证目录存在
     * @param path 指向目录的地址
     */
    public static void keepDirExist(Path path) throws IOException {
        //如果不存在, 则创建该目录
        if(!Files.exists(path)){
            //创建目录
            Files.createDirectories(path);
        }
        //检查参数, 必须是目录
        if(!Files.isDirectory(path)){//不是目录
            throw new NotDirectoryException("必须是目录");
        }
    }

    public static BasicFileAttributes getAttrs(Path path) throws IOException {
        return Files.readAttributes(path,BasicFileAttributes.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        config=applicationContext.getBean(Config.class);
    }
}
