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
import java.util.Date;

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
@TableName("t_merchant")
public class Merchant extends Model<Merchant> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.UUID)
    private String id;

        /**
     * 商户名
     */
        @TableField("merchantName")
        private String merchantName;

    @TableField("merchantName")
    private String shortName;

        /**
     * 商户类型
     */
        @TableField("type")
        private Integer type;

        /**
     * 商户风格
     */
        @TableField("style")
        private Integer style;

        /**
     * 商户介绍
     */
        @TableField("notes")
        private String notes;

    @TableField("brandId")
    private String brandId;

    @TableField("createDate")
    private LocalDateTime createDate;
    @TableField("updateDate")
    private LocalDateTime updateDate;
    @TableField("status")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
