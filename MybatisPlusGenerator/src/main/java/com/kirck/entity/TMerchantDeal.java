package com.kirck.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商户团购表
 * </p>
 *
 * @author kirck007
 * @since 2019-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TMerchantDeal extends Model<TMerchantDeal> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String merchantId;

    private String dealTitle;

    private BigDecimal price;

    private BigDecimal storePrice;

        /**
     * app地址
     */
         @TableField("appUrl")
    private String appUrl;

    private String notes;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
