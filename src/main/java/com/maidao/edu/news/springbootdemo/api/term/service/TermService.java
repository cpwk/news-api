package com.maidao.edu.news.springbootdemo.api.term.service;

import com.maidao.edu.news.springbootdemo.api.term.model.Term;
import com.maidao.edu.news.springbootdemo.api.term.qo.TermQo;
import com.maidao.edu.news.springbootdemo.api.term.repository.ITermRepository;
import com.maidao.edu.news.springbootdemo.common.cache.CacheOptions;
import com.maidao.edu.news.springbootdemo.common.cache.KvCacheFactory;
import com.maidao.edu.news.springbootdemo.common.cache.KvCacheWrap;
import com.maidao.edu.news.springbootdemo.common.entity.Constants;
import com.maidao.edu.news.springbootdemo.common.exception.RepositoryException;
import com.maidao.edu.news.springbootdemo.common.exception.ServiceException;
import com.maidao.edu.news.springbootdemo.common.util.StringUtils;
import com.sunnysuperman.kvcache.RepositoryProvider;
import com.sunnysuperman.kvcache.converter.ListModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
@Service
public class TermService implements ITermService {

    @Autowired
    private ITermRepository termRepository;

    @Autowired
    private KvCacheFactory kvCacheFactory;

    private KvCacheWrap<Integer, List<Term>> termsCache;

    @PostConstruct
    public void init() {
        termsCache = kvCacheFactory.create(new CacheOptions("terms", 1, 1200),
                new RepositoryProvider<Integer, List<Term>>() {

                    @Override
                    public List<Term> findByKey(Integer key) throws RepositoryException {
                        TermQo qo = new TermQo();
                        if (key == Constants.STATUS_OK) {
                            return termRepository.findAll(qo);
                        } else {
                            qo.setStatus(0);
                            return termRepository.findAll(qo);
                        }
                    }

                    @Override
                    public Map<Integer, List<Term>> findByKeys(Collection<Integer> ids) throws RepositoryException {
                        return null;
                    }

                }, new ListModelConverter<Term>(Term.class));
    }

    @Override
    public void save_term(Term term) throws ServiceException {

        Term ot = termRepository.findBySequence(term.getSequence());
        if (ot != null && (StringUtils.isNull(term.getId()) || term.getId().intValue() != ot.getId())) {
            throw new ServiceException(0);//"不能重复添加，请检查字段"
        }
        termRepository.save(term);
        clearCache();
    }

    @Override
    public Term term(int id) {
        return termRepository.getOne(id);
    }

    @Override
    public List<Term> terms(boolean admin) {
        if (admin) {
            return termsCache.findByKey(0);
        } else {
            return termsCache.findByKey((int) Constants.STATUS_OK);
        }
    }

    @Override
    public void set_term_default(int id) {
        termRepository.removeDefault();
        termRepository.setDefault(id);
        clearCache();
    }

    @Override
    public void update_status(int id, byte status) throws ServiceException {
        Term term = term(id);
        term.setStatus(status);
        termRepository.save(term);
        clearCache();
    }

    private void clearCache() {
        termsCache.remove(0);
        termsCache.remove((int) Constants.STATUS_OK);
    }
}
