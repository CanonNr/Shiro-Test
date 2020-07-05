package com.lksun.lkschool.controller;


import com.lksun.lkschool.entity.Administrators;
import com.lksun.lkschool.service.IAdministratorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LkSchool
 * @since 2020-07-05
 */
@RestController
@RequestMapping("/")
public class AdministratorsController {
    @Autowired
    IAdministratorsService iAdministratorsService;

    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public Administrators test(){
        Administrators user = iAdministratorsService.getOne(null);
        return user;
    }

    @RequestMapping(value = "/baba",method = RequestMethod.GET)
    public String baba(){
        return "baba";
    }

}
