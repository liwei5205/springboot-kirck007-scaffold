package com.kirck.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 国家省市区表
 * </p>
 *
 * @author kirck007
 * @since 2019-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TSysArea extends Model<TSysArea> {

    private static final long serialVersionUID = 1L;

        /**
     * 主键Id
     */
         @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        /**
     * 类型 0：国家  , 1：省 , 2：市 , 3: 区
     */
         private Integer type;

        /**
     * 标签
     */
         private String label;

        /**
     * 描述
     */
         private String description;

        /**
     * 排序begin 0
     */
         private Integer sort;

        /**
     * 行政区域Id
     */
         @TableField("areaID")
    private Integer areaID;

        /**
     * 上级Id
     */
         private Integer pid;

        /**
     * 邮编
     */
         private String zip;

    private String abbreviation;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
