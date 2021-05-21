package com.rookie.gktalk.services;

public interface MailService {
    String sendSimpleMail(String toReceiver);
    String sendInvitationTemplateMail(String toReceiver);
    String sendVerificationTemplateMail(String toReceiver);
    String sendTemplateMail(String toReceiver,String templatePath);
}
