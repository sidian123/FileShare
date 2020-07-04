package live.sidian.file.share.controller;

import live.sidian.file.share.entity.vo.ApiResponse;
import live.sidian.file.share.entity.vo.File;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sidian
 * @date 2020/7/3 13:26
 */
@ResponseBody
@RequestMapping("/file")
public interface FileApi {

    /**
     * 列出Path下的所有文件或目录
     * @param path 要查看的路径
     * @return 所有文件或目录
     */
    @GetMapping("/list")
    ApiResponse<List<File>> list(String path);

    /**
     * 新增目录
     * @param path 目录名
     * @return 结果
     */
    @GetMapping("/make/dir")
    ApiResponse<Void> mkdir(String path);

    /**
     * 新增问价
     * @param path 文件名
     * @return 结果
     */
    @GetMapping("/touch/file")
    ApiResponse<Void> touch(String path);

    /**
     * 删除文件或目录(递归)
     * @param path 文件或目录的全路径名
     * @return 结果
     */
    @GetMapping("/rm")
    ApiResponse<Void> rm(String path);
}
