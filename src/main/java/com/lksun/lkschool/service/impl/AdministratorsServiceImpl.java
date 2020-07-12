package com.lksun.lkschool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lksun.lkschool.entity.Administrators;
import com.lksun.lkschool.mapper.AdministratorsMapper;
import com.lksun.lkschool.service.AdministratorsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LkSchool
 * @since 2020-07-05
 */
@Service
public class AdministratorsServiceImpl extends ServiceImpl<AdministratorsMapper, Administrators> implements AdministratorsService {

    public Administrators getByUsername(String username){
        QueryWrapper<Administrators> ew = new QueryWrapper<Administrators>();
        ew.eq("username",username);
        return this.getOne(ew);
    }

    public Administrators getById(Integer id){
        QueryWrapper<Administrators> ew = new QueryWrapper<Administrators>();
        ew.eq("id",id);
        return this.getOne(ew);
    }

}
