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
 * @since 2023-12-26
 */
@Getter
@Setter
@TableName("spu_image")
public class SpuImage extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long spuId;

    private String imgName;

    private String imgUrl;
}
