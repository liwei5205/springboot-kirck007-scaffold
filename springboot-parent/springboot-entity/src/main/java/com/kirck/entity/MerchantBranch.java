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
import java.time.LocalDateTime;

/**
 * <p>
 * 分店表
 * </p>
 *
 * @author kirck007
 * @since 2019-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_merchant_branch")
public class MerchantBranch extends Model<MerchantBranch> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;

        /**
     * 商户id
     */
        @TableField("merchantId")
        private String merchantId;
    @TableField("address")
    private String address;
    @TableField("telephone")
    private String telephone;
    @TableField("merchantName")
    private String merchantName;
    @TableField("shopId")
    private String shopId;
    @TableField("createDate")
    private LocalDateTime createDate;
    @TableField("updateDate")
    private LocalDateTime updateDate;

        /**
     * 省
     */
        @TableField("provinceId")
        private Integer provinceId;
    @TableField("cityId")
    private Integer cityId;

        /**
     * 区
     */
        @TableField("districtId")
        private Integer districtId;
    @TableField("status")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
