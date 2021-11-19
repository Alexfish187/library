package com.alex.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.alex.dao.AdminMapper;
import com.alex.po.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements  AdminService
{
    @Autowired
    private AdminMapper adminDao;

    /**
     * 分页查询
     */
    @Override
    public PageInfo<Admin> queryAdminInfoAll(Admin admin, int page, int limit)
    {
        PageHelper.startPage(page,limit);
        List<Admin> list = adminDao.queryAdminInfoAll(admin);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 添加
     */
    @Override
    public int addAdminSubmit(Admin admin)
    {
        return adminDao.insert(admin);
    }

    /**
     * 修改
     */
    @Override
    public int updateAdminSubmit(Admin admin)
    {
        return adminDao.updateByPrimaryKey(admin);
    }

    /**
     * 删除
     */
    @Override
    public int deleteAdminByIds(List<String> ids)
    {
        int num=0;
        for(String id:ids)
        {
           num += adminDao.deleteByPrimaryKey(Integer.valueOf(id));
        }
        return num;
    }

    /**
     * 根据id查询详细信息
     */
    @Override
    public Admin queryAdminById(Integer id)
    {
        return adminDao.selectByPrimaryKey(id);
    }

    /**
     * 根据用户名和密码查询用户信息
     */
    @Override
    public Admin queryUserByNameAndPassword(String username, String password)
    {
        return adminDao.queryUserByNameAndPassword(username,password);
    }
}
