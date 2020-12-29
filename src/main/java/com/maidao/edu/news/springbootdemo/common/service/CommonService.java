package com.maidao.edu.news.springbootdemo.common.service;

import com.maidao.edu.news.springbootdemo.api.trainee.model.Trainee;
import com.maidao.edu.news.springbootdemo.api.trainee.repository.ITraineeRepository;
import com.maidao.edu.news.springbootdemo.api.trainer.model.Trainer;
import com.maidao.edu.news.springbootdemo.api.trainer.repository.ITrainerRepository;
import com.maidao.edu.news.springbootdemo.common.cache.CacheOptions;
import com.maidao.edu.news.springbootdemo.common.cache.KvCacheFactory;
import com.maidao.edu.news.springbootdemo.common.cache.KvCacheWrap;
import com.maidao.edu.news.springbootdemo.common.entity.Constants;
import com.maidao.edu.news.springbootdemo.common.entity.ValCode;
import com.maidao.edu.news.springbootdemo.common.entity.ValCodeConstants;
import com.maidao.edu.news.springbootdemo.common.exception.RepositoryException;
import com.maidao.edu.news.springbootdemo.common.exception.ServiceException;
import com.maidao.edu.news.springbootdemo.common.util.StringUtils;
import com.sunnysuperman.kvcache.RepositoryProvider;
import com.sunnysuperman.kvcache.converter.BeanModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;

import static com.maidao.edu.news.springbootdemo.api.user.model.UserErrors.ERR_MOBILECODE_NONE;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
@Service
public class CommonService implements ICommonService {

    @Autowired
    private KvCacheFactory kvCacheFactory;

    private KvCacheWrap<Long, ValCode> valCodeCache;

    @Autowired
    private ITrainerRepository trainerRepository;

    @Autowired
    private ITraineeRepository traineeRepository;

    @PostConstruct
    public void init() {
        valCodeCache = kvCacheFactory.create(new CacheOptions("val_code", 1, 600),
                new RepositoryProvider<Long, ValCode>() {

                    @Override
                    public ValCode findByKey(Long key) throws Exception {
//                        throw new Exception();
                        return null;
                    }

                    @Override
                    public Map<Long, ValCode> findByKeys(Collection<Long> ids) throws RepositoryException {
                        throw new UnsupportedOperationException("findByKeys");
                    }

                }, new BeanModelConverter<>(ValCode.class));
    }

    @Override
    public void saveValCode(Long key, ValCode valCode) {
        valCodeCache.save(key, valCode);
    }

    @Override
    public ValCode getValCode(Long key) throws ServiceException {
//        try {
//            return valCodeCache.findByKey(key);
//        } catch (Exception e) {
//            throw new ServiceException(AdminErrors.ERR_CODE_INVALID);
//        }

        ValCode valCode = valCodeCache.findByKey(key);
        if (valCode == null) {
            throw new ServiceException(ERR_MOBILECODE_NONE);
        }
        return valCode;
    }

    @Override
    public void reset_password(ValCode valCode, String password, String salt) throws ServiceException {

        ValCode vc = getValCode(valCode.getKey());

        if (!(valCode.getUserType().intValue() == vc.getUserType() && valCode.getAccountType().intValue() == vc.getAccountType()
                && valCode.getAccount().equals(vc.getAccount()) && valCode.getCode().equals(vc.getCode()))) {
            throw new ServiceException(0);
        }

        boolean isEmail = valCode.getAccountType() == ValCodeConstants.EMAIL;
        String account = valCode.getAccount();

        if (valCode.getUserType() == ValCodeConstants.TRAINER) {
            Trainer trainer = isEmail ? trainerRepository.findByEmailAndStatus(account, Constants.STATUS_OK) : trainerRepository.findByMobileAndStatus(account, Constants.STATUS_OK);
            if (trainer == null) {
                throw new ServiceException(0);
            }

            trainer.setPassword(StringUtils.getMD5(password, salt));
            trainerRepository.save(trainer);
        } else {
            Trainee trainee = isEmail ? traineeRepository.findByEmailAndStatus(account, Constants.STATUS_OK) : traineeRepository.findByMobileAndStatus(account, Constants.STATUS_OK);
            if (trainee == null) {
                throw new ServiceException(0);
            }

            trainee.setPassword(StringUtils.getMD5(password, salt));
            traineeRepository.save(trainee);
        }

    }
}