package com.alex.controller;

import com.github.pagehelper.PageInfo;
import com.alex.po.Admin;
import com.alex.service.AdminService;
import com.alex.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class AdminController
{
    @Autowired
    private AdminService adminService;

    /**
     * 跳转管理员首页
     */
    @GetMapping("/adminIndex")
    public String adminIndex()
    {
        return "admin/adminIndex";
    }

    /**
     * 跳转添加管理员页面
     */
    @GetMapping("/addAdmin")
    public String addAdmin()
    {
        return "admin/addAdmin";
    }

    /**
     * 根据id查询并跳转修改页面 aaa
     */
    @GetMapping("/queryAdminById")
    public String queryAdminById(Integer id, Model model)
    {
        model.addAttribute("id", id);
        return "admin/updateAdmin";
    }

    /**
     * 查询所有的管理员信息
     */
    @ResponseBody
    @RequestMapping("/adminAll")
    public R adminAll(Admin admin,
                      @RequestParam(defaultValue = "1") int page,
                      @RequestParam(defaultValue = "15") int limit)
    {
        //查询所有的记录信息
        PageInfo<Admin> pageInfo = adminService.queryAdminInfoAll(admin,page,limit);
        return R.ok("成功",pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     *  提交管理添加功能实现
     */
    @ResponseBody
    @RequestMapping("/addAdminSubmit")
    public R addAdminSubmit(Admin admin)
    {
        int num = adminService.addAdminSubmit(admin);
        if(num>0)
        {
            return R.ok();
        } else {
            return  R.fail("添加失败");
        }
    }

    /**
     * 密码的修改
     */
    @ResponseBody
    @RequestMapping("/updatePwdSubmit")
    public R updatePwdSubmit(Integer id, String oldPwd, String newPwd)
    {
         //根据id查询对象
         Admin info = adminService.queryAdminById(id);
         if(!oldPwd.equals(info.getPassword()))
         {
             return R.fail("输入的旧密码和原来不一致");
         } else {
             info.setPassword(newPwd);
             return R.ok("修改密码成功");
         }
    }

    /***
     * 删除功能实现
     */
    @ResponseBody
    @RequestMapping("/deleteAdmin")
    public R deleteAdmin(String ids)
    {
        List<String> list = Arrays.asList(ids.split(","));
        int num = adminService.deleteAdminByIds(list);
        if(num > 0)
        {
            return R.ok();
        } else {
            return  R.fail("删除失败");
        }
    }
}
