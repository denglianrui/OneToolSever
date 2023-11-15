package com.ot.onetool.POJO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author dlr
 * @since 2023-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_user")
@AllArgsConstructor
@NoArgsConstructor

public class UserPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uid
     */
    @TableId(value = "uid", type = IdType.ASSIGN_ID)
    private Long uid;

    /**
     * 账号
     */

    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * qq号
     */
    private Long qq;

    /**
     * 电话
     */
    private Long tel;

    /**
     * 是否为管理员 0、不是  1、是
     */
    private Boolean isadmin;

    /**
     * 用户状态：0 注销、1 正常
     */
    private Boolean state;

    /**
     * 是否为vip用户  0 否  1 是
     */
    private Boolean isvip;

    /**
     * 创建时间
     */
    private LocalDateTime CreateTime;


}
