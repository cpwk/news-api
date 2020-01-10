package com.maidao.edu.homework.springbootdemo.api.qa.service;


import com.maidao.edu.homework.springbootdemo.api.heavywork.model.HeavyWork;
import com.maidao.edu.homework.springbootdemo.api.qa.model.QAPaper;
import com.maidao.edu.homework.springbootdemo.api.qa.model.QATemplate;
import com.maidao.edu.homework.springbootdemo.api.qa.qo.QAPaperQo;
import com.maidao.edu.homework.springbootdemo.common.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:TODO
 * 类描述:TODO
 **/
public interface IQAService {


    //tpl
    List<QATemplate> qaTemplates();

    QATemplate qaTemplate(int id);

    void saveQATemplate(QATemplate qaTemplate) throws ServiceException;

    void statusQATemplate(int id, byte status) throws ServiceException;

    //paper
    Page<QAPaper> qaPapers(QAPaperQo qo);

    HeavyWork exportQAPapers(QAPaperQo qo) throws Exception;

    void saveQAPaper(QAPaper qaPaper);

    void statusQAPaper(int id, byte status);

    void removeQAPaper(int id) throws ServiceException;

}
