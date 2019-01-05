package com.kirck.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class TMerchantBranch extends Model<TMerchantBranch> {

    private static final long serialVersionUID = 1L;

    private String id;

        /**
     * 商户id
     */
         private String merchantId;

    private String address;

    private String telephone;

    private String businessHours;

    private String shopId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

        /**
     * 省
     */
         private Integer provinceId;

    private Integer cityId;

        /**
     * 区
     */
         private Integer districtId;

    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
