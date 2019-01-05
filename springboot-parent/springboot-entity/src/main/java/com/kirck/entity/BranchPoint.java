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

/**
 * <p>
 * 分店得分
 * </p>
 *
 * @author kirck007
 * @since 2019-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_branch_point")
public class BranchPoint extends Model<BranchPoint> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.UUID)
    private String id;

        /**
     * 分店id
     */
        @TableField("branchId")
        private String branchId;

        /**
     * 人均
     */
        @TableField("avgPrice")
        private Integer avgPrice;

        /**
     * 口味
     */
        @TableField("taste")
        private Double taste;

        /**
     * 环境
     */
        @TableField("environment")
        private Double environment;

        /**
     * 服务
     */
        @TableField("service")
        private Double service;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
