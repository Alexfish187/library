package com.alex.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.alex.dao.NoticeMapper;
import com.alex.po.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("noticeService")
public class NoticeServiceImpl  implements  NoticeService
{
    @Autowired
    private NoticeMapper noticeDao;

    /**
     * 分页查询公告信息
     */
    @Override
    public PageInfo<Notice> queryNoticeAll(String content, int page, int limit)
    {
        PageHelper.startPage(page, limit);
        List<Notice> list = noticeDao.queryNoticeAll(content);
        PageInfo<Notice> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 添加公告
     */
    @Override
    public void insertNoticeInfo(Notice notice)
    {
        noticeDao.insert(notice);
    }

    /**
     * 根据id查询
     */
    @Override
    public Notice queryNoticeById(Integer id)
    {
        return noticeDao.queryNoticeById(id);
    }

    /**
     * 删除
     */
    @Override
    public void deleteNoticeByIds(List<String> ids)
    {
         List<Integer> list = new ArrayList<>();
         for(String id:ids)
         {
             list.add(Integer.valueOf(id));
         }
         noticeDao.deleteNoticeByIds(list);
    }
}
