package com.lksun.lkschool.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginParam {
    @NotNull(message = "用户名不能为空")
    @Pattern(regexp = "/^[a-zA-Z0-9_-]{6,16}$/")
    @Length(min = 6,max = 16,message = "用户名长度为6~16位")
    private String username;


    @NotNull(message = "密码不能为空")
    @Length(min = 6,max = 32,message = "密码长度为6~32位")
    private String password;
}
