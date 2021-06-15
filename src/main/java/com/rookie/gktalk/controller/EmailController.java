package com.rookie.gktalk.controller;

import com.rookie.gktalk.services.Impl.MailServiceImpl;
import com.rookie.gktalk.utils.common.Result;
import com.rookie.gktalk.utils.common.StringUtil;
import com.rookie.gktalk.utils.common.TimeUtil;
import com.rookie.gktalk.utils.validate.DataAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Map;

@RestController
@RequestMapping("/mail")
public class EmailController {
    @Autowired
    MailServiceImpl mailService;

    @PostMapping("/invitation")
    public Object postInvitationEmail(@RequestBody Map<String,String> RequestBody, HttpServletRequest request){
        String receiver = RequestBody.get("receiver");

        DataAssert.isTrue(StringUtil.isValid(receiver,StringUtil.EMAIL_REGEX),"请输入正确的邮件！");

//        String invitationCode = mailService.sendSimpleMail(receiver);
//        String invitationCode = mailService.sendInvitationTemplateMail(receiver);
        String invitationCode = mailService.sendTemplateMail(receiver,"mail/invitation");

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("invitationCode",invitationCode);
        httpSession.setAttribute("receiver",receiver);

        Calendar creationTime = TimeUtil.getCurrentTime();
        Calendar deadTime = TimeUtil.setEffectiveTime(creationTime,Calendar.MINUTE,30);
        httpSession.setAttribute("deadTime",deadTime);

        return new Result(200,"成功发送邮件",null);
    }

    /**
     * 发送验证码邮件
     * @param body
     * @param request
     * @return
     */
    @PostMapping("/verification")
    public Object postVerificationEmail(@RequestBody Map<String,String> body,HttpServletRequest request){
        String receiver = body.get("email");

        DataAssert.isTrue(StringUtil.isValid(receiver,StringUtil.EMAIL_REGEX),"请输入正确的邮件！");

        String securityCode = mailService.sendTemplateMail(receiver,"mail/retrieve");

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("securityCode",securityCode);
        httpSession.setAttribute("receiverMail",receiver);

        Calendar creationTime = TimeUtil.getCurrentTime();
        Calendar deadTime = TimeUtil.setEffectiveTime(creationTime,Calendar.MINUTE,30);
        httpSession.setAttribute("deadTime",deadTime);

        return new Result(200,"成功发送邮件",null);
    }
}
