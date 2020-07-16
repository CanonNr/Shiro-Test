package com.lksun.lkschool.service.impl;

import com.lksun.lkschool.entity.Users;
import com.lksun.lkschool.mapper.UsersMapper;
import com.lksun.lkschool.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LkSchool
 * @since 2020-07-16
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
