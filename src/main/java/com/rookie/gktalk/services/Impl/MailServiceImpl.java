package com.rookie.gktalk.services.Impl;

import com.rookie.gktalk.services.MailService;
import com.rookie.gktalk.utils.common.RandomNumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String sendMailForInvitation(String toReceiver){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        String invitationCode = RandomNumberUtil.getRandomNumber(6);

        simpleMailMessage.setSubject("GT-Talk邀请码");
        simpleMailMessage.setText("请输入此邀请码以开始使用GT-Talk："+invitationCode+"，有效时间为2小时（若不是本人操作，请忽略该邮件）。");

        simpleMailMessage.setFrom("GT-Talk");
        simpleMailMessage.setTo(toReceiver);

        javaMailSender.send(simpleMailMessage);

        return invitationCode;
    }
}
