package com.maidao.edu.news.api.custeval.service;

import com.maidao.edu.news.api.custeval.model.Custeval;
import com.maidao.edu.news.api.custeval.repository.ICustevalRepository;
import com.maidao.edu.news.common.entity.Constants;
import com.maidao.edu.news.common.util.StringUtils;
import com.maidao.edu.news.api.custeval.qo.CustevalQo;
import com.maidao.edu.news.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.maidao.edu.news.common.exception.ErrorCode.ERROR_COVER_EMPTY;
import static com.maidao.edu.news.common.exception.ErrorCode.ERR_CONTENT_EMPTY;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:CustevalService
 * 类描述:用户评价服务类接口其中一个具体实现类
 **/

@Service
public class CustevalService implements ICustevalService {

    @Autowired
    private ICustevalRepository custevalRepository;

    @Override
    public List<Custeval> getTopCustevals(int limit) {
        CustevalQo qo = new CustevalQo();
        qo.setPageSize(limit);
        return custevalRepository.findAll(qo).getContent();
    }

    @Override
    public Custeval custeval(int id) {
        return custevalRepository.getOne(id);
    }

    @Override
    public Page<Custeval> custevals(CustevalQo qo, boolean adm) {
        if (adm) {
            qo.setStatus(0);
        }
        return custevalRepository.findAll(qo);
    }

    @Override
    public void save(Custeval custeval) throws ServiceException {

        if (StringUtils.isEmpty(custeval.getTitle())) {
            throw new ServiceException(ERR_CONTENT_EMPTY);
        }
        if (StringUtils.isEmpty(custeval.getImg())) {
            throw new ServiceException(ERROR_COVER_EMPTY);
        }
        if (custeval.getId() == null) {
            custeval.setCreatedAt(System.currentTimeMillis());
        }
        if (custeval.getStatus() == null) {
            custeval.setStatus(Constants.ISTATUS_OK);
        }
        custevalRepository.save(custeval);
    }

    @Override
    public void remove(int id) {
        custevalRepository.deleteById(id);
    }
}
