package com.alex.service;

import com.alex.po.ReaderCard;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ReaderService
{

    /**
     * 查询读者信息 分页
     */
    PageInfo<ReaderCard> queryReaderAll(ReaderCard readerCard, int page, int limit);

    /**
     * 添加读者信息
     */
    void addReaderInfoSubmit(ReaderCard readerCard);

    /**
     * 根据id查询读者信息
     */
    ReaderCard queryReaderById(Integer id);

    /**
     * 修改读者信息
     */
    void updateReaderInfoSubmit(ReaderCard readerCard);

    /**
     * 删除读者信息
     */
    void deleteReaderByIds(List<String> ids);

    ReaderCard queryUserInfoByNameAndPassword(String username, String password);
}
