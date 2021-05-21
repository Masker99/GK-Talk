package com.rookie.gktalk.services.Impl;

import com.rookie.gktalk.services.MailService;
import com.rookie.gktalk.utils.common.RandomNumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public String sendSimpleMail(String toReceiver){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        String invitationCode = RandomNumberUtil.getRandomNumber(6);

        simpleMailMessage.setSubject("GT-Talk邀请码");
        simpleMailMessage.setText("请输入此邀请码以开始使用GT-Talk："+invitationCode+"，有效时间为2小时（若不是本人操作，请忽略该邮件）。");

        simpleMailMessage.setFrom("masker.99@qq.com");
        simpleMailMessage.setTo(toReceiver);

        javaMailSender.send(simpleMailMessage);

        return invitationCode;
    }

    @Override
    public String sendInvitationTemplateMail(String toReceiver){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        String invitationCode = RandomNumberUtil.getRandomNumber(6);
        MimeMessageHelper helper = null;

        Context context = new Context();
        context.setVariable("invitationCode",invitationCode);
        context.setVariable("receiver",toReceiver);
        String process = templateEngine.process("mail/invitation",context);

        try {
            helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom("masker.99@qq.com");
            helper.setTo(toReceiver);
            helper.setSubject("GT-Talk");
            helper.setText(process,true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);

        return invitationCode;
    }

    @Override
    public String sendVerificationTemplateMail(String toReceiver){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        String securityCode = RandomNumberUtil.getRandomNumber(6);
        MimeMessageHelper helper = null;

        Context context = new Context();
        context.setVariable("securityCode",securityCode);
        context.setVariable("receiver",toReceiver);

        String process = templateEngine.process("mail/retrieve",context);


        try {
            helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom("masker.99@qq.com");
            helper.setTo(toReceiver);
            helper.setSubject("GT-Talk");
            helper.setText(process,true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);

        return securityCode;
    }

    @Override
    public String sendTemplateMail(String toReceiver,String templatePath){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        String securityCode = RandomNumberUtil.getRandomNumber(6);
        MimeMessageHelper helper = null;

        Context context = new Context();
        context.setVariable("securityCode",securityCode);
        context.setVariable("receiver",toReceiver);

        String process = templateEngine.process(templatePath,context);

        try {
            helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom("masker.99@qq.com");
            helper.setTo(toReceiver);
            helper.setSubject("GT-Talk");
            helper.setText(process,true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);

        return securityCode;
    }

}
