package com.clinical.jingyi.file.share.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sidian
 * @date 2020/7/3 13:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File {
    String name;
    String type;
}

