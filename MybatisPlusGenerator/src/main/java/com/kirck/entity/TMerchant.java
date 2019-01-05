package com.kirck.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商户表
 * </p>
 *
 * @author kirck007
 * @since 2019-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TMerchant extends Model<TMerchant> {

    private static final long serialVersionUID = 1L;

    private String id;

        /**
     * 商户名
     */
         private String merchantName;

    private String shortName;

        /**
     * 商户类型
     */
         private Integer type;

        /**
     * 商户风格
     */
         private Integer style;

        /**
     * 商户介绍
     */
         private String notes;

    private String brandId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
