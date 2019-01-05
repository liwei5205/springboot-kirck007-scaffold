package com.kirck.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author kirck007
 * @since 2019-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TDealInfo extends Model<TDealInfo> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String dealId;

    private String dealInfo;

    private String userNotes;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
