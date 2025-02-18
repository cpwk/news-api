package com.maidao.edu.news.api.trainee.service;


import com.maidao.edu.news.api.heavywork.model.HeavyWork;
import com.maidao.edu.news.api.heavywork.service.IHeavyWorkService;
import com.maidao.edu.news.api.trainee.authority.TraineeContext;
import com.maidao.edu.news.api.trainee.authority.TraineeSessionWrap;
import com.maidao.edu.news.api.trainee.model.Trainee;
import com.maidao.edu.news.api.trainee.model.TraineeSession;
import com.maidao.edu.news.api.trainee.qo.TraineeQo;
import com.maidao.edu.news.api.trainee.qo.TraineeSessionQo;
import com.maidao.edu.news.api.trainee.repository.ITraineeRepository;
import com.maidao.edu.news.api.trainee.repository.ITraineeSessionRepository;
import com.maidao.edu.news.common.cache.CacheOptions;
import com.maidao.edu.news.common.cache.KvCacheFactory;
import com.maidao.edu.news.common.cache.KvCacheWrap;
import com.maidao.edu.news.common.context.Context;
import com.maidao.edu.news.common.context.Contexts;
import com.maidao.edu.news.common.entity.Constants;
import com.maidao.edu.news.common.entity.ValCode;
import com.maidao.edu.news.common.exception.RepositoryException;
import com.maidao.edu.news.common.exception.ServiceException;
import com.maidao.edu.news.common.file.entity.UploadOptions;
import com.maidao.edu.news.common.file.service.FileService;
import com.maidao.edu.news.common.ipseeker.IPSeekerUtil;
import com.maidao.edu.news.common.service.ICommonService;
import com.maidao.edu.news.common.task.ApiTask;
import com.maidao.edu.news.common.task.TaskService;
import com.maidao.edu.news.common.util.CollectionUtil;
import com.maidao.edu.news.common.util.DateUtils;
import com.maidao.edu.news.common.util.L;
import com.maidao.edu.news.common.util.StringUtils;
import com.sunnysuperman.commons.util.FileUtil;
import com.sunnysuperman.kvcache.RepositoryProvider;
import com.sunnysuperman.kvcache.converter.BeanModelConverter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
public class TraineeService implements ITraineeService {

    @Value("${trainee.tokenSalt}")
    private String salt;

    @Autowired
    private ITraineeRepository traineeRepository;

    @Autowired
    private ITraineeSessionRepository traineeSessionRepository;

    @Autowired
    private ICommonService commonService;

    @Autowired
    private IHeavyWorkService heavyWorkService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FileService fileService;

    @Autowired
    private KvCacheFactory kvCacheFactory;

    private KvCacheWrap<Integer, Trainee> traineeCache;

    @PostConstruct
    public void init() {
        traineeCache = kvCacheFactory.create(new CacheOptions("trainee", 1, Constants.CACHE_REDIS_EXPIRE),
                new RepositoryProvider<Integer, Trainee>() {

                    @Override
                    public Trainee findByKey(Integer key) throws RepositoryException {
                        return traineeRepository.getOne(key);
                    }

                    @Override
                    public Map<Integer, Trainee> findByKeys(Collection<Integer> ids) throws RepositoryException {
                        throw new UnsupportedOperationException("findByKeys");
                    }

                }, new BeanModelConverter<>(Trainee.class));
    }

    @Override
    public void save_trainee(Trainee trainee) throws ServiceException {
        Trainee oa = getByUsername(trainee.getUsername());
        Trainee a_mobile = StringUtils.isEmpty(trainee.getMobile()) ? null : traineeRepository.findByMobileAndStatus(trainee.getMobile(), Constants.STATUS_OK);
        Trainee a_email = StringUtils.isEmpty(trainee.getEmail()) ? null : traineeRepository.findByEmailAndStatus(trainee.getEmail(), Constants.STATUS_OK);

        if (trainee.getId() != null && trainee.getId() > 0) {
            if (oa == null)
                throw new ServiceException(0);

            if (a_mobile != null && a_mobile.getId().intValue() != trainee.getId()) {
                throw new ServiceException(0);//重复手机号");
            }
            if (a_email != null && a_email.getId().intValue() != trainee.getId()) {
                throw new ServiceException(0);//重复邮箱");
            }

            oa.setName(trainee.getName());
            oa.setImg(trainee.getImg());
            oa.setEducationType(trainee.getEducationType());
            oa.setStudentNum(trainee.getStudentNum());
            oa.setGender(trainee.getGender());
            oa.setMobile(trainee.getMobile());
            oa.setEmail(trainee.getEmail());
            oa.setAddress(trainee.getAddress());
            oa.setAdmissionAt(trainee.getAdmissionAt());
            oa.setTerm(trainee.getTerm());

            if (!StringUtils.isNull(trainee.getPassword()))
                oa.setPassword(StringUtils.getMD5(trainee.getPassword(), salt));
            oa.setInSchool(trainee.getInSchool());

            traineeRepository.save(oa);
        } else {
            if (oa != null) {
                throw new ServiceException(0);//用户名已存在");
            }
            if (a_mobile != null) {
                throw new ServiceException(0);//重复手机号");
            }
            if (a_email != null) {
                throw new ServiceException(0);//重复邮箱");
            }

            trainee.setPassword(StringUtils.getMD5(trainee.getPassword(), salt));
            trainee.setStatus(Constants.STATUS_OK);
            trainee.setInSchool(Constants.STATUS_OK);
            trainee.setCreatedAt(System.currentTimeMillis());
            traineeRepository.save(trainee);
        }
    }

    @Override
    public Trainee getByUsername(String username) {
        try {
            username = StringUtils.escapeSql(username);
            return traineeRepository.findByUsernameAndStatus(username, Constants.STATUS_OK);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Page<Trainee> trainees(TraineeQo qo) {
        return traineeRepository.findAll(qo);
    }


    @Override
    public HeavyWork exportTrainees(TraineeQo qo) throws Exception {
        HeavyWork task = heavyWorkService.create(String.valueOf(System.currentTimeMillis()));

        qo.setPageSize(500);

        taskService.addTask(new ExportActivitiesTask(task, qo));
        return task;
    }

    @Override
    public void remove_trainee(int id, boolean root) throws ServiceException {
        Trainee trainee = trainee(id, false);
        trainee.setStatus(Constants.STATUS_HALT);
        traineeRepository.save(trainee);
    }


    @Override
    public Trainee trainee(int id, boolean init) {
        Trainee trainee = trainee(id);
        if (init) {
        }
        return trainee;
    }

    private Trainee trainee(int id) {
        return traineeCache.findByKey(id);
    }

    @Override
    public void trainee_status(int id, byte status) throws ServiceException {
        Trainee trainee = trainee(id, false);
        trainee.setStatus(status);
        traineeRepository.save(trainee);
    }

    @Override
    public void update_password(Integer id, String password, boolean root) throws ServiceException {
        Trainee trainee = trainee(id, false);
        trainee.setPassword(StringUtils.getMD5(password, salt));
        traineeRepository.save(trainee);
    }

    @Override
    public void update_my_password(String password, String oldPassword) throws ServiceException {
        TraineeSessionWrap wrap = TraineeContext.requireSessionWrap();
        TraineeSession session = wrap.getTraineeSession();
        Trainee trainee = traineeRepository.getOne(session.getTraineeId());
        if (!trainee.getPassword().equals(StringUtils.getMD5(oldPassword, salt))) {
            throw new ServiceException(0);
        }
        trainee.setPassword(StringUtils.getMD5(password, salt));
        traineeRepository.save(trainee);
    }

    @Override
    public void reset_my_password(ValCode valCode, String password) throws ServiceException {
        commonService.reset_password(valCode, password, salt);
    }

    @Override
    public Map signin(Trainee a, ValCode valCode, String ip) throws ServiceException {

        ValCode vCode = commonService.getValCode(valCode.getKey());

        if (vCode.getCode().equals(valCode.getCode())) {

            Trainee trainee = getByUsername(a.getUsername());

            if (trainee == null)
                throw new ServiceException(0);

            if (trainee.getPassword().equals(StringUtils.getMD5(a.getPassword(), salt))) {
                if (trainee.getInSchool() == Constants.STATUS_OK) {
                    String token = StringUtils.getToken(salt);
                    TraineeSession session = saveTraineeSession(trainee, token, ip);
                    trainee.setSigninAt(System.currentTimeMillis());
                    traineeRepository.save(trainee);

                    TraineeSessionWrap wrap = new TraineeSessionWrap(trainee, session);

                    Context context = Contexts.get();
                    context.setSession(wrap);

                    return CollectionUtil.arrayAsMap("trainee", trainee, "session", session);

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

    private TraineeSession saveTraineeSession(Trainee trainee, String token, String ip) {
        TraineeSession session = new TraineeSession();
        session.setTraineeId(trainee.getId());
        session.setName(trainee.getName());
        session.setToken(token);
        session.setSigninAt(System.currentTimeMillis());
        session.setExpireAt(DateUtils.getDate_days(new Date(), Constants.SESSION_EXPIRE_DAYS).getTime());
        session.setIp(ip);
        session.setLocation(IPSeekerUtil.getFullLocation(ip));
        traineeSessionRepository.save(session);
        return session;
    }

    @Override
    public TraineeSession traineeSession(String token) {
        return traineeSessionRepository.findByToken(token);
    }

    @Override
    public Page<TraineeSession> traineeSessions(TraineeSessionQo qo) throws Exception {
        return traineeSessionRepository.findAll(qo);
    }

    @Override
    public Map profile() throws Exception {
        Integer traineeId = Contexts.requestTrainerId();
        Trainee trainee = trainee(traineeId, true);
        return CollectionUtil.arrayAsMap("trainee", trainee);
    }

    private class ExportActivitiesTask extends ApiTask {
        HeavyWork job;
        TraineeQo qo;
        int progress = 0;

        public ExportActivitiesTask(HeavyWork job, TraineeQo qo) {
            super();
            this.job = job;
            this.qo = qo;
        }

        private Workbook writeToWorkbook() throws Exception {
            SXSSFWorkbook wb = new SXSSFWorkbook(100);
            SXSSFSheet sheet = wb.createSheet("学员表");
            int rowIndex = 0;
            {
                int cellIndex = -1;
                Row titleRow = sheet.createRow(rowIndex);
                titleRow.createCell(++cellIndex).setCellValue("姓名");
                titleRow.createCell(++cellIndex).setCellValue("学历");
                titleRow.createCell(++cellIndex).setCellValue("学期");
                titleRow.createCell(++cellIndex).setCellValue("学号");
                titleRow.createCell(++cellIndex).setCellValue("入学时间");
                titleRow.createCell(++cellIndex).setCellValue("性别");
                titleRow.createCell(++cellIndex).setCellValue("身份证号码");
                titleRow.createCell(++cellIndex).setCellValue("手机");
                titleRow.createCell(++cellIndex).setCellValue("邮箱");
                titleRow.createCell(++cellIndex).setCellValue("地址");

            }
            Page<Trainee> page;
            int sum = 0;

            Map<Byte, String> edu = new HashMap<>();
            edu.put((byte) 1, "本科");
            edu.put((byte) 2, "专科");
            edu.put((byte) 3, "高中");

            while (true) {
                // 查询
                page = traineeRepository.findAll(qo);
                if (page.getContent().size() == 0) {
                    break;
                }
                // 写入Excel
                for (Trainee trainee : page.getContent()) {
                    Row row = sheet.createRow(++rowIndex);
                    int cellIndex = -1;
                    row.createCell(++cellIndex).setCellValue(trainee.getName());
                    row.createCell(++cellIndex).setCellValue(edu.get(trainee.getEducationType()));
                    row.createCell(++cellIndex).setCellValue(trainee.getTerm());
                    row.createCell(++cellIndex).setCellValue(trainee.getStudentNum());
                    row.createCell(++cellIndex).setCellValue(DateUtils.getFormatedDate(trainee.getAdmissionAt()));
                    row.createCell(++cellIndex).setCellValue(trainee.getGender() == 1 ? "男" : "女");
                    row.createCell(++cellIndex).setCellValue(trainee.getIdnumber());
                    row.createCell(++cellIndex).setCellValue(trainee.getMobile());
                    row.createCell(++cellIndex).setCellValue(trainee.getEmail());
                    row.createCell(++cellIndex).setCellValue(trainee.getAddress());

                }
                // 进度加一点
                int newProgress = progress + 5;
                if (newProgress > 90) {
                    newProgress = 90;
                }
                doUpdateProgress(newProgress);
                Pageable pageable = page.getPageable();

                // 没有更多了
                if (page.getContent().size() < qo.getPageSize()) {
                    break;
                }
                // 标记分页，由于分页算法默认-1，此处需+2
                qo.setPageNumber(qo.getPageNumber() + 2);
                // 最多10w条
                sum += page.getSize();
                if (sum >= 100000) {
                    break;
                }
            }
            return wb;
        }

        private void doUpdateProgress(int progress) {
            try {
                // 进度不能倒退
                if (progress <= this.progress) {
                    return;
                }
                this.progress = progress;
                heavyWorkService.updateProgress(job.getId(), progress);
            } catch (Exception ex) {
                L.error(ex);
            }
        }

        @Override
        protected void doApiWork() throws Exception {
            // 开始
            doUpdateProgress(1);
            // 生成表格
            Workbook wb = null;
            File file = null;
            try {
                wb = writeToWorkbook();
                file = fileService.createTmpFile("trainee-export", "xlsx");
                // 写入文件
                {
                    FileOutputStream out = new FileOutputStream(file);
                    wb.write(out);
                    out.flush();
                    out.close();
                }
                // 上传前先报告一下状态
                doUpdateProgress(95);

                // 上传
                UploadOptions options = new UploadOptions();
                options.setContentType("application/x-xls");
                String exportUrl = fileService.upload(file, options);

                // 报告成功
                heavyWorkService.updateSuccess(job.getId(), exportUrl);
            } catch (Exception ex) {
                L.error(ex);
                heavyWorkService.updateFailed(job.getId(), null);
            } finally {
                FileUtil.close(wb);
                if (file != null) {
                    FileUtil.delete(file);
                }
            }
        }

    }

}
