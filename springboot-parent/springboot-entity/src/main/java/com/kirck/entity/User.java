package com.kirck.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

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
@TableName("t_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

        /**
     * id
     */
		@TableId(value="id",type= IdType.UUID)
		private String id;

        /**
     * 用户名
     */
		@TableField("user_name")
		private String username;

        /**
     * 密码
     */
		@TableField("password")
		private String password;

        /**
     * 昵称
     */
		@TableField("nick_name")
		private String nickname;

         
         
         
    public String getId() {
			return id;
		}




		public void setId(String id) {
			this.id = id;
		}




		public String getUsername() {
			return username;
		}




		public void setUsername(String username) {
			this.username = username;
		}




		public String getPassword() {
			return password;
		}




		public void setPassword(String password) {
			this.password = password;
		}




		public String getNickname() {
			return nickname;
		}




		public void setNickname(String nickname) {
			this.nickname = nickname;
		}


		

	@Override
		public String toString() {
			return "User [id=" + id + ", username=" + username + ", password=" + password + ", nickname=" + nickname
					+ "]";
		}




	@Override
    protected Serializable pkVal() {
        return this.id;
    }

}
