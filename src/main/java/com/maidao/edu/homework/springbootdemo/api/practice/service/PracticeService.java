package com.maidao.edu.homework.springbootdemo.api.practice.service;


import com.maidao.edu.homework.springbootdemo.api.practice.entity.PracticeItemType;
import com.maidao.edu.homework.springbootdemo.api.practice.entity.PracticeOption;
import com.maidao.edu.homework.springbootdemo.api.practice.model.Practice;
import com.maidao.edu.homework.springbootdemo.api.practice.qo.PracticeQo;
import com.maidao.edu.homework.springbootdemo.api.practice.qo.PracticeWo;
import com.maidao.edu.homework.springbootdemo.api.practice.repository.IPracticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:PracticeService
 * 类描述:业务逻辑层实现类
 **/
@Service
public class PracticeService implements IPracticeService {

    @Autowired
    private IPracticeRepository practiceRepository;

    @Override
    public Practice practice(int id) {
        return practiceRepository.getOne(id);
    }

    @Override
    public List<Practice> practices(PracticeQo qo, PracticeWo wo) {
        List<Practice> practices = practiceRepository.findAll(qo);
        for (Practice practice : practices) {
            wrapPractice(practice, wo);
        }
        return practices;
    }

    private void wrapPractice(Practice practice, PracticeWo wo) {
        if (wo.isWithoutAnswer()) {
            String type = practice.getType();
            if (type.equals(PracticeItemType.SELECT.getKey()) || type.equals(PracticeItemType.MSELECT.getKey())) {
                for (PracticeOption option : practice.getOptions()) {
                    option.setIsKey(0);
                }
            } else {
                practice.setAnswer(null);
            }
        }
    }

    @Override
    public void save_practice(Practice practice) throws Exception {

        Integer id = practice.getId();
        if (id == null || id == 0) {
            practice.setCreatedAt(System.currentTimeMillis());
            practiceRepository.save(practice);
        } else {
            Practice ot = practice(id);
            ot.setType(practice.getType());
            ot.setQuestion(practice.getQuestion());
            ot.setOptions(practice.getOptions());
            ot.setAnswer(practice.getAnswer());
            ot.setScore(practice.getScore());
            ot.setPriority(practice.getPriority());
            practiceRepository.save(ot);
        }
    }

    @Override
    public void save_practices(List<Practice> practices) throws Exception {
        for (Practice practice : practices) {
            save_practice(practice);
        }
    }

    @Override
    public void remove_practice(int id) throws Exception {
        practiceRepository.deleteById(id);
    }

    @Override
    public int countByLessonId(int lessonId) {
        return practiceRepository.countAllByLessonIdEquals(lessonId);
    }
}
