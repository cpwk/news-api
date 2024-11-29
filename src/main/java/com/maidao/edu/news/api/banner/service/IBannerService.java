package com.maidao.edu.news.api.banner.service;

import com.maidao.edu.news.common.exception.ServiceException;
import com.maidao.edu.news.api.banner.model.Banner;
import com.maidao.edu.news.api.banner.qo.BannerQo;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:IBannerService
 * 类描述:banner服务层接口
 **/

public interface IBannerService {

    List<Banner> banners(BannerQo qo, boolean admin);

    Banner banner(int id);

    void save(Banner banner) throws ServiceException;

    void remove(int id);
}
