package com.kirck.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class TBranchPoint extends Model<TBranchPoint> {

    private static final long serialVersionUID = 1L;

    private String id;

        /**
     * 分店id
     */
         private String branchId;

        /**
     * 人均
     */
         private Integer avgPrice;

        /**
     * 口味
     */
         private Double taste;

        /**
     * 环境
     */
         private Double environment;

        /**
     * 服务
     */
         private Double service;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
