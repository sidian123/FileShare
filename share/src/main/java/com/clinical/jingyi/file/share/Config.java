package com.clinical.jingyi.file.share;

import com.clinical.jingyi.file.share.utils.FileUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;

/**
 * @author sidian
 * @date 2020/7/3 13:42
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix="share")
public class Config {
    String location;

    @PostConstruct
    void init() throws IOException {
        // 保证分享的根目录存在
        FileUtils.keepDirExist(Path.of(location));
    }
}
