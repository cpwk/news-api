package com.maidao.edu.homework.springbootdemo.api.custcase.service;

import com.maidao.edu.homework.springbootdemo.api.custcase.model.Custcase;
import com.maidao.edu.homework.springbootdemo.api.custcase.qo.CustcaseQo;
import com.maidao.edu.homework.springbootdemo.api.custcase.repository.ICustcaseRepository;
import com.maidao.edu.homework.springbootdemo.common.entity.Constants;
import com.maidao.edu.homework.springbootdemo.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:CustcaseService
 * 类描述:用户案例服务层接口其中一个具体实现类
 **/

@Service
public class CustcaseService implements ICustcaseService {

    @Autowired
    private ICustcaseRepository custcaseRepository;

    @Override
    public Custcase custcase(int id) {
        return custcaseRepository.getOne(id);
    }

    @Override
    public List<Custcase> getTopCases(int limit) {
        CustcaseQo qo = new CustcaseQo();
        qo.setPageSize(limit);
        qo.setOntop(1);
        return custcaseRepository.findAll(qo).getContent();
    }

    @Override
    public Page<Custcase> custcases(CustcaseQo qo, boolean adm) throws ServiceException {
        if (adm) {
            qo.setStatus(0);
        }
        return custcaseRepository.findAll(qo);

    }

    @Override
    public void save(Custcase custcase) throws ServiceException {

        if (custcase.getId() == null) {
            custcase.setCreatedAt(System.currentTimeMillis());
        }
        if (custcase.getStatus() == null) {
            custcase.setStatus(Constants.ISTATUS_OK);
        }
        custcaseRepository.save(custcase);
    }

    @Override
    public void remove(int id) {
        custcaseRepository.deleteById(id);
    }

}