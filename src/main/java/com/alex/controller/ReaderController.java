package com.alex.controller;

import com.alex.po.ReaderCard;
import com.alex.service.ReaderService;
import com.alex.util.R;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class ReaderController
{
    @Autowired
    private ReaderService readerService;

    /**
     * 读者页面
     */
    @GetMapping("/readerIndex")
    public String readerIndex()
    {
        return "reader/readerIndex";
    }

    /**
     *  添加读者页面
     */
    @GetMapping("/addReader")
    public String addReader()
    {
        return "reader/addReader";
    }

    /**
     * 查询所有的读者信息功能
     */
    @ResponseBody
    @RequestMapping("/queryReaderAll")
    public R queryReaderAll(ReaderCard info, @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "15") Integer limit)
    {

       PageInfo<ReaderCard>  pageInfo = readerService.queryReaderAll(info,page,limit);
       return R.ok("成功",pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 提交添加功能
     */
    @ResponseBody
    @RequestMapping("/addReaderSubmit")
    public R addReaderSubmit(@RequestBody ReaderCard info)
    {
        //设置默认密码
          info.setPassword("123456");
          readerService.addReaderInfoSubmit(info);
          return R.ok();
    }

    /**
     * 删除读者功能
     */
    @ResponseBody
    @RequestMapping("/deleteReader")
    public R deleteReaderByIds(String ids)
    {
        List list = Arrays.asList(ids.split(","));
        readerService.deleteReaderByIds(list);
        return R.ok();
    }

    /**
     * 根据id查询详细的读者信息，展示修改页面
     */
    @GetMapping("/queryReaderInfoById")
    public String queryReaderInfoById(Integer id, Model model){
         //根据id查询具体的读者信息
         ReaderCard readerCard=readerService.queryReaderById(id);
         model.addAttribute("info",readerCard);
         return "reader/updateReader";
    }

    /**
     * 修改读者功能
     */
    @ResponseBody
    @RequestMapping("/updateReaderSubmit")
    public R updateReaderSubmit(@RequestBody ReaderCard readerCard){
        readerService.updateReaderInfoSubmit(readerCard);
        return R.ok();
    }

}
