package com.xht.model.dto.product;

import lombok.Data;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/27  20:54
 */

@Data
public class AttrAndValueDto {
    private Long attrId;

    private Long attrValueId;

    private String valueName;
}
