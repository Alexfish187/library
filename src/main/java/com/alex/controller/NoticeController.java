package com.alex.controller;

import com.github.pagehelper.PageInfo;
import com.alex.po.Notice;
import com.alex.service.NoticeService;
import com.alex.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class NoticeController
{
    @Autowired
    private NoticeService noticeService;

    /**
     * 管理员跳转公告主页
     */
    @GetMapping("/noticeIndex")
    public String  noticeIndex()
    {
        return "notice/noticeIndex";
    }

    /**
     * 跳转公告主页2
     */
    @GetMapping("/noticeIndex2")
    public String  noticeIndex2()
    {
        return "notice/noticeIndex2";
    }

    /**
     * 跳转发布公告页面
     */
    @GetMapping("/addNotice")
    public String addNotice()
    {
        return "notice/addNotice";
    }

    /**
     * 根据id查询并跳转公告详情页面
     */
    @GetMapping("/queryNotice")
    public String queryNoticeById(Integer id, Model model)
    {
        // 根据id查询公告详细信息
        Notice notice = noticeService.queryNoticeById(id);
        // 将查询到的notice信息作为info字段传入前端jsp页面
        model.addAttribute("info", notice);
        return "notice/queryNotice";
    }

    /**
     * 查询所有的公告信息
     */
    @ResponseBody
    @RequestMapping("/noticeAll")
    public R noticeAll(String content, @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "15")Integer limit)
    {
        PageInfo<Notice> pageInfo = noticeService.queryNoticeAll(content, page, limit);
        return R.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 公告发布提交方法
     */
    @ResponseBody
    @RequestMapping("/addNoticeSubmit")
    public R addNoticeSubmit(Notice notice)
    {
        //设置进入
        notice.setAuthor(1);
        notice.setCreateDate(new Date());
        noticeService.insertNoticeInfo(notice);
        return R.ok();
    }

    /**
     * 根据ids删除公告信息
     */
    @ResponseBody
    @RequestMapping("/deleteNoticeByIds")
    public R deleteNoticeByIds(String ids)
    {
         List list= Arrays.asList(ids.split(","));
         noticeService.deleteNoticeByIds(list);
         return R.ok();
    }
}
