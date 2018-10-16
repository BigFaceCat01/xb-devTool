package xb.dev.tools.tool.mongo.service;

import xb.dev.tools.base.BaseService;
import xb.dev.tools.common.PageModule;
import xb.dev.tools.model.MongoNewsBasicInfo;
import xb.dev.tools.model.MongoNewsListModel;
import xb.dev.tools.model.mongo.MongoNewsModel;

/**
 * @author: Created by huangxb on 2018-08-01 18:11:33
 *
 */
public interface MongoNewsService extends BaseService<MongoNewsModel,String> {
    /**
     * 查询用户的新闻列表
     * @param pageNum 页数
     * @param pageSize 每页数量
     * @param userId 用户id
     * @return 新闻分页列表
     */
    PageModule<MongoNewsListModel> listUserNewsForPage(Integer pageNum,Integer pageSize,Long userId);

    /**
     * 提交新闻
     * @param newsModel 新闻信息
     */
    void confirm(MongoNewsModel newsModel);

    /**
     * 根据id获取新闻详情,包含是个评论
     * @param id 新闻id
     * @return 新闻详情
     */
    MongoNewsBasicInfo getById(String id);

    /**
     * 同步网易新闻到mongodb
     * @param type 类型
     */
    void syncNews163com(Byte type);
}
