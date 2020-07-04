package live.sidian.file.share.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author sidian
 * @date 2020/7/3 13:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File {
    /**
     * 文件/目录名
     */
    String name;
    /**
     * 类型, 文件或目录等
     */
    String type;
    /**
     * 相对于的根目录的路径
     */
    String path;

    /**
     * 文件修改日期
     */
    Date modifiedTime;

    /**
     * 文件创建日期
     */
    Date createdTime;

    /**
     * 大小, 单位子节
     */
    long size;
}

