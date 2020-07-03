package com.clinical.jingyi.file.share.controller.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.clinical.jingyi.file.share.controller.FileApi;
import com.clinical.jingyi.file.share.entity.vo.ApiResponse;
import com.clinical.jingyi.file.share.entity.vo.File;
import com.clinical.jingyi.file.share.service.FileService;
import com.clinical.jingyi.file.share.utils.FileUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sidian
 * @date 2020/7/3 13:38
 */
@RestController
public class FileApiImpl implements FileApi {
    @Resource
    FileService fileService;

    @Override
    public ApiResponse<List<File>> list(@RequestParam(required = false,defaultValue = "") String path) {
        try {
            List<File> fileList=fileService.list(path);
            return ApiResponse.success(fileList);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResponse.fail(e);
        }
    }

    @Override
    public ApiResponse<Void> mkdir(String path) {
        // 参数校验
        if(StrUtil.isBlank(path)){
            return ApiResponse.fail("参数不能为空");
        }
        // TODO 格式校验
        // 创建目录
        try {
            Files.createDirectory(FileUtils.getPath(path));
            return ApiResponse.success();
        }catch (FileAlreadyExistsException e){
            return ApiResponse.info("目录已存在");
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResponse.fail(e.getMessage());
        }
    }

    @Override
    public ApiResponse<Void> touch(String path) {
        // 参数校验
        if(StrUtil.isBlank(path)){
            return ApiResponse.fail("参数不能为空");
        }
        // TODO 格式校验
        // 创建文件
        try {
            Files.createFile(FileUtils.getPath(path));
            return ApiResponse.success();
        }catch(FileAlreadyExistsException e) {
            return ApiResponse.info("文件已存在");
        }catch(AccessDeniedException e){
            return ApiResponse.info("存在相同名字的其他文件, 如目录");
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResponse.fail(e);
        }
    }

    @Override
    public ApiResponse<Void> rm(String path) {
        // 参数校验
        if(StrUtil.isBlank(path)){
            return ApiResponse.fail("参数不能为空");
        }
        // TODO 格式校验
        try{
            FileUtil.del(FileUtils.getPath(path));
            return ApiResponse.success();
        }catch (Exception e){
            return ApiResponse.fail(e);
        }
    }

}
