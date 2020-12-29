package com.maidao.edu.news.springbootdemo.api.partner.service;

import com.maidao.edu.news.springbootdemo.api.partner.model.Partner;
import com.maidao.edu.news.springbootdemo.api.partner.qo.PartnerQo;
import com.maidao.edu.news.springbootdemo.api.partner.repository.IPartnerRepository;
import com.maidao.edu.news.springbootdemo.common.exception.ServiceException;
import com.maidao.edu.news.springbootdemo.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:PartnerService
 * 类描述:业务逻辑实现类
 **/
@Service
public class PartnerService implements IPartnerService {

    @Autowired
    private IPartnerRepository partnerRepository;

    @Override
    public List<Partner> partners(PartnerQo qo, boolean admin) {
        if (admin) {
            qo.setStatus(0);
        }
        return partnerRepository.findAll(qo);
    }

    @Override
    public Partner partner(int id) {
        return partnerRepository.getOne(id);
    }

    @Override
    public void save(Partner partner) throws ServiceException {
        if (StringUtils.isEmpty(partner.getImg())) {
            throw new ServiceException(0);//"请上传图片"
        }
        partnerRepository.save(partner);
    }

    @Override
    public void remove(int id) {
        partnerRepository.deleteById(id);
    }

}
