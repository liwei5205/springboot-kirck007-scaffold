package com.kirck007.entity;

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
 * @since 2018-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

        /**
     * id
     */
         private String id;

        /**
     * 用户名
     */
         private String username;

        /**
     * 密码
     */
         private String password;

        /**
     * 昵称
     */
         private String nickname;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
