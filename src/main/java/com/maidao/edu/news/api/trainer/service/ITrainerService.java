package com.maidao.edu.news.api.trainer.service;

import com.maidao.edu.news.api.trainer.model.Trainer;
import com.maidao.edu.news.api.trainer.model.TrainerSession;
import com.maidao.edu.news.api.trainer.qo.TrainerQo;
import com.maidao.edu.news.api.trainer.qo.TrainerSessionQo;
import com.maidao.edu.news.common.entity.ValCode;
import com.maidao.edu.news.common.exception.ServiceException;
import org.springframework.data.domain.Page;

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
public interface ITrainerService {

    //trainer

    void save_trainer(Trainer trainer) throws ServiceException;

    Trainer getByUsername(String username);

    List<Trainer> trainers(TrainerQo qo);

    void remove_trainer(int id, boolean root) throws ServiceException;

    Trainer trainer(int id, boolean init);

    void trainer_status(int id, byte status) throws ServiceException;

    void update_password(Integer id, String password, boolean root) throws ServiceException;

    void update_my_password(String password, String oldPassword) throws ServiceException;

    void reset_my_password(ValCode valCode, String password) throws ServiceException;

    // signin
    Map signin(Trainer trainer, ValCode valCode, String ip) throws ServiceException;

    TrainerSession trainerSession(String token);

    Map profile() throws Exception;

    Page<TrainerSession> trainerSessions(TrainerSessionQo qo) throws Exception;

}
