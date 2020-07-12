package com.lksun.lkschool.service;

import com.lksun.lkschool.entity.Administrators;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LkSchool
 * @since 2020-07-05
 */
public interface AdministratorsService extends IService<Administrators> {
    public Administrators getByUsername(String username);
    public Administrators getById(Integer id);
}
