package com.xht.generator.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xht.model.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author xht
 * @since 2023-12-27
 */
@Getter
@Setter
@TableName("sku_img")
public class SkuImg extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long skuId;

    private String imgName;

    private String imgUrl;

    private Long spuImgId;

    private Byte isDefault;
}
