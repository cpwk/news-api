package com.maidao.edu.news.springbootdemo.api.qa.service;

import com.maidao.edu.news.springbootdemo.api.heavywork.model.HeavyWork;
import com.maidao.edu.news.springbootdemo.api.heavywork.service.IHeavyWorkService;
import com.maidao.edu.news.springbootdemo.api.qa.entity.FormItemType;
import com.maidao.edu.news.springbootdemo.api.qa.entity.QAConstants;
import com.maidao.edu.news.springbootdemo.api.qa.entity.QAItem;
import com.maidao.edu.news.springbootdemo.api.qa.model.QAPaper;
import com.maidao.edu.news.springbootdemo.api.qa.model.QATemplate;
import com.maidao.edu.news.springbootdemo.api.qa.qo.QAPaperQo;
import com.maidao.edu.news.springbootdemo.api.qa.repository.IQAPaperRepository;
import com.maidao.edu.news.springbootdemo.api.qa.repository.IQATemplateRepository;
import com.maidao.edu.news.springbootdemo.common.exception.ServiceException;
import com.maidao.edu.news.springbootdemo.common.file.entity.UploadOptions;
import com.maidao.edu.news.springbootdemo.common.file.service.FileService;
import com.maidao.edu.news.springbootdemo.common.region.RegionService;
import com.maidao.edu.news.springbootdemo.common.task.ApiTask;
import com.maidao.edu.news.springbootdemo.common.task.TaskService;
import com.maidao.edu.news.springbootdemo.common.util.L;
import com.sunnysuperman.commons.util.FileUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
@Service
public class QAService implements IQAService {

    @Autowired
    private IQATemplateRepository qaTemplateRepository;

    @Autowired
    private IQAPaperRepository qaPaperRepository;

    @Autowired
    private IHeavyWorkService heavyWorkService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FileService fileService;

    @Autowired
    private RegionService regionService;

    @Override
    public List<QATemplate> qaTemplates() {
        return qaTemplateRepository.findAll();
    }

    @Override
    public QATemplate qaTemplate(int id) {
        return qaTemplateRepository.getOne(id);
    }

    @Override
    public void saveQATemplate(QATemplate qaTemplate) throws ServiceException {
        Integer id = qaTemplate.getId();
        if (id == null || id == 0) {
            qaTemplate.setCreatedAt(System.currentTimeMillis());
            qaTemplateRepository.save(qaTemplate);
        } else {
            QATemplate ot = qaTemplate(id);
            ot.setTitle(qaTemplate.getTitle());
            ot.setDescr(qaTemplate.getDescr());
            ot.setItems(qaTemplate.getItems());
            ot.setStatus(qaTemplate.getStatus());
            qaTemplateRepository.save(ot);
        }
    }

    @Override
    public void statusQATemplate(int id, byte status) throws ServiceException {
        QATemplate tpl = qaTemplate(id);
        tpl.setStatus(status);
        qaTemplateRepository.save(tpl);
    }

    @Override
    public Page<QAPaper> qaPapers(QAPaperQo qo) {
        return qaPaperRepository.findAll(qo);
    }

    @Override
    public HeavyWork exportQAPapers(QAPaperQo qo) throws Exception {
        HeavyWork task = heavyWorkService.create(String.valueOf(System.currentTimeMillis()));

        qo.setPageSize(500);

        taskService.addTask(new ExportQAPapersTask(task, qo));
        return task;
    }

    @Override
    public void saveQAPaper(QAPaper qaPaper) {
        qaPaper.setCreatedAt(System.currentTimeMillis());
        qaPaper.setStatus(QAConstants.STATUS_UNDEAL);
        qaPaperRepository.save(qaPaper);
    }

    @Override
    public void statusQAPaper(int id, byte status) {
        QAPaper paper = qaPaperRepository.getOne(id);
        paper.setStatus(status);
        paper.setDealAt(System.currentTimeMillis());
        qaPaperRepository.save(paper);
    }

    @Override
    public void removeQAPaper(int id) throws ServiceException {
        QAPaper paper = qaPaperRepository.getOne(id);
        if (paper.getStatus() == QAConstants.STATUS_DROPPED) {
            qaPaperRepository.deleteById(id);
        } else {
            throw new ServiceException(0);
        }
    }

    private class ExportQAPapersTask extends ApiTask {
        HeavyWork job;
        QAPaperQo qo;
        int progress = 0;

        public ExportQAPapersTask(HeavyWork job, QAPaperQo qo) {
            super();
            this.job = job;
            this.qo = qo;
        }

        private Workbook writeToWorkbook() throws Exception {

            QATemplate tpl = qaTemplateRepository.getOne(qo.getTplId());

            SXSSFWorkbook wb = new SXSSFWorkbook(100);
            SXSSFSheet sheet = wb.createSheet(tpl.getTitle());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            int rowIndex = 0;
            {
                int cellIndex = -1;
                Row titleRow = sheet.createRow(rowIndex);

                for (QAItem item : tpl.getItems()) {
                    if (!item.getType().equals(FormItemType.TIP.getKey())) {
                        titleRow.createCell(++cellIndex).setCellValue(item.getQuestion());
                    }
                }
            }
            Page<QAPaper> page;
            int sum = 0;

            while (true) {
                // 查询
                page = qaPaperRepository.findAll(qo);
                if (page.getContent().size() == 0) {
                    break;
                }
                // 写入Excel
                for (QAPaper paper : page.getContent()) {
                    Row row = sheet.createRow(++rowIndex);
                    int cellIndex = -1;

                    for (QAItem item : paper.getItems()) {

                        String type = item.getType();
                        List<String> values = item.getValues();

                        if (type.equals(FormItemType.TIP.getKey())) {
                            //do nothing
                        } else if (type.equals(FormItemType.DATE.getKey())) {
                            String date = "";
                            if (values.size() > 0) {
                                try {
                                    long t = Long.parseLong(values.get(0));
                                    date = sdf.format(new Date(t));

                                } catch (Exception e) {
                                    L.warn("exportQAPaper 时间转换错误[ id= " + paper.getId() + " ]");
                                }
                            }
                            row.createCell(++cellIndex).setCellValue(date);
                        } else if (type.equals(FormItemType.MSELECT.getKey())) {
                            row.createCell(++cellIndex).setCellValue(values.size() > 0 ? values.toString() : "");
                        } else if (type.equals(FormItemType.CITY.getKey())) {
                            row.createCell(++cellIndex).setCellValue((values.size() > 0 && values.get(0).length() == 6) ? regionService.getPCD(values.get(0)) : "");
                        } else {
                            row.createCell(++cellIndex).setCellValue(values.size() > 0 ? values.get(0) : "");
                        }
                    }
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
                file = fileService.createTmpFile("qapaper-export", "xlsx");
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
