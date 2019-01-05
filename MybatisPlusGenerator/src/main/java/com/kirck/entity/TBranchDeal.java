package com.kirck.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class TBranchDeal extends Model<TBranchDeal> {

    private static final long serialVersionUID = 1L;

    private String branchId;

    private String dealId;

    private Integer status;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
