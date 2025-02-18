package com.maidao.edu.news.api.banner.service;

import com.maidao.edu.news.api.banner.model.Banner;
import com.maidao.edu.news.api.banner.qo.BannerQo;
import com.maidao.edu.news.api.banner.repository.IBannerRepository;
import com.maidao.edu.news.common.exception.ErrorCode;
import com.maidao.edu.news.common.exception.ServiceException;
import com.maidao.edu.news.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:BannerService
 * 类描述:banner服务层接口的一个具体实现类
 **/

@Service
public class BannerService implements IBannerService {

    @Autowired
    private IBannerRepository bannerRepository;

    @Override
    public List<Banner> banners(BannerQo qo, boolean admin) {
        if (admin) {
            qo.setStatus(0);
        }
        return bannerRepository.findAll(qo);
    }

    @Override
    public Banner banner(int id) {
        return bannerRepository.getOne(id);
    }

    @Override
    public void save(Banner banner) throws ServiceException {
        if (StringUtils.isEmpty(banner.getImg())) {
            throw new ServiceException(ErrorCode.ERR_CONTENT_EMPTY);
        }
        bannerRepository.save(banner);
    }

    @Override
    public void remove(int id) {
        bannerRepository.deleteById(id);
    }

}