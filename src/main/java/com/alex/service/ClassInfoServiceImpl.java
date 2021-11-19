package com.alex.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.alex.dao.ClassInfoDao;
import com.alex.po.ClassInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 图书类型 Service 实现类
 */
@Service("classService")
public class ClassInfoServiceImpl implements ClassInfoService
{
    @Autowired
    private ClassInfoDao classDao;

    @Override
    public PageInfo<ClassInfo> queryClassInfoAll(String name,int page,int limit)
    {
        // 给分页插件传入数据：page页数，limit条数
        PageHelper.startPage(page,limit);

        // 调用dao层方法获取信息
        List<ClassInfo> list = classDao.queryClassInfoAll(name);

        // 把查到的类目信息 塞到分 页的类pageInfo里
        PageInfo<ClassInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void addTypeSubmit(ClassInfo info)
    {
        classDao.addTypeSubmit(info);
    }

    @Override
    public ClassInfo queryClassInfoById(Integer id)
    {
        return classDao.queryClassInfoById(id);
    }

    @Override
    public void updateTypeSubmit(ClassInfo info)
    {
         classDao.updateTypeSubmit(info);
    }

    @Override
    public void deleteTypeByIds(List<String> id)
    {
        // 将String类型的id转化为Integer类型
        List<Integer> list = new ArrayList<>();
        for(String cid:id)
        {
           int id2 = Integer.valueOf(cid);
           list.add(id2);
        }
        classDao.deleteTypeByIds(list);
    }
}
