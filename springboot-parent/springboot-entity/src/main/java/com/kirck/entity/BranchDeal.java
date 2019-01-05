package com.kirck.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 分店团购关系表
 * </p>
 *
 * @author kirck007
 * @since 2019-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_branch_deal")
public class BranchDeal extends Model<BranchDeal> {

    private static final long serialVersionUID = 1L;

    @TableField("branchId")
    private String branchId;

    @TableField("dealId")
    private String dealId;

    @TableField("status")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
