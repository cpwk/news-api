package com.maidao.edu.news.api.trainee.service;


import com.maidao.edu.news.common.entity.ValCode;
import com.maidao.edu.news.common.exception.ServiceException;
import com.maidao.edu.news.api.heavywork.model.HeavyWork;
import com.maidao.edu.news.api.trainee.model.Trainee;
import com.maidao.edu.news.api.trainee.model.TraineeSession;
import com.maidao.edu.news.api.trainee.qo.TraineeQo;
import com.maidao.edu.news.api.trainee.qo.TraineeSessionQo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
public interface ITraineeService {

    //trainee

    void save_trainee(Trainee trainee) throws ServiceException;

    Trainee getByUsername(String username);

    Page<Trainee> trainees(TraineeQo qo);

    HeavyWork exportTrainees(TraineeQo qo) throws Exception;

    void remove_trainee(int id, boolean root) throws ServiceException;

    Trainee trainee(int id, boolean init);

    void trainee_status(int id, byte status) throws ServiceException;

    void update_password(Integer id, String password, boolean root) throws ServiceException;

    void update_my_password(String password, String oldPassword) throws ServiceException;

    void reset_my_password(ValCode valCode, String password) throws ServiceException;

    // signin
    Map signin(Trainee trainee, ValCode valCode, String ip) throws ServiceException;

    TraineeSession traineeSession(String token);

    Map profile() throws Exception;

    Page<TraineeSession> traineeSessions(TraineeSessionQo qo) throws Exception;

}
