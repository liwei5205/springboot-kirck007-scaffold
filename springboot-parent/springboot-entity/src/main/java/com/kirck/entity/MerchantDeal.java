package com.kirck.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

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
@TableName("t_merchant_deal")
public class MerchantDeal extends Model<MerchantDeal> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField("merchantId")
    private String merchantId;
    @TableField("dealTitle")
    private String dealTitle;
    @TableField("price")
    private BigDecimal price;
    @TableField("storePrice")
    private BigDecimal storePrice;

        /**
     * app地址
     */
         @TableField("appUrl")
    private String appUrl;
    @TableField("notes")
    private String notes;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
