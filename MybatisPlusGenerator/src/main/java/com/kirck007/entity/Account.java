package com.kirck007.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账号表
 * </p>
 *
 * @author kirck007
 * @since 2018-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Account extends Model<Account> {

    private static final long serialVersionUID = 1L;

    private String id;

        /**
     * 公司id
     */
         private String companyId;

        /**
     * 人员id
     */
         private String userId;

        /**
     * 登录账号
     */
         private String loginId;

        /**
     * 密码
     */
         private String pwd;

        /**
     * 盐
     */
         private String salt;

        /**
     * 账号名称（展示用）
     */
         private String accountName;

        /**
     * 状态：0正常
     */
         private Integer status;

        /**
     * 微信的uid
     */
         private String wxUid;

        /**
     * 创建时间
     */
         private LocalDateTime createDate;

        /**
     * 创建人
     */
         private String createUid;

        /**
     * 修改时间
     */
         private LocalDateTime updateDate;

        /**
     * 修改人
     */
         private String updateUid;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
