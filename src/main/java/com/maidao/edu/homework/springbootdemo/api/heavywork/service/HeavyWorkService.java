package com.maidao.edu.homework.springbootdemo.api.heavywork.service;

import com.maidao.edu.homework.springbootdemo.api.heavywork.entity.HeavyWorkConstants;
import com.maidao.edu.homework.springbootdemo.api.heavywork.model.HeavyWork;
import com.maidao.edu.homework.springbootdemo.api.heavywork.repository.HeavyWorkRepository;
import com.maidao.edu.homework.springbootdemo.common.exception.ServiceException;
import com.maidao.edu.homework.springbootdemo.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:HeavyWorkService
 * 类描述:业务逻辑层具体实现类
 **/
@Service
public class HeavyWorkService implements IHeavyWorkService {

    @Autowired
    private HeavyWorkRepository heavyWorkRepository;

    @Override
    public HeavyWork create(String owner) throws Exception {
        HeavyWork job = new HeavyWork();
        job.setSecret(StringUtils.getRandNum(64));
        job.setOwner(owner);
        job.setProgress(0);
        job.setCreatedAt(System.currentTimeMillis());
        job.setUpdatedAt(job.getCreatedAt());
        job.setStatus(HeavyWorkConstants.STATUS_INIT);
        heavyWorkRepository.save(job);
        return job;
    }

    @Override
    public void updateProgress(Integer id, int progress) {
        if (progress > 100) {
            progress = 100;
        }
        HeavyWork work = heavyWorkRepository.getOne(id);
        work.setUpdatedAt(System.currentTimeMillis());
        work.setProgress(progress);

        heavyWorkRepository.save(work);

    }

    @Override
    public void updateFailed(Integer id, String errors) {
        HeavyWork work = heavyWorkRepository.getOne(id);
        work.setUpdatedAt(System.currentTimeMillis());
        work.setStatus(HeavyWorkConstants.STATUS_FAILED);
        work.setErrors(errors);

        heavyWorkRepository.save(work);

    }

    @Override
    public void updateSuccess(Integer id, String output) {

        HeavyWork work = heavyWorkRepository.getOne(id);
        work.setUpdatedAt(System.currentTimeMillis());
        work.setStatus(HeavyWorkConstants.STATUS_SUCCESS);
        work.setProgress(100);
        work.setOutput(output);

        heavyWorkRepository.save(work);

    }


    @Override
    public HeavyWork getById(Integer id, String secret) throws Exception {

        HeavyWork work = heavyWorkRepository.getOne(id);
        if (work == null || !work.getSecret().equals(secret)) {
            throw new ServiceException(0);//没有找到任务");
        }
        return work;
    }

}
