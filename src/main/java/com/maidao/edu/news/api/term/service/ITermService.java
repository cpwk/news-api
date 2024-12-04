package com.maidao.edu.news.api.term.service;


import com.maidao.edu.news.api.term.model.Term;
import com.maidao.edu.news.common.exception.ServiceException;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
public interface ITermService {

    void save_term(Term term) throws ServiceException;

    Term term(int id);

    List<Term> terms(boolean admin);

    void set_term_default(int id);

    void update_status(int id, byte status) throws ServiceException;

}
