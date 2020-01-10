package com.maidao.edu.homework.springbootdemo.common.mail;

import com.maidao.edu.homework.springbootdemo.api.admin.model.AdminErrors;
import com.maidao.edu.homework.springbootdemo.common.entity.ValCode;
import com.maidao.edu.homework.springbootdemo.common.exception.ServiceException;
import com.maidao.edu.homework.springbootdemo.common.service.ICommonService;
import com.maidao.edu.homework.springbootdemo.common.util.L;
import com.maidao.edu.homework.springbootdemo.common.util.StringUtils;
import com.sunnysuperman.commons.config.PropertiesConfig;
import com.sunnysuperman.commons.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static com.maidao.edu.homework.springbootdemo.api.user.model.UserErrors.ERR_MOBILECODE_NONE;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:MailService
 * 类描述:TODO
 **/
@Service
public class MailService implements IMailService, AdminErrors {
    @Autowired
    private ICommonService iCommonService;

    @Value("${mailConfig}")
    private String mailConfig;
    private MailHelper.MailConnectionInfo connectionInfo = new MailHelper.MailConnectionInfo();
    private String from = null;

    @PostConstruct
    public void init() {
        L.warn("Mail config: " + mailConfig);
        PropertiesConfig config = new PropertiesConfig(JSONUtil.parseJSONObject(mailConfig));
        connectionInfo.setHost(config.getString("host"));
        connectionInfo.setPort(config.getInt("port", 25));
        connectionInfo.setUsername(config.getString("username"));
        connectionInfo.setPassword(config.getString("password"));
        from = config.getString("from", connectionInfo.getFrom());
    }

    @Override
    public ValCode getValCode(Long key) throws ServiceException {
        ValCode mailCode = iCommonService.getValCode(key);
        if (mailCode == null) {
            throw new ServiceException(ERR_MOBILECODE_NONE);
        }
        return mailCode;
    }

    @Override
    public void sendEmail(long key, String email) throws Exception {
        ValCode code = generatedSmsCode(key, email);
        iCommonService.saveValCode(key, code);
        MailHelper.MailInfo mail = new MailHelper.MailInfo();
        mail.setFromAddress(from);
        mail.setToAddress(email);
        mail.setSubject("迈道教育-注册验证码");
        mail.setContent("尊敬的用户：您的注册验证码：" + code.getCode() + "，10分钟内有效，请尽快完成注册！");
        MailHelper.sendMail(connectionInfo, mail);
    }

    /**
     * 生成随机六位数字
     *
     * @param email
     * @return
     */
    public ValCode generatedSmsCode(long key, String email) {
        String code = StringUtils.getRandNum(6);
        return new ValCode(key, code, 1, 1, email);
    }
}
