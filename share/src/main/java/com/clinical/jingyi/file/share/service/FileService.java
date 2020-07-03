package com.clinical.jingyi.file.share.service;

import cn.hutool.core.io.FileUtil;
import com.clinical.jingyi.file.share.entity.vo.ApiResponse;
import com.clinical.jingyi.file.share.entity.vo.File;
import com.clinical.jingyi.file.share.utils.FileUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sidian
 * @date 2020/7/3 20:01
 */
@Service
public class FileService {

    public List<File> list(String path) throws IOException {
        List<File> res = new LinkedList<>();
        // 获取所有路径
        List<Path> paths = Files.list(FileUtils.getPath(path)).collect(Collectors.toList());
        // 数据处理, 转化
        for (Path path1 : paths) {
            var builder = File.builder()
                    .name(path1.getFileName().toString())
                    .type("directory")
                    .path(FileUtils.getRoot().relativize(path1).toString())
                    .modifiedTime(new Date(FileUtils.getAttrs(path1).lastModifiedTime().toMillis()))
                    .createdTime(new Date(FileUtils.getAttrs(path1).creationTime().toMillis()))
                    .size(FileUtils.getAttrs(path1).size());
            if(Files.isDirectory(path1)){
                builder.type("directory");
            }else{
                builder.type("file");
            }
            res.add(builder.build());
        }
        return res;
    }
}
