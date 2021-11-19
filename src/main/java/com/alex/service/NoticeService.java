package com.alex.service;

import com.github.pagehelper.PageInfo;
import com.alex.po.Notice;

import java.util.List;

public interface NoticeService
{
    /**
     * 分页查询公告信息
     */
    PageInfo<Notice> queryNoticeAll(String content, int page, int limit);

    /**
     * 添加公告
     */
    void insertNoticeInfo(Notice notice);

    /**
     * 根据id查询
     */
    Notice queryNoticeById(Integer id);

    /**
     * 删除
     */
    void deleteNoticeByIds(List<String> ids);

}
