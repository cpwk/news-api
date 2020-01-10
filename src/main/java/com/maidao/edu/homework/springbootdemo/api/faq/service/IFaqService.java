package com.maidao.edu.homework.springbootdemo.api.faq.service;

import com.maidao.edu.homework.springbootdemo.api.faq.entity.SimpleFaq;
import com.maidao.edu.homework.springbootdemo.api.faq.model.Faq;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:IFaqService
 * 类描述:Faq业务逻辑层接口
 **/

public interface IFaqService {

    List<Faq> faqs(boolean adm);

    SimpleFaq ask(String q);

    void save_faq(Faq faq);

    void remove_faq(int id);

    Faq faq(int id);
}
