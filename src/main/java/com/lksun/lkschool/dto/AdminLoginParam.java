package com.lksun.lkschool.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AdminLoginParam {
    @NotNull(message = "用户名不能为空")
    @Pattern(regexp = "/^[a-zA-Z0-9_-]{6,16}$/",message = "用户名为6~16位数字、字母、下划线")
    @Length(min = 6,max = 16,message = "用户名长度为6~16位")
    private String username;


    @NotNull(message = "密码不能为空")
    @Length(min = 6,max = 32,message = "密码长度为6~32位")
    private String password;

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
}
