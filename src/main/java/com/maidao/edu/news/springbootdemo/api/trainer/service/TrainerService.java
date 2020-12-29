package com.maidao.edu.news.springbootdemo.api.trainer.service;

import com.maidao.edu.news.springbootdemo.api.trainer.authority.TrainerContext;
import com.maidao.edu.news.springbootdemo.api.trainer.authority.TrainerSessionWrap;
import com.maidao.edu.news.springbootdemo.api.trainer.model.Trainer;
import com.maidao.edu.news.springbootdemo.api.trainer.model.TrainerSession;
import com.maidao.edu.news.springbootdemo.api.trainer.qo.TrainerQo;
import com.maidao.edu.news.springbootdemo.api.trainer.qo.TrainerSessionQo;
import com.maidao.edu.news.springbootdemo.api.trainer.repository.ITrainerRepository;
import com.maidao.edu.news.springbootdemo.api.trainer.repository.ITrainerSessionRepository;
import com.maidao.edu.news.springbootdemo.common.cache.CacheOptions;
import com.maidao.edu.news.springbootdemo.common.cache.KvCacheFactory;
import com.maidao.edu.news.springbootdemo.common.cache.KvCacheWrap;
import com.maidao.edu.news.springbootdemo.common.context.Context;
import com.maidao.edu.news.springbootdemo.common.context.Contexts;
import com.maidao.edu.news.springbootdemo.common.entity.Constants;
import com.maidao.edu.news.springbootdemo.common.entity.ValCode;
import com.maidao.edu.news.springbootdemo.common.exception.RepositoryException;
import com.maidao.edu.news.springbootdemo.common.exception.ServiceException;
import com.maidao.edu.news.springbootdemo.common.ipseeker.IPSeekerUtil;
import com.maidao.edu.news.springbootdemo.common.service.ICommonService;
import com.maidao.edu.news.springbootdemo.common.util.CollectionUtil;
import com.maidao.edu.news.springbootdemo.common.util.DateUtils;
import com.maidao.edu.news.springbootdemo.common.util.StringUtils;
import com.sunnysuperman.kvcache.RepositoryProvider;
import com.sunnysuperman.kvcache.converter.BeanModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Date;
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
public class TrainerService implements ITrainerService {

    @Value("${trainer.tokenSalt}")
    private String salt;

    @Autowired
    private ITrainerRepository trainerRepository;

    @Autowired
    private ITrainerSessionRepository trainerSessionRepository;

    @Autowired
    private ICommonService commonService;

    @Autowired
    private KvCacheFactory kvCacheFactory;

    private KvCacheWrap<Integer, Trainer> trainerCache;

    @PostConstruct
    public void init() {
        trainerCache = kvCacheFactory.create(new CacheOptions("trainer", 1, Constants.CACHE_REDIS_EXPIRE),
                new RepositoryProvider<Integer, Trainer>() {

                    @Override
                    public Trainer findByKey(Integer key) throws RepositoryException {
                        return trainerRepository.getOne(key);
                    }

                    @Override
                    public Map<Integer, Trainer> findByKeys(Collection<Integer> ids) throws RepositoryException {
                        throw new UnsupportedOperationException("findByKeys");
                    }

                }, new BeanModelConverter<>(Trainer.class));
    }

    @Override
    public void save_trainer(Trainer trainer) throws ServiceException {
        Trainer oa = getByUsername(trainer.getUsername());
        Trainer a_mobile = StringUtils.isEmpty(trainer.getMobile()) ? null : trainerRepository.findByMobileAndStatus(trainer.getMobile(), Constants.STATUS_OK);
        Trainer a_email = StringUtils.isEmpty(trainer.getEmail()) ? null : trainerRepository.findByEmailAndStatus(trainer.getEmail(), Constants.STATUS_OK);

        if (trainer.getId() != null && trainer.getId() > 0) {
            if (oa == null)
                throw new ServiceException(0);

            if (a_mobile != null && a_mobile.getId().intValue() != trainer.getId()) {
                throw new ServiceException(0);//重复手机号
            }
            if (a_email != null && a_email.getId().intValue() != trainer.getId()) {
                throw new ServiceException(0);//重复邮箱
            }

            oa.setName(trainer.getName());
            oa.setJob(trainer.getJob());
            oa.setWorkNum(trainer.getWorkNum());
            oa.setGender(trainer.getGender());
            oa.setMobile(trainer.getMobile());
            oa.setEmail(trainer.getEmail());
            oa.setIntro(trainer.getIntro());
            oa.setImg(trainer.getImg());

            if (!StringUtils.isNull(trainer.getPassword()))
                oa.setPassword(StringUtils.getMD5(trainer.getPassword(), salt));
            oa.setOnjob(trainer.getOnjob());

            trainerRepository.save(oa);
            trainerCache.remove(trainer.getId());
        } else {
            if (oa != null) {
                throw new ServiceException(0);//用户名已存在
            }
            if (a_mobile != null) {
                throw new ServiceException(0);//重复手机号
            }
            if (a_email != null) {
                throw new ServiceException(0);//重复邮箱
            }

            trainer.setPassword(StringUtils.getMD5(trainer.getPassword(), salt));
            trainer.setStatus(Constants.STATUS_OK);
            trainer.setOnjob(Constants.STATUS_OK);
            trainer.setCreatedAt(System.currentTimeMillis());
            trainerRepository.save(trainer);
        }
    }

    @Override
    public Trainer getByUsername(String username) {
        try {
            username = StringUtils.escapeSql(username);
            return trainerRepository.findByUsernameAndStatus(username, Constants.STATUS_OK);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Trainer> trainers(TrainerQo qo) {
        return trainerRepository.findAll(qo);
    }

    @Override
    public void remove_trainer(int id, boolean root) throws ServiceException {
        Trainer trainer = trainer(id, false);
        trainer.setStatus(Constants.STATUS_HALT);
        trainerRepository.save(trainer);
        trainerCache.remove(id);
    }

    @Override
    public Trainer trainer(int id, boolean init) {
        Trainer trainer = trainer(id);
        if (init) {
        }
        return trainer;
    }

    private Trainer trainer(int id) {
        return trainerCache.findByKey(id);
    }

    @Override
    public void trainer_status(int id, byte status) throws ServiceException {
        Trainer trainer = trainer(id, false);
        trainer.setStatus(status);
        trainerRepository.save(trainer);
        trainerCache.remove(id);
    }

    @Override
    public void update_password(Integer id, String password, boolean root) throws ServiceException {
        Trainer trainer = trainer(id, false);
        trainer.setPassword(StringUtils.getMD5(password, salt));
        trainerRepository.save(trainer);
    }

    @Override
    public void update_my_password(String password, String oldPassword) throws ServiceException {
        TrainerSessionWrap wrap = TrainerContext.requireSessionWrap();
        TrainerSession session = wrap.getTrainerSession();
        Trainer trainer = trainerRepository.getOne(session.getTrainerId());
        if (!trainer.getPassword().equals(StringUtils.getMD5(oldPassword, salt))) {
            throw new ServiceException(0);
        }
        trainer.setPassword(StringUtils.getMD5(password, salt));
        trainerRepository.save(trainer);
    }

    @Override
    public void reset_my_password(ValCode valCode, String password) throws ServiceException {
        commonService.reset_password(valCode, password, salt);
    }

    @Override
    public Map signin(Trainer a, ValCode valCode, String ip) throws ServiceException {

        ValCode vCode = commonService.getValCode(valCode.getKey());
        if (valCode.getCode().equals(vCode.getCode())) {

            Trainer trainer = getByUsername(a.getUsername());

            if (trainer == null)
                throw new ServiceException(0);

            if (trainer.getPassword().equals(StringUtils.getMD5(a.getPassword(), salt))) {
                if (trainer.getOnjob() == Constants.STATUS_OK) {
                    String token = StringUtils.getToken(salt);
                    TrainerSession session = saveTrainerSession(trainer, token, ip);
                    trainer.setSigninAt(System.currentTimeMillis());
                    trainerRepository.save(trainer);

                    TrainerSessionWrap wrap = new TrainerSessionWrap(trainer, session);

                    Context context = Contexts.get();
                    context.setSession(wrap);

                    return CollectionUtil.arrayAsMap("trainer", trainer, "session", session);

                } else {
                    throw new ServiceException(0);
                }
            } else {
                throw new ServiceException(0);
            }
        } else {
            throw new ServiceException(0);
        }
    }

    private TrainerSession saveTrainerSession(Trainer trainer, String token, String ip) {
        TrainerSession session = new TrainerSession();
        session.setTrainerId(trainer.getId());
        session.setName(trainer.getName());
        session.setToken(token);
        session.setSigninAt(System.currentTimeMillis());
        session.setExpireAt(DateUtils.getDate_days(new Date(), Constants.SESSION_EXPIRE_DAYS).getTime());
        session.setIp(ip);
        session.setLocation(IPSeekerUtil.getFullLocation(ip));
        trainerSessionRepository.save(session);
        return session;
    }

    @Override
    public TrainerSession trainerSession(String token) {
        return trainerSessionRepository.findByToken(token);
    }

    @Override
    public Page<TrainerSession> trainerSessions(TrainerSessionQo qo) throws Exception {
        return trainerSessionRepository.findAll(qo);
    }

    @Override
    public Map profile() throws Exception {
        Integer trainerId = Contexts.requestTrainerId();
        Trainer trainer = trainer(trainerId, true);
        return CollectionUtil.arrayAsMap("trainer", trainer);
    }
}