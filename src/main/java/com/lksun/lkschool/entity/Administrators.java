package com.lksun.lkschool.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author LkSchool
 * @since 2020-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("administrators")
public class Administrators implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    private String password;

    /**
     * 密码加盐
     */
    private String salt;

    /**
     * 昵称
     */
    private String nickname;

    private String email;

    private String profile;

    private String telephone;

    private LocalDateTime createDate;


}
